package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.SectionInternationale;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.guillaumegasnier.education.shell.mappers.DateMapper.toLocalDate;

@Slf4j
@Service
public class ShellEntityServiceImpl implements ShellEntityService {

    private final CoreReferenceService coreReferenceService;
    private final CoreEtablissementService coreEtablissementService;
    private final CoreFormationService coreFormationService;
    private final EtablissementMapper etablissementMapper;
    private final FormationMapper formationMapper;

    @Autowired
    public ShellEntityServiceImpl(CoreReferenceService coreReferenceService, CoreEtablissementService coreEtablissementService, CoreFormationService coreFormationService, EtablissementMapper etablissementMapper, FormationMapper formationMapper) {
        this.coreReferenceService = coreReferenceService;
        this.coreEtablissementService = coreEtablissementService;
        this.coreFormationService = coreFormationService;
        this.etablissementMapper = etablissementMapper;
        this.formationMapper = formationMapper;
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

        if (dataset.getCodeContrat() != null) {
            coreEtablissementService.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
        }

        if (dataset.getDateOuverture() != null)
            entity.setDateOuverture(dataset.getDateOuverture());

        if (dataset.getDateFermeture() != null)
            entity.setDateFermeture(dataset.getDateFermeture());

        entity.setUpdatedAt(LocalDateTime.now());

        if (dataset.isActif() != null) {
            entity.setActif(dataset.isActif());
        }

        if (dataset.getEducationPrioritaire() != null) {
            entity.setEducationPrioritaire(dataset.getEducationPrioritaire());
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
    public IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset, @NonNull String categorie) {

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
        entity.setCategorie(categorie);
        entity.setIndice(dataset.getIndice());
        entity.setEcartType(dataset.getEcartType());

        return entity;
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

    @Override
    public FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset) {
        var entity = coreFormationService.findFormationByOnisepId(dataset.getFormationOnisepId());

        if (entity.isPresent()) {
            return entity.get();
            // TODO mise à jour
        } else {
            Optional<EtablissementEntity> etablissementEntityOpt = coreEtablissementService.findEtablissement(dataset.getEtablissementUai());
            if (etablissementEntityOpt.isEmpty()) {
                log.warn("Rien trouvé avec {}/{}/{}/{}", dataset.getEtablissementUai(), dataset.getEtablissmentOnisepId(), dataset.getEtablissementNom(), dataset.getEtablissementAdresse());
                return null;
            }
            FormationEntity formationEntity = formationMapper.toFormationEntity(dataset, etablissementEntityOpt.get());
            coreFormationService.saveFormation(formationEntity);
            return formationEntity;
        }
    }

    @Override
    public OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset) {
        return coreEtablissementService.findOrganisme(dataset.getNumeroDeclarationActivite())
                .map(organismeEntity -> toOrganismeEntityOld(organismeEntity, dataset))
                .orElseGet(() -> toOrganismeEntityNew(dataset));
    }

    private OrganismeEntity toOrganismeEntityNew(TravailOrganismeFormationDataset dataset) {
        var o = etablissementMapper.toOrganismeEntity(dataset);
        coreReferenceService.findCommuneByNom(dataset.getNomCommune()).ifPresent(o::setCommune);
        return o;
    }

    private OrganismeEntity toOrganismeEntityOld(OrganismeEntity organismeEntity, TravailOrganismeFormationDataset dataset) {
        organismeEntity.setActionsDeFormation(dataset.getActionsDeFormation());
        organismeEntity.setBilansDeCompetences(dataset.getBilansDeCompetences());
        organismeEntity.setValidationAcquisExperience(dataset.getValidationAcquisExperience());
        organismeEntity.setActionsDeFormationParApprentissage(dataset.getActionsDeFormationParApprentissage());
        organismeEntity.setDateDebut(toLocalDate(dataset.getDateDebut()));
        organismeEntity.setDateFin(toLocalDate(dataset.getDateFin()));
        organismeEntity.setDateDerniereDeclaration(toLocalDate(dataset.getDateDerniereDeclaration()));

        return organismeEntity;
    }
}
