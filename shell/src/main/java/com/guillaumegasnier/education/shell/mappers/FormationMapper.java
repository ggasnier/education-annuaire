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

    // Vers les entitées
    @Mapping(target = "id", source = "formationId")
    @Mapping(target = "certification", ignore = true)
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

    // Partie Carif-Oref
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

    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "niveauEntreeObligatoire", ignore = true)
    @Mapping(target = "actionFormationId", ignore = true)
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

    // Partie Onisep
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
    @Mapping(target = "hebergement", ignore = true)
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

    @Named("toFormationId")
    public Long toFormationId(@NonNull FormationType formationType) {
        return toNormalizedId("FOR", formationType.getNumero().split("[._]")[1]);
    }

    @Named("toFormationOnisepId")
    public Integer toFormationOnisepId(@NonNull FormationType formationType) {
        return Integer.parseInt(formationType.getNumero().split("[._]")[1]);
    }

    @Named("toFormationId2")
    public Long toFormationId2(@NonNull ActionType actionType) {
        return toNormalizedId("FOR", actionType.getNumero().split("[._]")[1]);
    }

    @Named("toActionFormationId")
    public Long toActionFormationId(@NonNull ActionType actionType) {
        return toNormalizedId("AF", actionType.getNumero().split("[._]")[3]);
    }

    @Named("toActionFormationOnisepId")
    public Integer toActionFormationOnisepId(@NonNull ActionType actionType) {
        return Integer.parseInt(actionType.getNumero().split("[._]")[3]);
    }

    // Partie LHEO

    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "onisepId", ignore = true)
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "nom", ignore = true)
    @Mapping(target = "nda", ignore = true)
    @Mapping(target = "formationId", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "certifiante", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    @Mapping(target = "codeNiveauSortie", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    public abstract FormationDTO toFormationDTO(FormationType formationType);

    @Mapping(target = "dateInformation", ignore = true)
    @Mapping(target = "langueFormation", ignore = true)
    @Mapping(target = "urlAction", ignore = true)
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "codeModalitePedagogique", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "organismeFormateur", ignore = true)
    @Mapping(target = "organismeFinanceur", ignore = true)
    @Mapping(target = "uai", ignore = true)
    @Mapping(target = "codePublicVise", ignore = true)
    @Mapping(target = "actionFormationId", source = "actionType", qualifiedByName = "toActionFormationId")
    @Mapping(target = "formationId", source = "actionType", qualifiedByName = "toFormationId2")
    @Mapping(target = "onisepId", source = "actionType", qualifiedByName = "toActionFormationOnisepId")
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
    @Mapping(target = "carifId", ignore = true) // Ne pas mapper
    @Mapping(target = "parcoursupId", ignore = true) // Ne pas mapper
    public abstract ActionFormationDTO toActionFormationDTO(ActionType actionType);

    public List<ActionFormationDTO> toActionFormationDTO(@NonNull FormationType formationType) {
        return formationType.getAction().stream()
                .map(this::toActionFormationDTO)
                .toList();
    }

    // Partie Parcoursup
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
    @Mapping(target = "actionFormationId", ignore = true)
    @Mapping(target = "accesHandicapes", ignore = true)
    public abstract ActionFormationDTO toActionFormationDTO(ParcoursupFormationDataset dataset);
}
