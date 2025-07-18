package com.guillaumegasnier.education.annuaire.datasets.etablissements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IPSDataset {

    protected String uai;

    protected String rentreeScolaire;

    /**
     * E : école
     * C : collège
     * L : lycée
     * R : établissements régionaux d'enseignement adapté (EREA)
     */
    protected String categorie;

    protected Double indice;

    protected Double ecartType;
    protected Double indiceNational;
    protected Double indiceNationalPrive;
    protected Double indiceNationalPublic;
    protected String codeAcademie;
    protected Double indiceAcademie;
    protected Double indiceAcademiePrive;
    protected Double indiceAcademiePublic;
    protected String codeDepartement;
    protected Double indiceDepartement;
    protected Double indiceDepartementPrive;
    protected Double indiceDepartementPublic;

    public final int getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }
}
