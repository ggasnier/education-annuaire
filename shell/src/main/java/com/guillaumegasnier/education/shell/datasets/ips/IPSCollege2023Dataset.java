package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * num_ligne
 * 01 Rentrée scolaire
 * 02 Académie
 * 03 Code du département
 * 04 Département
 * 05 UAI
 * 06 Nom de l'établissement
 * 07 Code INSEE de la commune
 * 08 Nom de la commune
 * 09 Secteur
 * 10 IPS
 * 11 Ecart type de l'IPS (nom de colonne changé)
 * 12 Effectifs (supprimé)
 * Code région
 * Région académique
 * Code académie
 * IPS national privé
 * IPS national public
 * IPS national
 * IPS académique privé
 * IPS académique public
 * IPS académique
 * IPS départemental privé
 * IPS départemental public
 * IPS départemental
 */
@Getter
@Setter
@ToString
public class IPSCollege2023Dataset extends IPSCollege2022Dataset {

    @CsvBindByName(column = "Ecart type de l'IPS")
    protected String ecartType;
    // National
    @CsvBindByName(column = "IPS national")
    protected String indiceNational;
    @CsvBindByName(column = "IPS national privé")
    protected String indiceNationalPrive;
    @CsvBindByName(column = "IPS national public")
    protected String indiceNationalPublic;
    // Academie
    @CsvBindByName(column = "Code académie")
    protected String codeAcademie;
    @CsvBindByName(column = "IPS académique")
    protected String indiceAcademie;
    @CsvBindByName(column = "IPS académique privé")
    protected String indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique public")
    protected String indiceAcademiePublic;
    // Departement
    @CsvBindByName(column = "Code du département")
    protected String codeDepartement;
    @CsvBindByName(column = "IPS départemental")
    protected String indiceDepartement;
    @CsvBindByName(column = "IPS départemental privé")
    protected String indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental public")
    protected String indiceDepartementPublic;

    public Double getEcartType() {
        if (ecartType == null || ecartType.isBlank()) return null;
        if (ecartType.equals("NA") || ecartType.equals("NS")) return null;
        return Double.parseDouble(ecartType);
    }

}
