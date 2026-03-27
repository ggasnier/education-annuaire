package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;

/**
 * Indices de position sociale des lycées (à partir de 2023)
 * <p>
 * <a href="https://www.data.gouv.fr/datasets/ips-lycees-a-partir-de-2023/">Référence</a>
 */
@Getter
@Setter
public class IPSLycee2023Dataset implements IndicePositionSociale, Metadata {

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "UAI")
    private String uai;

    /**
     * LEGT
     * LP
     * LPO
     */
    @CsvBindByName(column = "Type de lycée")
    private String typeLycee;
    @CsvBindByName(column = "IPS de l'établissement")
    private String indice;
    @CsvBindByName(column = "Ecart-type établissement")
    private String ecartType;
    @CsvBindByName(column = "IPS national LEGT")
    private Double indiceNational;
    @CsvBindByName(column = "IPS national LEGT privé")
    private Double indiceNationalPrive;
    @CsvBindByName(column = "IPS national LEGT public")
    private Double indiceNationalPublic;
    @CsvBindByName(column = "IPS académique LEGT")
    private String indiceAcademie;
    @CsvBindByName(column = "IPS académique LEGT privé")
    private String indiceAcademiePrive;
    @CsvBindByName(column = "IPS académique LEGT public")
    private String indiceAcademiePublic;
    @CsvBindByName(column = "IPS départemental LEGT")
    private String indiceDepartement;
    @CsvBindByName(column = "IPS départemental LEGT privé")
    private String indiceDepartementPrive;
    @CsvBindByName(column = "IPS départemental LEGT public")
    private String indiceDepartementPublic;

    @Override
    public Double getEcartType() {
        return formatDouble(ecartType);
    }

    @Override
    public Double getIndice() {
        return formatDouble(indice);
    }

    //IPS voie GT
    //IPS voie PRO
    //IPS post BAC
    //Ecart type voie GT
    //Ecart type voie PRO
    //IPS national LPO
    //IPS national LPO privé
    //IPS national LPO public
    //IPS académique LPO
    //IPS académique LPO privé
    //IPS académique LPO public
    //IPS départemental LPO
    //IPS départemental LPO privé
    //IPS départemental LPO public

    //IPS national LP
    //IPS national LP privé
    //IPS national LP public
    //IPS académique LP
    //IPS académique LP privé
    //IPS académique LP public
    //IPS départemental LP
    //IPS départemental LP privé
    //IPS départemental LP public

    @Override
    public Double getIndiceAcademie() {
        return formatDouble(indiceAcademie);
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
    public Double getIndiceDepartement() {
        return formatDouble(indiceDepartement);
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
    public Integer getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
