package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;

@Getter
@Setter
public class IPSEcole2022Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;
    @CsvBindByName(column = "UAI")
    private String uai;
    @CsvBindByName(column = "IPS")
    private String indice;
    @CsvBindByName(column = "IPS national")
    private Double indiceNational;
    @CsvBindByName(column = "IPS national privé")
    private Double indiceNationalPrive;
    @CsvBindByName(column = "IPS national public")
    private Double indiceNationalPublic;
    @CsvBindByName(column = "IPS académique")
    private Double indiceAcademie;
    @CsvBindByName(column = "IPS académique privé")
    private String indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique public")
    private String indiceAcademiePublic;
    @CsvBindByName(column = "IPS départemental")
    private Double indiceDepartement;
    @CsvBindByName(column = "IPS départemental privé")
    private String indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental public")
    private String indiceDepartementPublic;

    @Override
    public Double getEcartType() {
        return null;
    }

    @Override
    public Double getIndice() {
        return formatDouble(indice);
    }

    @Override
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }

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
}
