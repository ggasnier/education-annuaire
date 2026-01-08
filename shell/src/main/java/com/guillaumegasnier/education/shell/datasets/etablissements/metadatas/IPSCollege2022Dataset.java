package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;


/**
 * Indices de position sociale dans les collèges (2022)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/indices-de-position-sociale-dans-les-colleges-a-partir-de-2022/">Référence</a>
 */
@Getter
@Setter
public class IPSCollege2022Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;
    @CsvBindByName(column = "UAI")
    private String uai;
    @CsvBindByName(column = "IPS")
    private String indice;
    @CsvBindByName(column = "Ecart-type de l'IPS")
    private String ecartType;

    @Override
    public Double getIndice() {
        return formatDouble(indice);
    }

    @Override
    public Double getEcartType() {
        return formatDouble(ecartType);
    }

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
