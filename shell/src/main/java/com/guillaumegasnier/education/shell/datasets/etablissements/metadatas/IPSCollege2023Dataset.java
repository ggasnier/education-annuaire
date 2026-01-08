package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;

/**
 * Indices de position sociale des collèges (à partir de 2023)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/ips-colleges-a-partir-de-2023/">Référence</a>
 */
@Getter
@Setter
public class IPSCollege2023Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;
    @CsvBindByName(column = "UAI")
    private String uai;
    @CsvBindByName(column = "IPS")
    private String indice;
    @CsvBindByName(column = "Ecart type de l'IPS")
    private String ecartType;
    @CsvBindByName(column = "IPS national privé")
    private Double indiceNationalPrive;
    @CsvBindByName(column = "IPS national public")
    private Double indiceNationalPublic;
    @CsvBindByName(column = "IPS national")
    private Double indiceNational;
    @CsvBindByName(column = "IPS académique privé")
    private String indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique public")
    private String indiceAcademiePublic;
    @CsvBindByName(column = "IPS académique")
    private Double indiceAcademie;
    @CsvBindByName(column = "IPS départemental privé")
    private String indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental public")
    private String indiceDepartementPublic;
    @CsvBindByName(column = "IPS départemental")
    private Double indiceDepartement;

    @Override
    public Double getIndiceAcademiePrive() {
        return formatDouble(indiceAcademiePrive);
    }

    @Override
    public Double getIndiceAcademiePublic() {
        return formatDouble(indiceAcademiePublic);
    }

    @Override
    public Double getIndiceDepartementPrive() {
        return formatDouble(indiceDepartementPrive);
    }

    @Override
    public Double getIndiceDepartementPublic() {
        return formatDouble(indiceDepartementPublic);
    }

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
