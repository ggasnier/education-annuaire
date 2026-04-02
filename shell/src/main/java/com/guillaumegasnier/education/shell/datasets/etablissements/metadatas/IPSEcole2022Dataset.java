package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPSEcole2022Dataset implements IndicePositionSociale, Metadata {

    @Override
    public Double getIndice() {
        return 0.0;
    }

    @Override
    public Double getEcartType() {
        return 0.0;
    }

    @Override
    public Integer getAnnee() {
        return 0;
    }

    @Override
    public String getUai() {
        return "";
    }


    // Rentrée scolaire;
    // Code région;
    // Région;
    // Code de l'académie;
    // Académie;
    // Code du département;
    // Département;
    // Code INSEE de la commune;
    // Nom de la commune;
    // UAI;
    // Nom de l'établissement;
    // Secteur;
    // IPS;
    // IPS national privé;
    // IPS national public;
    // IPS national;
    // IPS académique privé;
    // IPS académique public;
    // IPS académique;
    // IPS départemental privé;
    // IPS départemental public;
    // IPS départemental;
    // num_ligne
}
