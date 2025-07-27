package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 01 Rentrée scolaire
 * 02 Académie
 * 03 Code du département
 * 04 Département
 * 05 UAI
 * 06 Nom de l'établissment
 * 07 Code INSEE de la commune
 * 08 Nom de la commune
 * 09 Secteur
 * 10 IPS
 * 11 Ecart-type de l'IPS
 */
@Getter
@Setter
@ToString
public class IPSCollege2021Dataset extends IPSDataset {

    protected String categorie = "C";

    @CsvBindByName(column = "Secteur")
    protected String nomSecteur;

    @CsvBindByName(column = "Ecart-type de l'IPS")
    protected String ecartType;

    @CsvBindByName(column = "Code du département")
    protected String codeDepartement;

    @CsvBindByName(column = "Code INSEE de la commune")
    protected String codeCommune;

    public Double getEcartType() {
        if (ecartType == null || ecartType.isBlank()) return null;
        if (ecartType.equals("NA") || ecartType.equals("NS")) return null;
        return Double.parseDouble(ecartType);
    }
}
