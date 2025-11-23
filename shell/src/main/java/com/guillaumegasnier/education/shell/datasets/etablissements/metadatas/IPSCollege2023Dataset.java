package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.Effectifs;
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
public class IPSCollege2023Dataset implements IndicePositionSociale, Effectifs, Metadata {

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
        try {
            return Double.parseDouble(indiceAcademiePrive);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double getIndiceAcademiePublic() {
        try {
            return Double.parseDouble(indiceAcademiePublic);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double getIndiceDepartementPrive() {
        try {
            return Double.parseDouble(indiceDepartementPrive);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Double getIndiceDepartementPublic() {
        try {
            return Double.parseDouble(indiceDepartementPublic);
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
