package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPSEREA2016Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    //Académie
    //Code du département
    //Département

    @CsvBindByName(column = "UAI")
    private String uai;

    //Nom de l'établissment
    //Code INSEE de la commune
    //Nom de la commune
    //Secteur

    @CsvBindByName(column = "IPS")
    private Double indice;
    @CsvBindByName(column = "Ecart-type de l'IPS")
    private Double ecartType;

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
