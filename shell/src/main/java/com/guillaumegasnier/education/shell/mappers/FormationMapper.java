package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import com.guillaumegasnier.education.shell.datasets.CODESROME;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class FormationMapper {

    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "etablissement", ignore = true) // TODO
    @Mapping(target = "onisepId", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "codeNiveauSortie", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    //@Mapping(target = "certifications", ignore = true)
    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract FormationEntity toFormationEntity(CPFFormationDataset dataset);

    @Mapping(target = "onisepId", source = "dataset.formationOnisepId")
    @Mapping(target = "nom", source = "dataset.formationNom")
    @Mapping(target = "id", source = "dataset.formationId")
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    //@Mapping(target = "certifications", ignore = true)
    @Mapping(target = "certifiante", source = "dataset.certifiante")
    @Mapping(target = "actions", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    public abstract FormationEntity toFormationEntity(OnisepFormationDataset dataset, EtablissementEntity etablissement);

    @Mapping(target = "id", source = "formationId")
    @Mapping(target = "onisepId", source = "formationOnisepId")
    @Mapping(target = "nom", source = "formationNom")
    @Mapping(target = "parcoursDeFormation", constant = "1")
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "etablissement", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    //@Mapping(target = "certifications", ignore = true) // Ne pas mapper
    @Mapping(target = "actions", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    public abstract FormationEntity toFormationEntity(OnisepFormationDataset dataset);

    @Mapping(target = "urlAction", ignore = true)
    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "rythmeFormation", ignore = true)
    @Mapping(target = "restauration", ignore = true)
    @Mapping(target = "prixTotalTTC", ignore = true)
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
    @Mapping(target = "adresseInformation", ignore = true)
    @Mapping(target = "accesHandicapes", ignore = true)
    public abstract ActionFormationEntity toActionFormationEntity(OnisepFormationDataset dataset, FormationEntity formation);

    @Mapping(target = "nom", source = "LIBELLE")
    @Mapping(target = "code", source = "CODE")
    //@Mapping(target = "certifications", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract RomeEntity toRomeEntity(CODESROME.ROME rome);

    public abstract FormationEntity toFormationEntity(ParcoursupFormationDataset parcoursupFormationDataset);
}
