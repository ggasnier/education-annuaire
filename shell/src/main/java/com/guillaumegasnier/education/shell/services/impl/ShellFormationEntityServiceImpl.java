package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.services.ShellFormationEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShellFormationEntityServiceImpl implements ShellFormationEntityService {

    private final CoreEtablissementService coreEtablissementService;

    @Override
    public FormationEntity toFormationEntity(@NonNull FormationType dataset) {

        log.info("{}: {}", dataset.getNumero(), dataset.getIntituleFormation().getValue());

        String formationOnisepId = dataset.getNumero().split("[._]")[1];

        FormationEntity entity = new FormationEntity();

        entity.setId(UUID.nameUUIDFromBytes(formationOnisepId.getBytes()));
        // Nom de la formation
        entity.setNom(dataset.getIntituleFormation().getValue());
        // Objectifs de la formation
        if (dataset.getObjectifFormation().getValue() != null && !"-".equals(dataset.getObjectifFormation().getValue()))
            entity.setObjectif(dataset.getObjectifFormation().getValue());
        // Resultats attendus lors de l aformation
        if (dataset.getResultatsAttendus().getValue() != null && !dataset.getResultatsAttendus().getValue().contains("FOR."))
            entity.setResultats(dataset.getResultatsAttendus().getValue());
        // Contenu de la formation
        if (dataset.getContenuFormation().getValue() != null && !"-".equals(dataset.getContenuFormation().getValue()))
            entity.setContenu(dataset.getContenuFormation().getValue());
        // Foration certifiante (vrai diplome)
        entity.setCertifiante("1".equals(dataset.getCertifiante().getValue()));
        //
        entity.setParcoursDeFormation(Integer.parseInt(dataset.getParcoursDeFormation().getValue()));
        //
        entity.setCodeNiveauEntree(Integer.parseInt(dataset.getCodeNiveauEntree().getValue()));
        //
        entity.setCodeNiveauSortie(Integer.parseInt(dataset.getCodeNiveauSortie().getValue()));
        //
        if (dataset.getIdentifiantModule() != null)
            entity.setIdentifiantModule(dataset.getIdentifiantModule().getValue());
        //
        if (dataset.getPositionnement() != null)
            entity.setPositionnement(Integer.parseInt(dataset.getPositionnement().getValue()));

        entity.setActions(toActions(dataset));

        entity.setOnisepId(Integer.parseInt(formationOnisepId));

        return entity;
    }

    protected List<ActionFormationEntity> toActions(@NonNull FormationType dataset) {

        List<ActionFormationEntity> entities = new java.util.ArrayList<>();

        dataset.getAction().forEach(actionType -> {
            ActionFormationEntity entity = new ActionFormationEntity();
            String actionOnisepId = actionType.getNumero().split("[._]")[3];
            entity.setId(UUID.nameUUIDFromBytes(actionOnisepId.getBytes()));
            entity.setRythmeFormation(actionType.getRythmeFormation().getValue());
            entity.setCodePublicVise(actionType.getCodePublicVise().getFirst().getValue());
            if (actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation() != null)
                if (coreEtablissementService.isEtablissementExiste(actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation().getCodeUAI().getValue()))
                    entity.setEtablissement(coreEtablissementService.getEtablissementReferenceByUai(actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation().getCodeUAI().getValue()));

            // TODO
            entity.setOnisepId(Integer.parseInt(actionOnisepId));
            entities.add(entity);
        });

        return entities;
    }
}
