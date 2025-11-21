package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionEtablissement {

    // Les comodités
    RESTAURATION("Restauration"),
    HEBERGEMENT("Hébergement"),

    // les labels
    EUROSCOL("Euroscol"),
    REP("REP"),
    REPP("REP+"),
    LYCEE_AGRICOLE("Lycée agricole"),
    LYCEE_MILITAIRE("Lycée militaire"),
    LYCEE_DES_METIERS("Lycée des métiers"),

    // Les sections langues
    SECTION_BILINGUE("Section bilingue"),
    SECTION_INTERNATIONALE("Section internationale"),
    BFI("Baccalauréat Français international"),
    SECTION_EUROPEENNE("Section européenne"),
    SECTION_ORIENTALE("Section de langue orientale"),
    ABIBAC("Section binationale ABIBAC"),
    ESABAC("Section binationale ESABAC"),
    BACHIBAC("Section binationale BACHIBAC"),

    // Les sections cultures
    SECTION_ARTS("Section arts"),
    SECTION_CINEMA("Section cinéma"),
    SECTION_THEATRE("Section théatre"),

    // Sport
    SECTION_SPORT("Section sportive"),
    SPORT_ETUDES("Sport études"),

    // Enseignement
    ULIS("ULIS"),
    APPRENTISSAGE("Apprentissage"),
    SEGPA("Segpa"),
    POST_BAC("Post BAC"),
    CL_H_AM("Classe à horaires aménagés"),
    UPE2A("Unité pédagogique pour élèves allophones arrivants");

    private final String nom;
}
