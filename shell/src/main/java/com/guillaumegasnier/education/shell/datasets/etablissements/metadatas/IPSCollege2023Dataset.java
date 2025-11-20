package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Indices de position sociale des collèges (à partir de 2023)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/ips-colleges-a-partir-de-2023/">Référence</a>
 */
@Getter
@Setter
public class IPSCollege2023Dataset implements IndicePositionSociale, Metadata {

    //num_ligne
    //Région académique
    //Académie
    //Département
    //Code INSEE de la commune
    //Nom de la commune
    //Nom de l'établissement
    //Secteur

    @CsvBindByName(column = "Code région")
    private String codeRegion;

    @CsvBindByName(column = "Code académie")
    private String codeAcademie;

    @CsvBindByName(column = "Code du département")
    private String codeDepartement;

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "IPS")
    private Double indice;

    @CsvBindByName(column = "Ecart type de l'IPS")
    private Double ecartType;

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }

    //IPS national privé
    //IPS national public
    //IPS national
    //IPS académique privé
    //IPS académique public
    //IPS académique
    //IPS départemental privé
    //IPS départemental public
    //IPS départemental
}
