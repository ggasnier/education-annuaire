package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.transformers.FormationTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.guillaumegasnier.education.shell.mappers.DateMapper.toLocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FormationTransformerImpl implements FormationTransformer {

    private final CoreTerritoireService coreTerritoireService;
    private final CoreFormationService coreFormationService;
    private final CoreEtablissementService coreEtablissementService;
    private final FormationMapper formationMapper;
    private final EtablissementMapper etablissementMapper;

    @Override
    public FormationDTO recalculId(@NonNull FormationDTO dto) {
        // Si on a déjà l'onisepId c'est bon
        if (dto.getOnisepId() != null && dto.getOnisepId() > 0)
            return dto;

        if (dto.getParcoursupId() != null && dto.getParcoursupId() > 0) {
            Optional<LienOnisepEntity> opt = coreFormationService.findLienOnisep("PS", dto.getParcoursupId().toString());
            if (opt.isPresent()) {
                dto.setOnisepId(opt.get().getPk().getOnisepId());
                dto.setId(UUID.nameUUIDFromBytes(("FOR" + dto.getOnisepId()).getBytes()));
            }
            return dto;
        }
        return dto;
    }

    @Override
    public OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset) {
        return coreEtablissementService.findOrganisme(dataset.getNumeroDeclarationActivite())
                .map(organismeEntity -> toOrganismeEntityOld(organismeEntity, dataset))
                .orElseGet(() -> toOrganismeEntityNew(dataset));
    }

    private OrganismeEntity toOrganismeEntityNew(TravailOrganismeFormationDataset dataset) {
        var o = etablissementMapper.toOrganismeEntity(dataset);
        coreTerritoireService.findCommuneByNom(dataset.getNomCommune()).ifPresent(o::setCommune);
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

    @Override
    public FormationEntity toFormationEntity(@NonNull FormationDTO dto, @NonNull String source) {
        return coreFormationService.findFormation(dto.getId())
                .map(entity -> toFormationEntityOld(entity, dto, source))
                .orElseGet(() -> toFormationEntityNew(dto, source));
    }

    @Override
    public ActionFormationEntity toActionFormationEntity(@NonNull ActionFormationDTO dto, @NonNull String source) {
        return coreFormationService.findActionFormation(dto.getId())
                .map(entity -> toActionFormationEntityOld(entity, dto, source))
                .orElseGet(() -> toActionFormationEntityNew(dto, source));
    }

    private ActionFormationEntity toActionFormationEntityNew(@NonNull ActionFormationDTO dto, @NonNull String source) {

        ActionFormationEntity entity = formationMapper.toActionFormationEntity(dto);

        // Formation
        entity.setFormation(coreFormationService.findFormation(dto.getFormationId()).get()); // TODO

        // Etablissement
        if (dto.getUai() != null && !dto.getUai().isBlank()) {
            if (coreEtablissementService.isEtablissementExiste(dto.getUai())) {
                entity.setEtablissement(coreEtablissementService.getEtablissementReferenceByUai(dto.getUai()));
            } else {
                log.warn("Pas d'établissement {} pour action de formation {}", dto.getUai(), dto.getOnisepId());
            }
        }

        return entity;
    }

    // TODO à compléter
    private ActionFormationEntity toActionFormationEntityOld(ActionFormationEntity entity, @NonNull ActionFormationDTO dto, @NonNull String source) {

        if (entity.getModalitesEntreesSorties() == null && dto.getModalitesEntreesSorties() != null)
            entity.setModalitesEntreesSorties(dto.getModalitesEntreesSorties());

        if (entity.getUrlAction() == null && dto.getUrlAction() != null)
            entity.setUrlAction(dto.getUrlAction());

        if (entity.getDureeIndicative() == null && dto.getDureeIndicative() != null)
            entity.setDureeIndicative(dto.getDureeIndicative());

        if (entity.getAccesHandicapes() == null && dto.getAccesHandicapes() != null)
            entity.setAccesHandicapes(dto.getAccesHandicapes());

        return entity;
    }

    private FormationEntity toFormationEntityNew(@NonNull FormationDTO dto, @NonNull String source) {

        FormationEntity entity = formationMapper.toFormationEntity(dto);
        // Etablissement responsable de la formation
        // Organisme responsable de la formation

        // Source des données
        entity.addSource(source);

        return entity;
    }

    private FormationEntity toFormationEntityOld(@NonNull FormationEntity entity, @NonNull FormationDTO dto, @NonNull String source) {

        // TODO pour le moment on ne met rien à jour
        // Les ids
        if (entity.getOnisepId() == null && dto.getOnisepId() != null)
            entity.setOnisepId(dto.getOnisepId());
        if (entity.getParcoursupId() == null && dto.getParcoursupId() != null)
            entity.setParcoursupId(dto.getParcoursupId());

        if (entity.getOnisepId() != null && dto.getOnisepId() != null) {
            if (!entity.getOnisepId().equals(dto.getOnisepId())) {
                log.warn("Erreur avec l'onisep_id : {} vs {}", entity.getOnisepId(), dto.getOnisepId());
            }
        }
        /*if (entity.getParcoursupId() != null && dto.getParcoursupId() != null) {
            if (!entity.getParcoursupId().equals(dto.getParcoursupId())) {
                log.warn("Erreur avec le parcoursup_id : {} vs {}", entity.getParcoursupId(), dto.getParcoursupId());
            }
        }*/

        entity.addSource(source);

        return entity;
    }
}
