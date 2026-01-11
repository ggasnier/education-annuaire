package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum OptionEtablissement {

    // Les comodités
    RESTAURATION("Restauration", "🍽️", Categorie.COMODITE),
    HEBERGEMENT("Hébergement", "🏨", Categorie.COMODITE),

    // les labels
    EUROSCOL("Euroscol", "🇪🇺", Categorie.LABEL),
    REP("REP", "📚", Categorie.LABEL),
    REPP("REP+", "📚", Categorie.LABEL),
    LYCEE_AGRICOLE("Lycée agricole", "🚜", Categorie.LABEL),
    LYCEE_MILITAIRE("Lycée militaire", "🎖️", Categorie.LABEL),
    LYCEE_DES_METIERS("Lycée des métiers", "🔧", Categorie.LABEL),

    // Les sections langues
    SECTION_BILINGUE("Section bilingue", "🗣️", Categorie.SECTION_LANGUE),
    SECTION_INTERNATIONALE("Section internationale", "🌍", Categorie.SECTION_LANGUE),
    BFI("Baccalauréat Français international", "🇫🇷", Categorie.SECTION_LANGUE),
    SECTION_EUROPEENNE("Section européenne", "🇪🇺", Categorie.SECTION_LANGUE),
    SECTION_ORIENTALE("Section de langue orientale", "🈯", Categorie.SECTION_LANGUE),
    ABIBAC("Section binationale ABIBAC", "🇫🇷🇩🇪", Categorie.SECTION_LANGUE),
    ESABAC("Section binationale ESABAC", "🇫🇷🇮🇹", Categorie.SECTION_LANGUE),
    BACHIBAC("Section binationale BACHIBAC", "🇫🇷🇪🇸", Categorie.SECTION_LANGUE),

    // Les sections cultures
    SECTION_ARTS("Section arts", "🎨", Categorie.SECTION_CULTURE),
    SECTION_CINEMA("Section cinéma", "🎬", Categorie.SECTION_CULTURE),
    SECTION_THEATRE("Section théatre", "🎭", Categorie.SECTION_CULTURE),

    // Sport
    SECTION_SPORT("Section sportive", "⚽", Categorie.SPORT),
    SPORT_ETUDES("Sport études", "🏆", Categorie.SPORT),

    // Enseignement
    ULIS("ULIS", "🤝", Categorie.ENSEIGNEMENT),
    APPRENTISSAGE("Apprentissage", "🎓", Categorie.ENSEIGNEMENT),
    SEGPA("Segpa", "📖", Categorie.ENSEIGNEMENT),
    POST_BAC("Post BAC", "🎓", Categorie.ENSEIGNEMENT),
    CPGE("CPGE", "📚", Categorie.ENSEIGNEMENT),
    @Deprecated
    CL_H_AM("Classe à horaires aménagés", "⏰", Categorie.ENSEIGNEMENT),
    CHA_ARTS("Classe à horaires aménagés arts et métiers du spectacle", "", Categorie.ENSEIGNEMENT),
    CHA_ARTS2("Classe à horaires aménagés arts plastiques", "", Categorie.ENSEIGNEMENT),
    CHA_CINE("Classe à horaires aménagés cinéma", "", Categorie.ENSEIGNEMENT),
    CHA_DANSE("Classe à horaires aménagés danse", "", Categorie.ENSEIGNEMENT),
    CHA_MUSIQUE("Classe à horaires aménagés musique", "", Categorie.ENSEIGNEMENT),
    CHA_THEATRE("Classe à horaires aménagés théâtre", "", Categorie.ENSEIGNEMENT),
    BREVET_MER("Brevet d'initiation à la mer", "", Categorie.ENSEIGNEMENT),
    BREVET_AERO("Brevet d'initiation aéronautique (BIA)", "", Categorie.ENSEIGNEMENT),
    PREPA_METIERS("Classe de 3e \"prépa-métiers\"", "", Categorie.ENSEIGNEMENT),
    FORAINS("Classe pour enfants de familles itinérantes et de voyageurs", "", Categorie.ENSEIGNEMENT),
    RELAIS("Dispositif relais", "", Categorie.ENSEIGNEMENT),
    UPE2A("Unité pédagogique pour élèves allophones arrivants", "🌐", Categorie.ENSEIGNEMENT);

    private final String nom;
    private final String emoji;
    private final Categorie categorie;

    @Getter
    @RequiredArgsConstructor
    public enum Categorie {
        COMODITE("Aménagements", 1, "bg-cyan-100 text-cyan-800"),
        LABEL("Labels", 2, "bg-amber-100 text-amber-800"),
        ENSEIGNEMENT("Enseignements", 3, "bg-indigo-100 text-indigo-800"),
        SECTION_LANGUE("Langues", 4, "bg-pink-100 text-pink-800"),
        SECTION_CULTURE("Sections", 5, "bg-orange-100 text-orange-800"),
        SPORT("Sport", 6, "bg-green-100 text-green-800");

        private final String nom;
        private final int ordre;
        private final String couleur;
    }
}
