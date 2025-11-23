package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;


/**
 * Indices de position sociale dans les collèges (2022)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/indices-de-position-sociale-dans-les-colleges-a-partir-de-2022/">Référence</a>
 */
@Getter
@Setter
public class IPSCollege2022Dataset implements IndicePositionSociale, Effectifs, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;
    @CsvBindByName(column = "UAI")
    private String uai;
    @CsvBindByName(column = "Effectifs")
    private String effectifs;
    @CsvBindByName(column = "IPS")
    private String indice;
    @CsvBindByName(column = "Ecart-type de l'IPS")
    private String ecartType;

    @Override
    public Integer getEffectifs() {
        try {
            return (int) Double.parseDouble(effectifs);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double getIndice() {
        try {
            return Double.parseDouble(indice);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double getEcartType() {
        try {
            return Double.parseDouble(ecartType);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
