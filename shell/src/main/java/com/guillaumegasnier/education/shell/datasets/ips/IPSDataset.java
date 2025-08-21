package com.guillaumegasnier.education.shell.datasets.ips;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IPSDataset implements Dataset {

    @CsvBindByName(column = "UAI")
    protected String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    protected String rentreeScolaire;

    /**
     * E : école
     * C : collège
     * L : lycée
     * R : établissements régionaux d'enseignement adapté (EREA)
     */
    protected String categorie;

    protected String nomSecteur;

    @CsvBindByName(column = "IPS")
    protected String indice;
    protected String ecartType;
    protected String indiceNational;
    protected String indiceNationalPrive;
    protected String indiceNationalPublic;
    protected String codeAcademie;
    protected String indiceAcademie;
    protected String indiceAcademiePrive;
    protected String indiceAcademiePublic;
    protected String codeDepartement;
    protected String indiceDepartement;
    protected String indiceDepartementPrive;
    protected String indiceDepartementPublic;

    protected String codeCommune;

    public Double getIndice() {
        if (indice == null) return null;
        if (indice.equals("NA") || indice.equals("NS")) return null;
        return Double.parseDouble(indice);
    }

    public Double getEcartType() {
        if (ecartType == null || ecartType.isBlank()) return null;
        if (ecartType.equals("NA") || ecartType.equals("NS")) return null;
        return Double.parseDouble(ecartType);
    }

    public final int getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
