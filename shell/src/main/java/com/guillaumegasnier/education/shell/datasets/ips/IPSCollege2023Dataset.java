package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

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
 * 11 Ecart type de l'IPS
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
public class IPSCollege2023Dataset implements IPSDataset {

    private String categorie = "C";

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "IPS")
    private String indice;

    @CsvBindByName(column = "Ecart type de l'IPS")
    private String ecartType;

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

    @Override
    public Double getEcartType() {
        if (ecartType == null || ecartType.isBlank()) return null;
        if (ecartType.equals("NA") || ecartType.equals("NS")) return null;
        return Double.parseDouble(ecartType);
    }

}
