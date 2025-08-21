package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.ContactEntity;
import com.guillaumegasnier.education.core.domains.etablissements.ContactPk;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.core.dto.InformationsDto;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
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
public class ShellEtablissementServiceImpl implements ShellEtablissementService {

    private final Validator validator;
    private final EtablissementMapper etablissementMapper;
    private final CoreEtablissementService coreEtablissementService;
    private final CoreReferenceService coreReferenceService;

    public ShellEtablissementServiceImpl(Validator validator, EtablissementMapper etablissementMapper, CoreEtablissementService coreEtablissementService, CoreReferenceService coreReferenceService) {
        this.validator = validator;
        this.etablissementMapper = etablissementMapper;
        this.coreEtablissementService = coreEtablissementService;
        this.coreReferenceService = coreReferenceService;
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

        coreEtablissementService.saveEtablissements(etablissements);

        return String.format("Import terminé : %d établissements(s) enregistrée(s).", datasets.size());
    }

    @Transactional
    protected <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source) {
        EtablissementEntity entity;
        Optional<EtablissementEntity> etablissementEntityOptional = coreEtablissementService.findEtablissement(dataset.getUai());

        // Etablissement existant (mise à jour de certains champs)
        if (etablissementEntityOptional.isPresent()) {
            entity = etablissementEntityOptional.get();
            // uai : ne change pas
            // siret : ne change pas
            // nom : ne change pas
            // nature : ne change pas (à vérifier)
            // contrat : ne change pas (à vérifier)
            // adresse
            // complément
            // codePostal
            // commune
            // pays
            entity.setDateOuverture(dataset.getDateOuverture());
            entity.setDateFermeture(dataset.getDateFermeture());
            entity.setEtat(dataset.getEtat());
        } else {
            // Nouvel établissement
            entity = etablissementMapper.toEntity(dataset);

            if (dataset.getCodeCommune() != null) {
                coreReferenceService.findCommune(dataset.getCodeCommune()).ifPresent(entity::setCommune);
            }
            if (dataset.getCodeNature() != null) {
                coreEtablissementService.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
            }
            if (dataset.getCodeContrat() != null) {
                coreEtablissementService.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
            }
        }
        
        entity.getInformations().getOptions().addAll(dataset.getOptions());

//        entity.setContacts(mergeContacts(entity, dataset.getContacts()));
        entity.addSource(source);

        return entity;
    }

//    @Transactional
//    protected List<ContactEntity> mergeContacts(@NonNull EtablissementEntity entity, List<ContactEtablissementDataset> contacts) {
//        if (entity.getContacts() == null) {
//            entity.setContacts(new ArrayList<>());
//        }
//
//        Set<ContactPk> existingPks = entity.getContacts()
//                .stream()
//                .map(ContactEntity::getPk)
//                .collect(Collectors.toSet());
//
//        List<ContactEntity> nouveaux = toContactEntityList(entity, contacts).stream()
//                .filter(c -> !existingPks.contains(c.getPk()))
//                .toList();
//
//        entity.getContacts().addAll(nouveaux);
//
//        return entity.getContacts();
//    }

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
        coreEtablissementService.saveNatures(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(etablissementMapper::toNatureEntity)
                .toList());
        return String.format("Import terminé : %d natures(s) enregistrée(s).", datasets.size());
    }

    @Override
    @Transactional
    public String createOrUpdateContrats(@NonNull List<ContratDataset> datasets) {
        coreEtablissementService.saveContrats(datasets.stream()
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

        coreEtablissementService.saveIPS(entities);

        return String.format("Import terminé : %d ips enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets) {

        List<EtablissementEntity> entities = new ArrayList<>();

        datasets.stream().collect(Collectors.groupingBy(SectionSportiveDataset::getUai))
                .forEach((uai, sectionSportiveList) -> {
                    List<String> sections = sectionSportiveList
                            .stream()
                            .map(SectionSportiveDataset::getSectionList)
                            .flatMap(List::stream)
                            .map(String::toLowerCase)
                            .distinct()
                            .toList();

                    var entity = coreEtablissementService.findEtablissement(uai);
                    if (entity.isPresent()) {
                        InformationsDto informations = entity.get().getInformations();
                        if (informations == null)
                            informations = new InformationsDto();

                        informations.setSectionsSportives(sections);
                        entity.get().setInformations(informations);
                        entities.add(entity.get());
                    } else {
                        log.error("Etablissement {} non trouvé", uai);
                    }
                });

        coreEtablissementService.saveEtablissements(entities);

        return String.format("Import terminé : %d sections sportives enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateLangues(@NonNull List<LangueDataset> datasets) {

        List<EtablissementEntity> entities = new ArrayList<>();

        datasets.stream().collect(Collectors.groupingBy(LangueDataset::getUai))
                .forEach((uai, langueList) -> {

                    List<InformationsDto.LangueDto> langues = langueList.stream().map(etablissementMapper::toLangueDto).toList();

                    var entity = coreEtablissementService.findEtablissement(uai);
                    if (entity.isPresent()) {
                        InformationsDto informations = entity.get().getInformations();
                        if (informations == null)
                            informations = new InformationsDto();

                        informations.setLangues(langues);
                        entity.get().setInformations(informations);

                        entities.add(entity.get());

                    } else {
                        log.error("Etablissement {} non trouvé", uai);
                    }

                });

        coreEtablissementService.saveEtablissements(entities);

        return String.format("Import terminé : %d langues enregistrée(s).", datasets.size());
    }

    @Nullable
    protected IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset) {

        IndicePositionSocialeEntity ips = coreEtablissementService.findIPS(dataset.getUai(), dataset.getAnnee()).orElseGet(() -> etablissementMapper.toIndicePositionSocialeEntity(dataset));

        Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissement.isPresent()) {
            ips.setEtablissement(etablissement.get());
        } else {
            log.error("Pas d'établissemenet avec UAI : {}", dataset.getUai());
            return null;
        }

        return ips;
    }
}
