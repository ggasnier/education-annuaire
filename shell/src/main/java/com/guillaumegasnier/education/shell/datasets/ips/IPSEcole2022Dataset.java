package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * num_ligne
 * Rentrée scolaire
 * Code région
 * Région
 * Code de l'académie
 * Académie
 * Code du département
 * Département
 * Code INSEE de la commune
 * Nom de la commune
 * UAI
 * Nom de l'établissement
 * Secteur
 * IPS
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
public class IPSEcole2022Dataset implements IPSDataset {

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
