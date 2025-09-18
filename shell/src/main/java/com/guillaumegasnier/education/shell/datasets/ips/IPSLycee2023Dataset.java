package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * num_ligne
 * Rentrée scolaire
 * Code région
 * Région académique
 * Code académie
 * Académie
 * Code du département
 * Département
 * Code INSEE de la commune
 * Nom de la commune
 * UAI
 * Nom de l'établissement
 * Secteur
 * Type de lycée
 * IPS voie GT
 * IPS voie PRO
 * IPS post BAC
 * IPS de l'établissement
 * Ecart type voie GT
 * Ecart type voie PRO
 * Ecart-type établissement
 * IPS national LEGT
 * IPS national LEGT privé
 * IPS national LEGT public
 * IPS national LPO
 * IPS national LPO privé
 * IPS national LPO public
 * IPS national LP
 * IPS national LP privé
 * IPS national LP public
 * IPS académique LEGT
 * IPS académique LEGT privé
 * IPS académique LEGT public
 * IPS académique LPO
 * IPS académique LPO privé
 * IPS académique LPO public
 * IPS académique LP
 * IPS académique LP privé
 * IPS académique LP public
 * IPS départemental LEGT
 * IPS départemental LEGT privé
 * IPS départemental LEGT public
 * IPS départemental LPO
 * IPS départemental LPO privé
 * IPS départemental LPO public
 * IPS départemental LP
 * IPS départemental LP privé
 * IPS départemental LP public
 */
@Getter
@Setter
public class IPSLycee2023Dataset implements IPSDataset {

    private String categorie = "L";

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "IPS de l'établissement")
    private String indice;

    @CsvBindByName(column = "Ecart-type établissement")
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
