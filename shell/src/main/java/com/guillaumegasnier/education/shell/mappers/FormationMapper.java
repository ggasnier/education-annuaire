package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.shell.datasets.ActionType;
import com.guillaumegasnier.education.shell.datasets.FormationType;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import com.guillaumegasnier.education.shell.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.util.List;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.toNormalizedId;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class FormationMapper {

    @Mapping(target = "carifId", ignore = true)
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
    @Mapping(target = "onisepId", source = "formationOnisepId")
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    public abstract FormationDTO toFormationDTO(OnisepFormationDataset dataset);


    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "restauration", ignore = true)
    @Mapping(target = "prixHoraireTTC", ignore = true)
    @Mapping(target = "priseEnChargeFraisPossible", ignore = true)
    @Mapping(target = "organismeFormateur", ignore = true)
    @Mapping(target = "organismeFinanceur", ignore = true)
    @Mapping(target = "nombreHeuresTotal", ignore = true)
    @Mapping(target = "nombreHeuresEntreprise", ignore = true)
    @Mapping(target = "nombreHeuresCentre", ignore = true)
    @Mapping(target = "niveauEntreeObligatoire", ignore = true)
    @Mapping(target = "modalitesRecrutement", ignore = true)
    @Mapping(target = "modalitesPedagogiques", ignore = true)
    @Mapping(target = "modalitesEntreesSorties", ignore = true)
    @Mapping(target = "modalitesAlternance", ignore = true)
    @Mapping(target = "langueFormation", ignore = true)
    @Mapping(target = "infosPerimetreRecrutement", ignore = true)
    @Mapping(target = "infoPublicVise", ignore = true)
    @Mapping(target = "fraisRestants", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "dureeIndicative", ignore = true)
    @Mapping(target = "dureeConventionnee", ignore = true)
    @Mapping(target = "detailConditionsPriseEnCharge", ignore = true)
    @Mapping(target = "dateInformation", ignore = true)
    @Mapping(target = "conventionnement", ignore = true)
    @Mapping(target = "conditionsSpecifiques", ignore = true)
    @Mapping(target = "codePublicVise", ignore = true)
    @Mapping(target = "codePerimetreRecrutement", ignore = true)
    @Mapping(target = "codeModalitePedagogique", ignore = true)
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "uai", source = "etablissementUai")
    @Mapping(target = "rythmeFormation", source = "actionFormationModalitesScolarite")
    @Mapping(target = "onisepId", source = "actionFormationOnisepId")
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    public abstract ActionFormationDTO toActionFormationDTO(OnisepFormationDataset dataset);

    @Mapping(target = "actionFormationId", ignore = true)
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

    @Mapping(target = "id", source = "formationId")
    @Mapping(target = "actions", ignore = true) // Ne pas mapper
    @Mapping(target = "organisme", ignore = true) // Ne pas mapper
    @Mapping(target = "etablissement", ignore = true) // Ne pas mapper
    @Mapping(target = "sources", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract FormationEntity toFormationEntity(FormationDTO dto);

    @Mapping(target = "id", source = "actionFormationId")
    @Mapping(target = "sources", ignore = true) // Ne pas mapper
    @Mapping(target = "formation", ignore = true) // Ne pas mapper
    @Mapping(target = "etablissement", ignore = true) // Ne pas mapper
    @Mapping(target = "session", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract ActionFormationEntity toActionFormationEntity(ActionFormationDTO dto);

    @Named("toFormationId")
    public Long toFormationId(@NonNull FormationType formationType) {
        return toNormalizedId("FOR", formationType.getNumero().split("[._]")[1]);
    }

    @Named("toActionFormationId")
    public Long toActionFormationId(@NonNull ActionType actionType) {
        return toNormalizedId("AF", actionType.getNumero().split("[._]")[3]);
    }

    @Named("toFormationOnisepId")
    public Integer toFormationOnisepId(@NonNull FormationType formationType) {
        return Integer.parseInt(formationType.getNumero().split("[._]")[1]);
    }

    // TODO à tester
    @Mapping(target = "formationId", source = "formationType", qualifiedByName = "toFormationId")
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "onisepId", source = "formationType", qualifiedByName = "toFormationOnisepId")
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
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    public abstract FormationDTO toFormationDTO(FormationType formationType);

    @Mapping(target = "actionFormationId", source = "actionType", qualifiedByName = "toActionFormationId")
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "onisepId", ignore = true) // TODO
    @Mapping(target = "formationId", ignore = true) // TODO
    @Mapping(target = "codePublicVise", ignore = true)
    @Mapping(target = "rythmeFormation", source = "rythmeFormation.value")
    @Mapping(target = "infoPublicVise", source = "infoPublicVise.value")
    @Mapping(target = "niveauEntreeObligatoire", source = "niveauEntreeObligatoire.value")
    @Mapping(target = "modalitesAlternance", source = "modalitesAlternance.value")
    @Mapping(target = "modalitesEnseignement", source = "modalitesEnseignement.value")
    @Mapping(target = "conditionsSpecifiques", source = "conditionsSpecifiques.value")
    @Mapping(target = "priseEnChargeFraisPossible", source = "priseEnChargeFraisPossible.value")
    @Mapping(target = "modalitesEntreesSorties", source = "modalitesEntreesSorties.value")
    @Mapping(target = "restauration", source = "restauration.value")
    @Mapping(target = "hebergement", source = "hebergement.value")
    @Mapping(target = "transport", source = "transport.value")
    @Mapping(target = "accesHandicapes", source = "accesHandicapes.value")
    @Mapping(target = "dureeCycle", source = "dureeCycle.value")
    @Mapping(target = "modalitesRecrutement", source = "modalitesRecrutement.value")
    @Mapping(target = "modalitesPedagogiques", source = "modalitesPedagogiques.value")
    @Mapping(target = "fraisRestants", source = "fraisRestants.value")
    @Mapping(target = "codePerimetreRecrutement", source = "codePerimetreRecrutement.value")
    @Mapping(target = "infosPerimetreRecrutement", source = "infosPerimetreRecrutement.value")
    @Mapping(target = "prixHoraireTTC", source = "prixHoraireTTC.value")
    @Mapping(target = "prixTotalTTC", source = "prixTotalTTC.value")
    @Mapping(target = "dureeIndicative", source = "dureeIndicative.value")
    @Mapping(target = "nombreHeuresCentre", source = "nombreHeuresCentre.value")
    @Mapping(target = "nombreHeuresEntreprise", source = "nombreHeuresEntreprise.value")
    @Mapping(target = "nombreHeuresTotal", source = "nombreHeuresTotal.value")
    @Mapping(target = "detailConditionsPriseEnCharge", source = "detailConditionsPriseEnCharge.value")
    @Mapping(target = "conventionnement", source = "conventionnement.value")
    @Mapping(target = "dureeConventionnee", source = "dureeConventionnee.value")
    @Mapping(target = "dateInformation", ignore = true)
    @Mapping(target = "langueFormation", ignore = true)
    @Mapping(target = "urlAction", ignore = true)
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "codeModalitePedagogique", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "organismeFormateur", ignore = true)
    @Mapping(target = "organismeFinanceur", ignore = true)
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    public abstract ActionFormationDTO toActionFormationDTO(ActionType actionType);

//    /**
//     * @param formationType
//     * @return
//     * @deprecated voir toFormationDTO2
//     */
//    @Deprecated
//    public FormationDTO toFormationDTO(@NonNull FormationType formationType) {
//        FormationDTO dto = new FormationDTO();
//
//        String formationOnisepId = formationType.getNumero().split("[._]")[1];
//        // UUID
//        dto.setId(UUID.nameUUIDFromBytes(("FOR" + formationOnisepId).getBytes()));
//        // Nom de la formation
//        dto.setNom(formationType.getIntituleFormation().getValue());
//        // Objectifs de la formation
//        if (formationType.getObjectifFormation().getValue() != null && !"-".equals(formationType.getObjectifFormation().getValue()))
//            dto.setObjectif(formationType.getObjectifFormation().getValue());
//        // Résultats attendus lors de la formation
//        if (formationType.getResultatsAttendus().getValue() != null && !formationType.getResultatsAttendus().getValue().contains("FOR."))
//            dto.setResultats(formationType.getResultatsAttendus().getValue());
//        // Contenu de la formation
//        if (formationType.getContenuFormation().getValue() != null && !"-".equals(formationType.getContenuFormation().getValue()))
//            dto.setContenu(formationType.getContenuFormation().getValue());
//        // Foration certifiante (vrai diplome)
//        dto.setCertifiante("1".equals(formationType.getCertifiante().getValue()));
//        //
//        dto.setParcoursDeFormation(Integer.parseInt(formationType.getParcoursDeFormation().getValue()));
//        //
//        dto.setCodeNiveauEntree(Integer.parseInt(formationType.getCodeNiveauEntree().getValue()));
//        //
//        dto.setCodeNiveauSortie(Integer.parseInt(formationType.getCodeNiveauSortie().getValue()));
//        //
//        if (formationType.getIdentifiantModule() != null)
//            dto.setIdentifiantModule(formationType.getIdentifiantModule().getValue());
//        //
//        if (formationType.getPositionnement() != null)
//            dto.setPositionnement(Integer.parseInt(formationType.getPositionnement().getValue()));
//
//        dto.setOnisepId(Integer.parseInt(formationOnisepId));
//
//        return dto;
//    }

//    public ActionFormationDTO toActionFormationDTO(ActionType actionType) {
//        ActionFormationDTO dto = new ActionFormationDTO();
//        String formationOnisepId = actionType.getNumero().split("[._]")[1];
//        String actionOnisepId = actionType.getNumero().split("[._]")[3];
//
//        dto.setId(UUID.nameUUIDFromBytes(actionOnisepId.getBytes()));
//        dto.setRythmeFormation(actionType.getRythmeFormation().getValue());
//        dto.setCodePublicVise(actionType.getCodePublicVise().getFirst().getValue());
//        dto.setNiveauEntreeObligatoire("1".equals(actionType.getNiveauEntreeObligatoire().getValue()));
//
//        if (actionType.getModalitesAlternance().getValue() != null && !"-".equals(actionType.getModalitesAlternance().getValue()))
//            dto.setModalitesAlternance(actionType.getModalitesAlternance().getValue());
//
//        dto.setModalitesEnseignement(Integer.parseInt(actionType.getModalitesEnseignement().getValue()));
//
//        if (actionType.getConditionsSpecifiques().getValue() != null && !"-".equals(actionType.getConditionsSpecifiques().getValue()))
//            dto.setConditionsSpecifiques(actionType.getConditionsSpecifiques().getValue());
//
//        dto.setPriseEnChargeFraisPossible("1".equals(actionType.getPriseEnChargeFraisPossible().getValue()));
//
//        if (actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation() != null)
//            dto.setUai(actionType.getLieuDeFormation().getFirst().getCodeUAILieuFormation().getCodeUAI().getValue());
//
//        dto.setModalitesEntreesSorties(Integer.parseInt(actionType.getModalitesEntreesSorties().getValue()));
//
//        if (actionType.getUrlAction() != null && actionType.getUrlAction().getUrlweb().getFirst() != null)
//            dto.setUrlAction(actionType.getUrlAction().getUrlweb().getFirst().getValue());
//
//        if (actionType.getDureeCycle() != null)
//            dto.setDureeCycle((int) actionType.getDureeCycle().getValue());
//
//        if (actionType.getHebergement() != null)
//            dto.setHebergement(actionType.getHebergement().getValue());
//
//        if (actionType.getTransport() != null)
//            dto.setTransport(actionType.getTransport().getValue());
//
//        if (actionType.getAccesHandicapes() != null)
//            dto.setAccesHandicapes(actionType.getAccesHandicapes().getValue());
//
//        if (actionType.getDureeIndicative() != null)
//            dto.setDureeIndicative(actionType.getDureeIndicative().getValue());
//
//        if (actionType.getModalitesRecrutement() != null)
//            dto.setModalitesRecrutement(actionType.getModalitesRecrutement().getValue());
//
//        // TODO
//        dto.setOnisepId(Integer.parseInt(actionOnisepId));
//        dto.setFormationId(UUID.nameUUIDFromBytes(formationOnisepId.getBytes()));
//        return dto;
//    }

    public List<ActionFormationDTO> toActionFormationDTO(@NonNull FormationType formationType) {
        return formationType.getAction().stream()
                .map(this::toActionFormationDTO)
                .toList();
    }

    @Mapping(target = "carifId", ignore = true)
    @Mapping(target = "nom", source = "nomFormation")
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "certifiante", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    @Mapping(target = "codeNiveauSortie", ignore = true)
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursupId", source = "codeFormation")
    @Mapping(target = "onisepId", ignore = true)
    public abstract FormationDTO toFormationDTO(ParcoursupFormationDataset dataset);
}
