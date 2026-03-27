package com.guillaumegasnier.education.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categorie {

    CATEGORIE("Catégorie"),
    SPECIALITE("Spécialité (BAC)"),
    OPTION("Option"),
    ETABLISSEMENTS("Etablissements"),
    CODE_NATURE("Type d'établissement"),
    CODE_CONTRAT("Contrat"),
    CODE_COMMUNE("Commune");

    private final String nom;
}
