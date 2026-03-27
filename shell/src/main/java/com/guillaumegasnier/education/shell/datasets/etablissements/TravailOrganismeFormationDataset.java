package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatAdresse;

@Getter
@Setter
public class TravailOrganismeFormationDataset implements Dataset {

    @CsvBindByName(column = "numeroDeclarationActivite")
    private String numeroDeclarationActivite;

    //numerosDeclarationActivitePrecedent

    @CsvBindByName(column = "denomination")
    private String nom;

    @CsvBindByName(column = "siren")
    private String siren;

    @CsvBindByName(column = "siretEtablissementDeclarant")
    private String siret;

    @CsvBindByName(column = "adressePhysiqueOrganismeFormation.voie")
    private String adresse;
    @CsvBindByName(column = "adressePhysiqueOrganismeFormation.codePostal")
    private String codePostal;
    @CsvBindByName(column = "adressePhysiqueOrganismeFormation.ville")
    private String nomCommune;
    @CsvBindByName(column = "adressePhysiqueOrganismeFormation.codeRegion")
    private String codeRegion;
    @CsvBindByName(column = "certifications.actionsDeFormation")
    private Boolean actionsDeFormation;
    @CsvBindByName(column = "certifications.bilansDeCompetences")
    private Boolean bilansDeCompetences;
    @CsvBindByName(column = "certifications.VAE")
    private Boolean validationAcquisExperience;
    @CsvBindByName(column = "certifications.actionsDeFormationParApprentissage")
    private Boolean actionsDeFormationParApprentissage;
    @CsvBindByName(column = "informationsDeclarees.dateDerniereDeclaration")
    private String dateDerniereDeclaration; // Format JJ/MM/AAAA

    //organismeEtrangerRepresente.denomination
    //organismeEtrangerRepresente.voie
    //organismeEtrangerRepresente.codePostal
    //organismeEtrangerRepresente.ville
    //organismeEtrangerRepresente.pays
    @CsvBindByName(column = "informationsDeclarees.debutExercice")
    private String dateDebut; // Format JJ/MM/AAAA
    @CsvBindByName(column = "informationsDeclarees.finExercice")
    private String dateFin; // Format JJ/MM/AAAA
    @CsvBindByName(column = "informationsDeclarees.specialitesDeFormation.codeSpecialite1")
    private String codeSpecialite1;
    @CsvBindByName(column = "informationsDeclarees.specialitesDeFormation.codeSpecialite2")
    private String codeSpecialite2;
    @CsvBindByName(column = "informationsDeclarees.specialitesDeFormation.codeSpecialite3")
    private String codeSpecialite3;

    public String getAdresse() {
        return formatAdresse(adresse);
    }

    //informationsDeclarees.specialitesDeFormation.libelleSpecialite1
    //informationsDeclarees.specialitesDeFormation.libelleSpecialite2
    //informationsDeclarees.specialitesDeFormation.libelleSpecialite3

    //informationsDeclarees.nbStagiaires
    //informationsDeclarees.nbStagiairesConfiesParUnAutreOF
    //informationsDeclarees.effectifFormateurs
}
