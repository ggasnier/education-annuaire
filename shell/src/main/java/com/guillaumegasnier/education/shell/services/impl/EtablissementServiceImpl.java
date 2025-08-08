package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.*;
import com.guillaumegasnier.education.core.repositories.ContratRepository;
import com.guillaumegasnier.education.core.repositories.EtablissementRepository;
import com.guillaumegasnier.education.core.repositories.IndicePositionSocialeRepository;
import com.guillaumegasnier.education.core.repositories.NatureRepository;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContactEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.EtablissementService;
import com.guillaumegasnier.education.shell.services.ReferenceService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class EtablissementServiceImpl implements EtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final NatureRepository natureRepository;
    private final ContratRepository contratRepository;
    private final IndicePositionSocialeRepository ipsRepository;
    private final Validator validator;
    private final ReferenceService referenceService;
    private final EtablissementMapper etablissementMapper;

    @Autowired
    public EtablissementServiceImpl(EtablissementRepository etablissementRepository, NatureRepository natureRepository, ContratRepository contratRepository, IndicePositionSocialeRepository ipsRepository, Validator validator, ReferenceService referenceService, EtablissementMapper etablissementMapper) {
        this.etablissementRepository = etablissementRepository;
        this.natureRepository = natureRepository;
        this.contratRepository = contratRepository;
        this.ipsRepository = ipsRepository;
        this.validator = validator;
        this.referenceService = referenceService;
        this.etablissementMapper = etablissementMapper;
    }

    @Override
    public void saveEtablissements(List<EtablissementEntity> etablissements) {
        etablissementRepository.saveAll(etablissements);
    }

    @Override
    public void saveNatures(List<NatureEntity> natures) {
        natureRepository.saveAll(natures);
    }

    @Override
    public void saveContrats(List<ContratEntity> contrats) {
        contratRepository.saveAll(contrats);
    }

    @Override
    public Optional<EtablissementEntity> findEtablissement(String uai) {
        return etablissementRepository.findById(uai);
    }

    @Override
    @Cacheable("nature")
    public Optional<NatureEntity> findNature(String codeNature) {
        return natureRepository.findById(codeNature);
    }

    @Override
    @Cacheable("contrat")
    public Optional<ContratEntity> findContrat(String codeContrat) {
        return contratRepository.findById(codeContrat);
    }

    @Override
    public Optional<IndicePositionSocialeEntity> getIPS(String uai, int annee) {
        return ipsRepository.findByPkUaiAndPkAnnee(uai, annee);
    }

    @Override
    public void saveIPS(List<IndicePositionSocialeEntity> entities) {
        ipsRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source) {
        AtomicInteger counter = new AtomicInteger(0);
        int size = datasets.size();
        int progressStep = Math.max(size / 10, 1);

        List<EtablissementEntity> etablissements = datasets.stream()
                .flatMap(dataset ->
                        Arrays.stream(dataset.getUai().split(";"))
                                .map(String::trim)
                                .filter(uai -> !uai.isEmpty())
                                .map(dataset::cloneWithUai))
                .flatMap(dataset -> {
                    String siretField = dataset.getSiret();
                    if (siretField == null || siretField.isBlank()) {
                        return Stream.of(dataset);
                    }
                    return Arrays.stream(siretField.split(","))
                            .map(String::trim)
                            .filter(siret -> !siret.isEmpty())
                            .distinct()
                            .map(dataset::cloneWithSiret);
                })
                .map(dataset -> {
                            int count = counter.incrementAndGet();
                            if (count % progressStep == 0) {
                                var percent = (count * 100) / size;
                                log.info("Avancement {}%", percent);
                            }
                            return this.toEtablissementEntity(dataset, source);
                        }
                )
                .filter(entity -> {
                    Set<ConstraintViolation<EtablissementEntity>> violations = validator.validate(entity);
                    if (!violations.isEmpty()) {
                        // TODO : à enregister dans une autre table en JSONB
                        violations.forEach(v -> {
                                    if (v.getMessage().equals("SIRET invalide")) {
                                        entity.setSiret(null);
                                        if (violations.size() == 1) // Si c'est la seule erreur
                                            violations.clear();
                                    } else {
                                        log.warn("Validation failed on {}.{}: {} ({})", entity.getClass().getSimpleName(), v.getPropertyPath(), v.getMessage(), v.getInvalidValue());
                                    }
                                }
                        );
                    }
                    return violations.isEmpty();
                })
                .toList();

        this.saveEtablissements(etablissements);

        return String.format("Import terminé : %d établissements(s) enregistrée(s).", datasets.size());
    }

    @Transactional
    protected <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source) {

        EtablissementEntity entity = this.findEtablissement(dataset.getUai()).orElseGet(() -> etablissementMapper.toEntity(dataset));

        if (dataset.getCodeCommune() != null) {
            referenceService.findCommune(dataset.getCodeCommune()).ifPresent(entity::setCommune);
        }
        if (dataset.getCodeNature() != null) {
            this.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
        }
        if (dataset.getCodeContrat() != null) {
            this.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
        }

        entity.setContacts(mergeContacts(entity, dataset.getContacts()));

        entity.addSource(source);

        return entity;
    }

    @Transactional
    protected List<ContactEntity> mergeContacts(EtablissementEntity entity, List<ContactEtablissementDataset> contacts) {
        if (entity.getContacts() == null) {
            entity.setContacts(new ArrayList<>());
        }

        Set<ContactPk> existingPks = entity.getContacts()
                .stream()
                .map(ContactEntity::getPk)
                .collect(Collectors.toSet());

        List<ContactEntity> nouveaux = toContactEntityList(entity, contacts).stream()
                .filter(c -> !existingPks.contains(c.getPk()))
                .toList();

        entity.getContacts().addAll(nouveaux);

        return entity.getContacts();
    }

    @Transactional
    protected List<ContactEntity> toContactEntityList(@NonNull EtablissementEntity entity, @NonNull List<ContactEtablissementDataset> contacts) {

        List<ContactEntity> contactEntityList = new ArrayList<>();

        for (ContactEtablissementDataset contact : contacts) {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setPk(new ContactPk(entity.getUai(), contact.getClef(), contact.getValeur()));
            contactEntity.setEtablissement(entity);
            contactEntityList.add(contactEntity);
        }

        return contactEntityList;
    }

    @Override
    @Transactional
    public String createOrUpdateNatures(@NonNull List<NatureDataset> datasets) {
        this.saveNatures(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toNatureEntity)
                .toList());
        return String.format("Import terminé : %d natures(s) enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
        this.saveContrats(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toContratEntity)
                .toList());
        return String.format("Import terminé : %d contrat(s) enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateIPSColleges(@NonNull List<? extends IPSDataset> datasets) {

        List<IndicePositionSocialeEntity> entities = datasets.stream()
                .map(this::toIndicePositionSocialeEntity)
                .filter(Objects::nonNull)
                .toList();

        this.saveIPS(entities);

        return String.format("Import terminé : %d ips enregistrée(s).", datasets.size());
    }

    @Nullable
    protected IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset) {

        IndicePositionSocialeEntity ips = this.getIPS(dataset.getUai(), dataset.getAnnee()).orElseGet(() -> etablissementMapper.toIndicePositionSocialeEntity(dataset));

        Optional<EtablissementEntity> etablissement = this.findEtablissement(dataset.getUai());

        if (etablissement.isPresent()) {
            ips.setEtablissement(etablissement.get());
        } else {
            log.error("Pas d'établissemenet avec UAI : {}", dataset.getUai());
            return null;
        }

        return ips;
    }
}
