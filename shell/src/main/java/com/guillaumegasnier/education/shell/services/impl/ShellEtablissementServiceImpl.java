package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.SectionInternationale;
import com.guillaumegasnier.education.core.enums.Sport;
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

import java.time.LocalDateTime;
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

        // Les établissements
        List<EtablissementEntity> etablissements = datasets.stream()
                .flatMap(this::dedoublement)
                .map(dataset -> toEntityWithProgress(dataset, source, counter, size, progressStep))
                .filter(this::isValidEntity)
                .toList();
        coreEtablissementService.saveEtablissements(etablissements);

        // Les options
        List<OptionEtablissementEntity> options = datasets.stream()
                .map(this::toOptions)
                .flatMap(List::stream)
                .toList();
        coreEtablissementService.saveOptions(options);

        // Les contacts

        return String.format("Import terminé : %d établissements(s) enregistrée(s).", datasets.size());
    }

    private List<OptionEtablissementEntity> toOptions(EtablissementDataset dataset) {

        List<OptionEtablissementEntity> entities = new ArrayList<>();

        dataset.getOptions().forEach(option -> {
            Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

            if (etablissement.isPresent()) {
                OptionEtablissementPK pk = new OptionEtablissementPK();
                pk.setOption(option);
                pk.setUai(dataset.getUai());
                OptionEtablissementEntity entity = new OptionEtablissementEntity();
                entity.setPk(pk);
                entity.setEtablissement(etablissement.get());
                entities.add(entity);
            }
        });

        return entities;
    }

    /**
     * @param dataset
     * @param source
     * @param counter
     * @param size
     * @param progressStep
     * @return L'entité JPA {@link EtablissementEntity}
     */
    @Transactional
    protected EtablissementEntity toEntityWithProgress(
            EtablissementDataset dataset,
            String source,
            @NonNull AtomicInteger counter,
            int size,
            int progressStep
    ) {
        int count = counter.incrementAndGet();
        if (count % progressStep == 0) {
            var percent = (count * 100) / size;
            log.info("Avancement {}%", percent);
        }
        return toEtablissementEntity(dataset, source);
    }

    /**
     * @param entity Entité JPA à valider
     * @return Résultat de la validation
     */
    private boolean isValidEntity(EtablissementEntity entity) {
        Set<ConstraintViolation<EtablissementEntity>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return true;
        }

        violations.removeIf(v -> {
//            if (v.getMessage().equals("SIRET invalide")) {
//                entity.setSiret(null);
//                return violations.size() == 1; // suppression si c'était la seule erreur
//            }
            log.warn("Validation failed on {}.{}: {} ({})",
                    entity.getClass().getSimpleName(),
                    v.getPropertyPath(),
                    v.getMessage(),
                    v.getInvalidValue());
            return false;
        });

        return violations.isEmpty();
    }

    /**
     * <p>Dédoublement des datasets</p>
     * <ul>
     *     <li>UAI séparés par ";"</li>
     *     <li>SIRET séparés par ","</li>
     * </ul>
     */
    private Stream<EtablissementDataset> dedoublement(@NonNull EtablissementDataset etablissementDataset) {
        return Stream.of(etablissementDataset)
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
                });
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
            // adresse : ne change pas (à vérifier)
            // complément : ne change pas (à vérifier)
            // codePostal : ne change pas (à vérifier)
            // commune : ne change pas (à vérifier)
            entity.setDateOuverture(dataset.getDateOuverture());
            entity.setDateFermeture(dataset.getDateFermeture());
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            // Nouvel établissement
            entity = etablissementMapper.toEntity(dataset);

            if (dataset.getCodeCommune() != null) {
                var communeOptional = coreReferenceService.findCommune(dataset.getCodeCommune());
                if (communeOptional.isPresent()) {
                    entity.setCommune(communeOptional.get());
                } else {
                    communeOptional = coreReferenceService.findCommuneByNom(dataset.getNomCommune());
                    if (communeOptional.isPresent()) {
                        entity.setCommune(communeOptional.get());
                    } else {
                        log.warn("Commune inconnue pour {} / {}", dataset.getCodeCommune(), dataset.getNomCommune());
                    }
                }
            } else {
                var communeOptional = coreReferenceService.findCommuneByNom(dataset.getNomCommune());

                if (communeOptional.isPresent()) {
                    entity.setCommune(communeOptional.get());
                } else {
                    log.warn("Commune absente pour {} / {}", dataset.getUai(), dataset.getNomCommune());
                }
            }
            if (dataset.getCodeNature() != null) {
                coreEtablissementService.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
            }
            if (dataset.getCodeContrat() != null) {
                coreEtablissementService.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
            }

            // N emettre à jour l'état que si il est renseigné
            if (dataset.getEtat() != null) {
                entity.setEtat(dataset.getEtat());
            }

        }

        entity.addSource(source);

        return entity;
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
        coreEtablissementService.saveIPS(datasets.stream()
                .map(this::toIndicePositionSocialeEntity)
                .filter(Objects::nonNull)
                .toList());
        return String.format("Import terminé : %d ips enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets) {

        List<SectionSportiveEntity> entities = new ArrayList<>();

        datasets.stream().collect(Collectors.groupingBy(SectionSportiveDataset::getUai))
                .forEach((uai, sectionSportiveList) -> {
                    Set<Sport> sections = sectionSportiveList
                            .stream()
                            .map(SectionSportiveDataset::getSectionList)
                            .flatMap(List::stream)
                            .map(String::toUpperCase)
                            .map(Sport::transformation)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());

                    Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(uai);

                    if (etablissement.isPresent()) {
                        sections.forEach(sport -> {
                            SectionSportivePK pk = new SectionSportivePK();
                            pk.setUai(uai);
                            pk.setSport(sport);
                            SectionSportiveEntity entity = new SectionSportiveEntity();
                            entity.setPk(pk);
                            entity.setEtablissement(etablissement.get());
                            entities.add(entity);
                        });
                    } else {
                        log.error("Pas d'établissement avec UAI {} pour sections sportives", uai);
                    }
                });

        coreEtablissementService.saveSectionsSporties(entities);

        return String.format("Import terminé : %d sections sportives enregistrée(s).", entities.size());
    }

    @Override
    public String createOrUpdateLangues(@NonNull List<LangueDataset> datasets) {

        List<LangueEntity> entities = new ArrayList<>();

        datasets.stream().collect(Collectors.groupingBy(LangueDataset::getUai))
                .forEach((uai, langueList) -> {
                    langueList.forEach(langueDataset -> {

                        Langue langue = Langue.transformation(langueDataset.getLangue());
                        String enseignement = langueDataset.getEnseignement();

                        if (langue != null && enseignement != null) {
                            LanguePK pk = new LanguePK();
                            pk.setLangue(langue);
                            pk.setUai(uai);
                            pk.setEnseignement(enseignement);

                            LangueEntity entity = new LangueEntity();
                            entity.setPk(pk);

                            Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(uai);

                            if (etablissement.isPresent()) {
                                entity.setEtablissement(etablissement.get());
                                entities.add(entity);
                            } else {
                                log.error("Pas d'établissement avec UAI {} pour langues", uai);
                            }
                        }
                    });
                });

        coreEtablissementService.saveLangues(entities);

        return String.format("Import terminé : %d langues enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets) {

        List<SpecialiteEntity> entities = datasets
                .stream()
                .filter(dataset -> dataset.getUai() != null && !dataset.getUai().isBlank())
                .map(dataset -> {
                    List<SpecialiteEntity> entities1 = new ArrayList<>();
                    dataset.getSpecialites().forEach(
                            specialite -> {
                                SpecialitePK pk = new SpecialitePK();
                                pk.setUai(dataset.getUai());
                                pk.setSpecialite(specialite);
                                SpecialiteEntity entity = new SpecialiteEntity();
                                entity.setPk(pk);

                                Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

                                if (etablissement.isPresent()) {
                                    entity.setEtablissement(etablissement.get());
                                    entities1.add(entity);
                                } else {
                                    log.error("Pas d'établissement avec UAI {} pour specialites", dataset.getUai());
                                }
                            }
                    );
                    return entities1;
                })
                .flatMap(List::stream)
                .toList();

        coreEtablissementService.saveSpecialites(entities);

        return String.format("Import terminé : %d specialités enregistrée(s).", entities.size());
    }

    @Override
    public String createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets) {

        List<SectionInternationaleEntity> entities = new ArrayList<>();

        datasets.forEach(dataset -> {
            dataset.getNiveaux().forEach(niveau -> {

                SectionInternationalePK pk = new SectionInternationalePK();
                pk.setUai(dataset.getUai());
                pk.setSection(SectionInternationale.transformation(dataset.getSection()));
                pk.setNiveau(niveau);
                SectionInternationaleEntity entity = new SectionInternationaleEntity();
                entity.setPk(pk);

                Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

                if (etablissement.isPresent()) {
                    entity.setEtablissement(etablissement.get());
                    entities.add(entity);
                } else {
                    log.error("Pas d'établissement avec UAI {} pour sections internationales", dataset.getUai());
                }
            });
        });

        coreEtablissementService.saveSectionsInternationales(entities);

        return String.format("Import terminé : %d sections internationale(s).", entities.size());

    }

    @Override
    public String createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets) {
        List<OptionEtablissementEntity> entities = new ArrayList<>();

        datasets.forEach(dataset -> {
            Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

            if (etablissement.isPresent()) {
                OptionEtablissementPK pk = new OptionEtablissementPK();
                pk.setUai(dataset.getUai());
                pk.setOption(dataset.getOption());

                OptionEtablissementEntity entity = new OptionEtablissementEntity();
                entity.setPk(pk);
                entity.setEtablissement(etablissement.get());
                entities.add(entity);
            } else {
                log.error("Pas d'établissement avec UAI {} pour sections binationales", dataset.getUai());
            }
        });

        coreEtablissementService.saveOptions(entities);

        return String.format("Import terminé : %d sections binationale(s).", entities.size());
    }

    @Nullable
    protected IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset) {

        IndicePositionSocialeEntity ips = coreEtablissementService.findIPS(dataset.getUai(), dataset.getAnnee()).orElseGet(() -> etablissementMapper.toIndicePositionSocialeEntity(dataset));

        Optional<EtablissementEntity> etablissement = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissement.isPresent()) {
            ips.setEtablissement(etablissement.get());
        } else {
            log.error("Pas d'établissement avec UAI {} pour IPS", dataset.getUai());
            return null;
        }

        return ips;
    }
}
