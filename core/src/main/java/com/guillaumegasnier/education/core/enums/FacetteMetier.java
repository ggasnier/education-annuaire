package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacetteMetier {

    DOMAINE("codeDomaine", "nomDomaine", "Domaine");

    private final String code;
    private final String codeNom;
    private final String nom;
}
