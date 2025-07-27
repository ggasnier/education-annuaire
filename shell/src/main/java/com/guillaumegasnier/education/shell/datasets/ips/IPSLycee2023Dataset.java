package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class IPSLycee2023Dataset extends IPSLycee2022Dataset {

    @CsvBindByName(column = "IPS de l'établissement")
    protected String indice;

    @CsvBindByName(column = "Ecart-type établissement")
    protected String ecartType;

    public Double getIndice() {
        if (indice == null) return null;
        if (indice.equals("NA") || indice.equals("NS")) return null;
        return Double.parseDouble(indice);
    }

    public Double getEcartType() {
        if (ecartType == null || ecartType.isBlank()) return null;
        if (ecartType.equals("NA") || ecartType.equals("NS")) return null;
        return Double.parseDouble(ecartType);
    }
}
