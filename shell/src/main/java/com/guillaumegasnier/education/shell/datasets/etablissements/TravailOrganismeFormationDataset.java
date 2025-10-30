package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class TravailOrganismeFormationDataset implements EtablissementDataset {

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

    @CsvBindByName(column = "organismeEtrangerRepresente.pays")
    private String nomPays;

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        return null;
    }
    
    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        return null;
    }

    @Override
    public UUID getId() {
        return UUID.nameUUIDFromBytes(siret.getBytes());
    }

    @Override
    public String getCodeCommune() {
        return null;
    }

    //certifications.actionsDeFormation
    //certifications.bilansDeCompetences
    //certifications.VAE
    //certifications.actionsDeFormationParApprentissage

    //organismeEtrangerRepresente.denomination

    //informationsDeclarees.dateDerniereDeclaration
    //informationsDeclarees.debutExercice
    //informationsDeclarees.finExercice
    //informationsDeclarees.specialitesDeFormation.codeSpecialite1
    //informationsDeclarees.specialitesDeFormation.libelleSpecialite1
    //informationsDeclarees.specialitesDeFormation.codeSpecialite2
    //informationsDeclarees.specialitesDeFormation.libelleSpecialite2
    //informationsDeclarees.specialitesDeFormation.codeSpecialite3
    //informationsDeclarees.specialitesDeFormation.libelleSpecialite3
    //informationsDeclarees.nbStagiaires
    //informationsDeclarees.nbStagiairesConfiesParUnAutreOF
    //informationsDeclarees.effectifFormateurs
}
