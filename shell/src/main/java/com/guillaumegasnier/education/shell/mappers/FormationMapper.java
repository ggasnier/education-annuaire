package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.shell.datasets.ActionType;
import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class FormationMapper {

    @Mapping(target = "id", source = "formationId")
    @Mapping(target = "onisepId", source = "formationOnisepId")
    @Mapping(target = "nom", source = "intituleLong")
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "codeNiveauSortie", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    public abstract FormationDTO toFormationDTO(CarifFormationDataset dataset);

    @Mapping(target = "id", source = "formationId")
    @Mapping(target = "onisepId", source = "formationOnisepId")
    @Mapping(target = "nom", source = "formationNom")
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    public abstract FormationDTO toFormationDTO(OnisepFormationDataset dataset);

    @Mapping(target = "id", source = "actionFormationId")
    @Mapping(target = "uai", source = "etablissementUai")
    @Mapping(target = "urlAction", ignore = true)
    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "rythmeFormation", ignore = true)
    @Mapping(target = "restauration", ignore = true)
    @Mapping(target = "prixTotalTTC", ignore = true)
    @Mapping(target = "prixHoraireTTC", ignore = true)
    @Mapping(target = "priseEnChargeFraisPossible", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true)
    @Mapping(target = "organismeFormateur", ignore = true)
    @Mapping(target = "organismeFinanceur", ignore = true)
    @Mapping(target = "onisepId", ignore = true)
    @Mapping(target = "nombreHeuresTotal", ignore = true)
    @Mapping(target = "nombreHeuresEntreprise", ignore = true)
    @Mapping(target = "nombreHeuresCentre", ignore = true)
    @Mapping(target = "niveauEntreeObligatoire", ignore = true)
    @Mapping(target = "modalitesRecrutement", ignore = true)
    @Mapping(target = "modalitesPedagogiques", ignore = true)
    @Mapping(target = "modalitesEntreesSorties", ignore = true)
    @Mapping(target = "modalitesEnseignement", ignore = true)
    @Mapping(target = "modalitesAlternance", ignore = true)
    @Mapping(target = "langueFormation", ignore = true)
    @Mapping(target = "infosPerimetreRecrutement", ignore = true)
    @Mapping(target = "infoPublicVise", ignore = true)
    @Mapping(target = "hebergement", ignore = true)
    @Mapping(target = "fraisRestants", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "dureeIndicative", ignore = true)
    @Mapping(target = "dureeCycle", ignore = true)
    @Mapping(target = "dureeConventionnee", ignore = true)
    @Mapping(target = "detailConditionsPriseEnCharge", ignore = true)
    @Mapping(target = "dateInformation", ignore = true)
    @Mapping(target = "conventionnement", ignore = true)
    @Mapping(target = "conditionsSpecifiques", ignore = true)
    @Mapping(target = "codePublicVise", ignore = true)
    @Mapping(target = "codePerimetreRecrutement", ignore = true)
    @Mapping(target = "codeModalitePedagogique", ignore = true)
    @Mapping(target = "carifId", ignore = true)
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "accesHandicapes", ignore = true)
    public abstract ActionFormationDTO toActionFormationDTO(OnisepFormationDataset dataset);

    @Mapping(target = "niveauEntreeObligatoire", ignore = true)
    @Mapping(target = "urlAction", ignore = true)
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "rythmeFormation", ignore = true)
    @Mapping(target = "restauration", ignore = true)
    @Mapping(target = "prixTotalTTC", ignore = true)
    @Mapping(target = "prixHoraireTTC", ignore = true)
    @Mapping(target = "priseEnChargeFraisPossible", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true)
    @Mapping(target = "organismeFormateur", ignore = true)
    @Mapping(target = "organismeFinanceur", ignore = true)
    @Mapping(target = "onisepId", ignore = true)
    @Mapping(target = "nombreHeuresTotal", ignore = true)
    @Mapping(target = "nombreHeuresEntreprise", ignore = true)
    @Mapping(target = "nombreHeuresCentre", ignore = true)
    @Mapping(target = "modalitesRecrutement", ignore = true)
    @Mapping(target = "modalitesPedagogiques", ignore = true)
    @Mapping(target = "modalitesEntreesSorties", ignore = true)
    @Mapping(target = "modalitesEnseignement", ignore = true)
    @Mapping(target = "modalitesAlternance", ignore = true)
    @Mapping(target = "langueFormation", ignore = true)
    @Mapping(target = "infosPerimetreRecrutement", ignore = true)
    @Mapping(target = "infoPublicVise", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hebergement", ignore = true)
    @Mapping(target = "fraisRestants", ignore = true)
    @Mapping(target = "formationId", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "dureeIndicative", ignore = true)
    @Mapping(target = "dureeCycle", ignore = true)
    @Mapping(target = "dureeConventionnee", ignore = true)
    @Mapping(target = "detailConditionsPriseEnCharge", ignore = true)
    @Mapping(target = "dateInformation", ignore = true)
    @Mapping(target = "conventionnement", ignore = true)
    @Mapping(target = "codePublicVise", ignore = true)
    @Mapping(target = "codePerimetreRecrutement", ignore = true)
    @Mapping(target = "codeModalitePedagogique", ignore = true)
    @Mapping(target = "carifId", ignore = true)
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "accesHandicapes", ignore = true)
    public abstract ActionFormationDTO toActionFormationDTO(CarifFormationDataset dataset);

    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "organisme", ignore = true)
    @Mapping(target = "etablissement", ignore = true)
    @Mapping(target = "sources", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract FormationEntity toFormationEntity(FormationDTO dto);

    @Mapping(target = "formation", ignore = true) // Ne pas mapper
    @Mapping(target = "etablissement", ignore = true) // Ne pas mapper
    @Mapping(target = "session", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract ActionFormationEntity toActionFormationEntity(ActionFormationDTO dto);

    // TODO à tester
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true)
    @Mapping(target = "onisepId", ignore = true)
    @Mapping(target = "resultats", source = "resultatsAttendus.value")
    @Mapping(target = "objectif", source = "objectifFormation.value")
    @Mapping(target = "nom", source = "intituleFormation.value")
    @Mapping(target = "contenu", source = "contenuFormation.value")
    @Mapping(target = "certifiante", source = "certifiante.value")
    @Mapping(target = "parcoursDeFormation", source = "parcoursDeFormation.value")
    @Mapping(target = "codeNiveauEntree", source = "codeNiveauEntree.value")
    @Mapping(target = "codeNiveauSortie", source = "codeNiveauSortie.value")
    @Mapping(target = "identifiantModule", source = "identifiantModule.value")
    @Mapping(target = "positionnement", source = "positionnement.value")
    public abstract FormationDTO toFormationDTO2(FormationType formationType);

    public FormationDTO toFormationDTO(@NonNull FormationType formationType) {
        FormationDTO dto = new FormationDTO();

        String formationOnisepId = formationType.getNumero().split("[._]")[1];
        // UUID
        dto.setId(UUID.nameUUIDFromBytes(formationOnisepId.getBytes()));
        // Nom de la formation
        dto.setNom(formationType.getIntituleFormation().getValue());
        // Objectifs de la formation
        if (formationType.getObjectifFormation().getValue() != null && !"-".equals(formationType.getObjectifFormation().getValue()))
            dto.setObjectif(formationType.getObjectifFormation().getValue());
        // Résultats attendus lors de la formation
        if (formationType.getResultatsAttendus().getValue() != null && !formationType.getResultatsAttendus().getValue().contains("FOR."))
            dto.setResultats(formationType.getResultatsAttendus().getValue());
        // Contenu de la formation
        if (formationType.getContenuFormation().getValue() != null && !"-".equals(formationType.getContenuFormation().getValue()))
            dto.setContenu(formationType.getContenuFormation().getValue());
        // Foration certifiante (vrai diplome)
        dto.setCertifiante("1".equals(formationType.getCertifiante().getValue()));
        //
        dto.setParcoursDeFormation(Integer.parseInt(formationType.getParcoursDeFormation().getValue()));
        //
        dto.setCodeNiveauEntree(Integer.parseInt(formationType.getCodeNiveauEntree().getValue()));
        //
        dto.setCodeNiveauSortie(Integer.parseInt(formationType.getCodeNiveauSortie().getValue()));
        //
        if (formationType.getIdentifiantModule() != null)
            dto.setIdentifiantModule(formationType.getIdentifiantModule().getValue());
        //
        if (formationType.getPositionnement() != null)
            dto.setPositionnement(Integer.parseInt(formationType.getPositionnement().getValue()));

        dto.setOnisepId(Integer.parseInt(formationOnisepId));

        return dto;
    }

    public ActionFormationDTO toActionFormationDTO(ActionType actionType) {
        ActionFormationDTO dto = new ActionFormationDTO();
        String formationOnisepId = actionType.getNumero().split("[._]")[1];
        String actionOnisepId = actionType.getNumero().split("[._]")[3];

        dto.setId(UUID.nameUUIDFromBytes(actionOnisepId.getBytes()));
        dto.setRythmeFormation(actionType.getRythmeFormation().getValue());
        dto.setCodePublicVise(actionType.getCodePublicVise().getFirst().getValue());
        dto.setNiveauEntreeObligatoire("1".equals(actionType.getNiveauEntreeObligatoire().getValue()));

        if (actionType.getModalitesAlternance().getValue() != null && !"-".equals(actionType.getModalitesAlternance().getValue()))
            dto.setModalitesAlternance(actionType.getModalitesAlternance().getValue());

        dto.setModalitesEnseignement(Integer.parseInt(actionType.getModalitesEnseignement().getValue()));

        if (actionType.getConditionsSpecifiques().getValue() != null && !"-".equals(actionType.getConditionsSpecifiques().getValue()))
            dto.setConditionsSpecifiques(actionType.getConditionsSpecifiques().getValue());

        dto.setPriseEnChargeFraisPossible("1".equals(actionType.getPriseEnChargeFraisPossible().getValue()));

        if (actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation() != null)
            dto.setUai(actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation().getCodeUAI().getValue());

        dto.setModalitesEntreesSorties(Integer.parseInt(actionType.getModalitesEntreesSorties().getValue()));

        if (actionType.getUrlAction() != null && actionType.getUrlAction().getUrlweb().getFirst() != null)
            dto.setUrlAction(actionType.getUrlAction().getUrlweb().getFirst().getValue());

        if (actionType.getDureeCycle() != null)
            dto.setDureeCycle((int) actionType.getDureeCycle().getValue());

        if (actionType.getHebergement() != null)
            dto.setHebergement(actionType.getHebergement().getValue());

        if (actionType.getTransport() != null)
            dto.setTransport(actionType.getTransport().getValue());

        if (actionType.getAccesHandicapes() != null)
            dto.setAccesHandicapes(actionType.getAccesHandicapes().getValue());

        if (actionType.getDureeIndicative() != null)
            dto.setDureeIndicative(actionType.getDureeIndicative().getValue());

        if (actionType.getModalitesRecrutement() != null)
            dto.setModalitesRecrutement(actionType.getModalitesRecrutement().getValue());

        // TODO
        dto.setOnisepId(Integer.parseInt(actionOnisepId));
        dto.setFormationId(UUID.nameUUIDFromBytes(formationOnisepId.getBytes()));
        return dto;
    }

    public List<ActionFormationDTO> toActionFormationDTO(@NonNull FormationType formationType) {
        return formationType.getAction().stream()
                .map(this::toActionFormationDTO)
                .toList();
    }
}
