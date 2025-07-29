package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Rentrée scolaire
 * Académie
 * Code du département
 * Département
 * UAI
 * Nom de l'établissment
 * Code INSEE de la commune
 * Nom de la commune
 * Secteur
 * Type de lycée
 * IPS voie GT
 * IPS voie PRO
 * IPS Ensemble GT-PRO
 * Ecart-type de l'IPS voie GT
 * Ecart-type de l'IPS voie PRO
 */
@Getter
@Setter
@ToString
public class IPSLycee2021Dataset extends IPSDataset {

    protected String categorie = "L";

    @CsvBindByName(column = "IPS Ensemble GT-PRO")
    protected String indice;

    public Double getIndice() {
        if (indice == null) return null;
        if (indice.equals("NA") || indice.equals("NS")) return null;
        return Double.parseDouble(indice);
    }

}
