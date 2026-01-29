package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPSLycee2016Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "UAI")
    private String uai;

    //Académie
    //Code du département
    //Département
    //Nom de l'établissment
    //Code INSEE de la commune
    //Nom de la commune
    //Secteur
    //Type de lycée
    //IPS voie GT
    //IPS voie PRO
    //IPS Ensemble GT-PRO
    //Ecart-type de l'IPS voie GT
    //Ecart-type de l'IPS voie PRO

    @CsvBindByName(column = "IPS Ensemble GT-PRO")
    private Double indice;

    @CsvBindByName(column = "Ecart-type de l'IPS voie GT")
    private Double ecartType;

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
