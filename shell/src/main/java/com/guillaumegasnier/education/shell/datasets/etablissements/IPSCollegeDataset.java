package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Code région
 * Région académique
 * Code académie
 * Académie
 * Code du département
 * Département
 * Code INSEE de la commune
 * Nom de la commune
 * Secteur
 * <p>
 * IPS national privé
 * IPS national public
 * IPS national
 * <p>
 * IPS académique privé
 * IPS académique public
 * IPS académique
 * <p>
 * IPS départemental privé
 * IPS départemental public
 * IPS départemental
 */
@Getter
@Setter
@ToString
public class IPSCollegeDataset extends IPSDataset {

    @CsvBindByName(column = "UAI")
    protected String uai;
    @CsvBindByName(column = "Rentrée scolaire")
    protected String rentreeScolaire;
    @CsvBindByName(column = "IPS")
    protected Double indice;
    @CsvBindByName(column = "Ecart type de l'IPS")
    protected Double ecartType;

    // National
    @CsvBindByName(column = "IPS national")
    protected Double indiceNational;
    @CsvBindByName(column = "IPS national privé")
    protected Double indiceNationalPrive;
    @CsvBindByName(column = "IPS national public")
    protected Double indiceNationalPublic;

    // Academie
    @CsvBindByName(column = "Code académie")
    protected String codeAcademie;
    @CsvBindByName(column = "IPS académique")
    protected Double indiceAcademie;
    @CsvBindByName(column = "IPS académique privé")
    protected Double indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique public")
    protected Double indiceAcademiePublic;

    // Departement
    @CsvBindByName(column = "Code du département")
    protected String codeDepartement;
    @CsvBindByName(column = "IPS départemental")
    protected Double indiceDepartement;
    @CsvBindByName(column = "IPS départemental privé")
    protected Double indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental public")
    protected Double indiceDepartementPublic;

}
