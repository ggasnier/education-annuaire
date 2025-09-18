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
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ShellEntityServiceImpl implements ShellEntityService {

    private final Validator validator;
    private final CoreReferenceService coreReferenceService;
    private final CoreEtablissementService coreEtablissementService;
    private final EtablissementMapper etablissementMapper;

    @Autowired
    public ShellEntityServiceImpl(Validator validator, CoreReferenceService coreReferenceService, CoreEtablissementService coreEtablissementService, EtablissementMapper etablissementMapper) {
        this.validator = validator;
        this.coreReferenceService = coreReferenceService;
        this.coreEtablissementService = coreEtablissementService;
        this.etablissementMapper = etablissementMapper;
    }

    @Override
    public <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, @NonNull String source) {
        return coreEtablissementService.findEtablissement(dataset.getUai())
                .map(etablissementEntity -> toEtablissementEntityOld(etablissementEntity, dataset, source))
                .orElseGet(() -> toEtablissementEntityNew(dataset, source));
    }

    private <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityOld(@NonNull EtablissementEntity entity, @NonNull T dataset, @NonNull String source) {
        // Ne mettre à jour les champs que si ils sont renseignés

        if (dataset.getCodeNature() != null) {
            coreEtablissementService.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
        }

        if (dataset.getDateOuverture() != null)
            entity.setDateOuverture(dataset.getDateOuverture());

        if (dataset.getDateFermeture() != null)
            entity.setDateFermeture(dataset.getDateFermeture());

        entity.setUpdatedAt(LocalDateTime.now());

        if (dataset.getEtat() != null) {
            entity.setEtat(dataset.getEtat());
        }

        entity.addSource(source);

        return entity;
    }

    private <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityNew(@NonNull T dataset, @NonNull String source) {
        EtablissementEntity entity = etablissementMapper.toEntity(dataset);

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

        // Ne renseigner l'état que si il est renseigné
        if (dataset.getEtat() != null) {
            entity.setEtat(dataset.getEtat());
        }

        entity.addSource(source);

        return entity;
    }

    @Override
    public List<OptionEtablissementEntity> toOptionEtablissementEntity(@NonNull EtablissementDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour etablissement", dataset.getUai());
            return List.of();
        }

        return dataset.getOptions()
                .stream()
                .map(option -> {
                    OptionEtablissementPK pk = new OptionEtablissementPK();
                    pk.setOption(option);
                    pk.setUai(dataset.getUai());

                    OptionEtablissementEntity entity = new OptionEtablissementEntity();
                    entity.setPk(pk);
                    entity.setEtablissement(etablissementOpt.get());

                    return entity;
                })
                .toList();
    }

    @Nullable
    @Override
    public OptionEtablissementEntity toOptionEtablissementEntity(@NonNull SectionBinationaleDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour sections binationales", dataset.getUai());
            return null;
        }

        OptionEtablissementPK pk = new OptionEtablissementPK();
        pk.setUai(dataset.getUai());
        pk.setOption(dataset.getOption());

        OptionEtablissementEntity entity = new OptionEtablissementEntity();
        entity.setPk(pk);
        entity.setEtablissement(etablissementOpt.get());

        return entity;
    }

    @Override
    public List<SectionSportiveEntity> toSectionSportiveEntity(@NonNull SectionSportiveDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour sections sportives", dataset.getUai());
            return List.of();
        }

        return dataset.getSectionList()
                .stream()
                .map(String::toUpperCase)
                .map(Sport::transformation)
                .filter(Objects::nonNull)
                .map(section -> {
                    SectionSportivePK pk = new SectionSportivePK();
                    pk.setUai(dataset.getUai());
                    pk.setSport(section);

                    SectionSportiveEntity entity = new SectionSportiveEntity();
                    entity.setPk(pk);
                    entity.setEtablissement(etablissementOpt.get());

                    return entity;
                })
                .toList();
    }

    @Nullable
    @Override
    public LangueEntity toLangueEntity(@NonNull LangueDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour langues", dataset.getUai());
            return null;
        }

        Langue langue = Langue.transformation(dataset.getLangue());

        if (langue != null) {
            LanguePK pk = new LanguePK();
            pk.setLangue(langue);
            pk.setUai(dataset.getUai());
            pk.setEnseignement(dataset.getEnseignement());

            LangueEntity entity = new LangueEntity();
            entity.setPk(pk);
            entity.setEtablissement(etablissementOpt.get());

            return entity;
        }

        return null;
    }

    @Override
    public List<SpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour specialites", dataset.getUai());
            return List.of();
        }

        return dataset.getSpecialites().stream().map(
                specialiteBac -> {
                    SpecialitePK pk = new SpecialitePK();
                    pk.setUai(dataset.getUai());
                    pk.setSpecialite(specialiteBac);

                    SpecialiteEntity entity = new SpecialiteEntity();
                    entity.setPk(pk);
                    entity.setEtablissement(etablissementOpt.get());

                    return entity;
                }
        ).toList();
    }

    @Override
    public List<SectionInternationaleEntity> toSectionInternationaleEntity(@NonNull SectionInternationaleDataset dataset) {
        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour sections internationales", dataset.getUai());
            return List.of();
        }

        return dataset.getNiveaux().stream()
                .map(niveau -> {
                    SectionInternationalePK pk = new SectionInternationalePK();
                    pk.setUai(dataset.getUai());
                    pk.setSection(SectionInternationale.transformation(dataset.getSection()));
                    pk.setNiveau(niveau);

                    SectionInternationaleEntity entity = new SectionInternationaleEntity();
                    entity.setPk(pk);
                    entity.setEtablissement(etablissementOpt.get()); // association avec l'établissement
                    return entity;
                })
                .toList();
    }

    @Nullable
    @Override
    public IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset) {

        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour IPS", dataset.getUai());
            return null;
        }

        IndicePositionSocialePK pk = new IndicePositionSocialePK();
        pk.setUai(dataset.getUai());
        pk.setAnnee(dataset.getAnnee());

        IndicePositionSocialeEntity entity = new IndicePositionSocialeEntity();
        entity.setPk(pk);
        entity.setEtablissement(etablissementOpt.get());
        entity.setIndice(dataset.getIndice());
        entity.setEcartType(dataset.getEcartType());

        return entity;
    }

    /**
     * @param entity Entité JPA à valider
     * @return Résultat de la validation
     */
    @Override
    public <T> T toValidEntity(@NonNull T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            return entity;
        }

        if (entity instanceof EtablissementEntity) {
            return toValidEtablissementEntity(entity, violations);
        } else {
            for (ConstraintViolation<T> v : violations) {
                log.warn("Validation failed on {}.{}: {} ({})",
                        entity.getClass().getSimpleName(),
                        v.getPropertyPath(),
                        v.getMessage(),
                        v.getInvalidValue());
            }
        }

        return null;
    }

    private <T> T toValidEtablissementEntity(T entity, Set<ConstraintViolation<T>> violations) {

        for (ConstraintViolation<T> v : violations) {
            log.warn("Validation failed on {}.{}: {} ({})",
                    entity.getClass().getSimpleName(),
                    v.getPropertyPath(),
                    v.getMessage(),
                    v.getInvalidValue());
        }

        return null;
    }

    @Nullable
    @Override
    public SportEtudeEntity toSportEtudeEntity(@NonNull SectionSportEtudeDataset dataset) {
        Optional<EtablissementEntity> etablissementOpt = coreEtablissementService.findEtablissement(dataset.getUai());

        if (etablissementOpt.isEmpty()) {
            log.warn("Pas d'établissement avec UAI {} pour sections sport etudes", dataset.getUai());
            return null;
        }

        Sport sport = Sport.transformation(dataset.getNomSport());

        if (sport != null) {
            SportEtudePK pk = new SportEtudePK();
            pk.setUai(dataset.getUai());
            pk.setSport(sport);

            SportEtudeEntity entity = new SportEtudeEntity();
            entity.setPk(pk);
            entity.setEtablissement(etablissementOpt.get());

            return entity;
        }

        return null;
    }
}
