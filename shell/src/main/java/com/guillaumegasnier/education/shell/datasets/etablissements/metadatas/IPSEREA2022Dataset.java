package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPSEREA2022Dataset implements IndicePositionSociale, Metadata {


    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    //Code région
    //Code région Insee
    //Libellé région académique
    //Code académie
    //Libellé académie
    //Code département
    //Libellé département
    //Code commune Insee
    //Libellé commune
    @CsvBindByName(column = "UAI")
    private String uai;
    //Nom de l'établissement
    //Secteur

    @CsvBindByName(column = "IPS")
    private Double indice;
    @CsvBindByName(column = "Ecart type de l'IPS")
    private Double ecartType;

    @CsvBindByName(column = "IPS national privé")
    private Double indiceNationalPrive;
    @CsvBindByName(column = "IPS national public")
    private Double indiceNationalPublic;
    @CsvBindByName(column = "IPS national")
    private Double indiceNational;

    @CsvBindByName(column = "IPS académique privé")
    private Double indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique public")
    private Double indiceAcademiePublic;
    @CsvBindByName(column = "IPS académique")
    private Double indiceAcademie;

    @CsvBindByName(column = "IPS départemental privé")
    private Double indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental public")
    private Double indiceDepartementPublic;
    @CsvBindByName(column = "IPS départemental")
    private Double indiceDepartement;

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
