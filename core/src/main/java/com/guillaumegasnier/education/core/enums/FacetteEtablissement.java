package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacetteEtablissement {

    SECTEUR("codeSecteur", "nomSecteur", "Secteur", null),
    NATURE("codeNature", "nomNature", "Nature", null),
    OPTION("options.codeOption", "options.nomOption", "Option", "options"),
    COMMUNE("codeCommune", "nomCommune", "Commune", null),
    DEPARTEMENT("codeDepartement", "nomDepartement", "Département", null),
    ACADEMIE("codeAcademie", "nomAcademie", "Académie", null);
    //REGION("codeRegion", "nomRegion", "Région", null),
    //PAYS("codePays", "nomPays", "Pays", null);

    private final String code;
    private final String codeNom;
    private final String nom;

    /**
     * Chemin du champ nested (null si champ plat)
     */
    private final String nestedPath;

    public boolean isNested() {
        return nestedPath != null;
    }
}
