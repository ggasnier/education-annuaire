package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;

/**
 * Indices de position sociale des collèges (2016-2021)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/indices-de-position-sociale-dans-les-colleges-de-france-metropolitaine-et-drom/">Référence</a>
 */
@Getter
@Setter
public class IPSCollege2016Dataset implements IndicePositionSociale, Metadata {

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
