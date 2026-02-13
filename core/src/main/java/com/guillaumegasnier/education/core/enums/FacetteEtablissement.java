package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacetteEtablissement {

    SECTEUR("codeSecteur", "nomSecteur", "Secteur"),
    NATURE("codeNature", "nomNature", "Nature"),
    COMMUNE("codeCommune", "nomCommune", "Commune"),
    DEPARTEMENT("codeDepartement", "nomDepartement", "Département"),
    ACADEMIE("codeAcademie", "nomAcademie", "Académie"),
    //REGION("codeRegion", "nomRegion", "Région"),
    PAYS("codePays", "nomPays", "Pays");

    private final String code;
    private final String codeNom;
    private final String nom;
}
