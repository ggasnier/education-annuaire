package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPSEcole2016Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;
    @CsvBindByName(column = "UAI")
    private String uai;
    @CsvBindByName(column = "IPS")
    private Double indice;

    @Override
    public Double getEcartType() {
        return null;
    }

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
