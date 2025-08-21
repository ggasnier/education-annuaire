package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionEtablissement {

    RESTAURATION("Restauration"),
    HEBERGEMENT("Hebergement"),
    ULIS("ULIS"),
    APPRENTISSAGE("Apprentissage"),
    SEGPA("Segpa"),
    SECTION_ARTS("Section arts"),
    SECTION_CINEMA("Section cinéma"),
    SECTION_THEATRE("Section théatre"),
    SECTION_SPORT("Section sport"),
    SECTION_INTERNATIONALE("Section internationale"),
    SECTION_EUROPEENNE("Section europeenne"),
    LYCEE_AGRICOLE("Lycee agricole"),
    LYCEE_MILITAIRE("Lycee militaire"),
    LYCEE_DES_METIERS("Lycee des métiers"),
    POST_BAC("Post BAC"),
    BACHIBAC("Section binationale BACHIBAC"),
    BFI("Baccalauréat Français international");

    private final String nom;
}
