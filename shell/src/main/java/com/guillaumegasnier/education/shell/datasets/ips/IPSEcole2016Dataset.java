package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

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
 * IPS
 */
@Getter
@Setter
public class IPSEcole2016Dataset implements IPSDataset {

    private String categorie = "E";

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "IPS")
    private String indice;

    private Double ecartType;

    @Override
    public final int getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }

    @Override
    public Double getIndice() {
        if (indice == null || indice.isBlank()) return null;
        if (indice.equals("NA") || indice.equals("NS")) return null;
        return Double.parseDouble(indice);
    }
}
