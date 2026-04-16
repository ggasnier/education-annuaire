package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacetteEtablissement {

    SECTEUR("codeSecteur", "nomSecteur", "Secteur", null, "codeSecteur"),
    NATURE("codeNature", "nomNature", "Nature", null, "codeNature"),
    OPTION("options.codeOption", "options.nomOption", "Option", "options", "codeOption"),
    LANGUE("langues.codeLangue", "langues.nomLangue", "Langue", "langues", "codeLangue"),
    COMMUNE("codeCommune", "nomCommune", "Commune", null, "codeCommune"),
    DEPARTEMENT("codeDepartement", "nomDepartement", "Département", null, "codeDepartement"),
    ACADEMIE("codeAcademie", "nomAcademie", "Académie", null, "codeAcademie");

    /**
     * Champ Elasticsearch (utilisé pour les queries/aggregations ES)
     */
    private final String code;
    private final String codeNom;
    private final String nom;

    /**
     * Chemin du champ nested (null si champ plat)
     */
    private final String nestedPath;

    /**
     * Clé utilisée dans les URLs (courte, sans namespace).
     * Ex : "codeOption" au lieu de "options.codeOption"
     */
    private final String urlKey;

    /**
     * Retrouve une facette par sa clé URL.
     */
    public static FacetteEtablissement byUrlKey(String urlKey) {
        if (urlKey == null) return null;
        for (FacetteEtablissement f : values()) {
            if (f.urlKey.equals(urlKey)) return f;
        }
        return null;
    }

    public boolean isNested() {
        return nestedPath != null;
    }
}
