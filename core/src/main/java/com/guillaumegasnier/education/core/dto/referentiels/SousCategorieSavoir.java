package com.guillaumegasnier.education.core.dto.referentiels;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SousCategorieSavoir {

    A("", CategorieSavoir.C);

    private final String nom;
    private final CategorieSavoir categorieSavoir;

    @Getter
    @RequiredArgsConstructor
    public enum CategorieSavoir {
        C("Certifications et habilitations"),
        D("Domaines d'expertise"),
        N("Normes et procédés"),
        P("Produits, outils et matières"),
        T("Techniques professionnelles");

        private final String nom;
    }
}
