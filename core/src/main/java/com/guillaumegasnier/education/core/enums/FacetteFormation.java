package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacetteFormation {

    SECTEUR("codeCertifiante", "nomCertifiante", "certifiante", null);

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
