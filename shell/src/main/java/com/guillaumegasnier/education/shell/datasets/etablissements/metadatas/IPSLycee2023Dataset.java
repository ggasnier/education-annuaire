package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Indices de position sociale des lycées (à partir de 2023)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/ips-lycees-a-partir-de-2023/">Référence</a>
 */
@Getter
@Setter
public class IPSLycee2023Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "UAI")
    private String uai;

    /**
     * LEGT
     * LP
     * LPO
     */
    @CsvBindByName(column = "Type de lycée")
    private String typeLycee;

    //IPS voie GT
    //IPS voie PRO
    //IPS post BAC

    @CsvBindByName(column = "IPS de l'établissement")
    private Double indice;


    //Ecart type voie GT
    //Ecart type voie PRO

    @CsvBindByName(column = "Ecart-type établissement")
    private Double ecartType;

    //IPS national LEGT
    //IPS national LEGT privé
    //IPS national LEGT public
    //IPS académique LEGT
    //IPS académique LEGT privé
    //IPS académique LEGT public
    //IPS départemental LEGT
    //IPS départemental LEGT privé
    //IPS départemental LEGT public

    //IPS national LPO
    //IPS national LPO privé
    //IPS national LPO public
    //IPS académique LPO
    //IPS académique LPO privé
    //IPS académique LPO public
    //IPS départemental LPO
    //IPS départemental LPO privé
    //IPS départemental LPO public

    //IPS national LP
    //IPS national LP privé
    //IPS national LP public
    //IPS académique LP
    //IPS académique LP privé
    //IPS académique LP public
    //IPS départemental LP
    //IPS départemental LP privé
    //IPS départemental LP public

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
