package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Slf4j
@Getter
@AllArgsConstructor
public enum SpecialiteBac {

    LLCER("Langues, littératures et cultures étrangères et régionales", "Langues, littératures et cultures étrangères et régionales"),
    LLCER_ANGLAIS("LLCER anglais", "LLCER anglais"),
    LLCER_ANGLAIS2("LLCER anglais - monde contemporain", "LLCER anglais - monde contemporain"),
    LLCER_ESPAGNOL("LLCER espagnol", "LLCER espagnol"),
    LLCER_PORTUGAIS("LLCER portugais", "LLCER portugais"),
    LLCER_ITALIEN("LLCER italien", "LLCER italien"),
    LLCER_ALLEMAND("LLCER allemand", "LLCER allemand"),
    LLCER_TAHITIEN("LLCER tahitien", "LLCER tahitien"),
    LLCER_BRETON("LLCER breton", "LLCER breton"),
    LLCER_BASQUE("LLCER basque", "LLCER basque"),
    LLCER_CREOLE("LLCER créole", "LLCER créole"),
    LLCER_OCCITAN("LLCER occitan-langue d'oc", "LLCER occitan-langue d'oc"),
    CINEMA("Cinéma", "Arts : cinéma - audiovisuel"),
    DANSE("Danse", "Arts : danse"),
    THEATRE("Théâtre", "Arts : théâtre"),
    EPS("Éducation physique, pratiques et culture sportives", "Éducation physique, pratiques et culture sportives"),
    MUSIQUE("Musique", "Arts : musique"),
    HIS_ART("Histoire des arts", "Arts : histoire des arts"),
    HIST_GE("Histoire-géographie, géopolitique et sciences politiques", "Histoire-géographie, géopolitique et sciences politiques"),
    ART_PLA("Arts plastiques", "Arts : arts plastiques"),
    CIRQUE("Arts du cirque", "Arts : arts du cirque"),
    SC_INGE("Sciences de l'ingénieur", "Sciences de l'ingénieur"),
    LIT_GRE("Littérature et langues et cultures de l'Antiquité", "Littérature et langues et cultures de l'Antiquité"),
    PHY_CHI("Physique-chimie", "Physique-chimie"),
    BIO_ECO("Biologie-écologie", "Biologie-écologie"),
    LIT_PHI("Humanités, littérature et philosophie", "Humanités, littérature et philosophie"),
    NUMERIQ("Numérique et sciences informatiques", "Numérique et sciences informatiques"),
    SVT("Sciences de la vie et de la Terre", "Sciences de la vie et de la Terre"),
    SES("Sciences économiques et sociales", "Sciences économiques et sociales"),
    MATHS("Mathématiques", "Mathématiques");

    private final String nom;
    private final String nomOnisep;

    @Nullable
    public static SpecialiteBac transformation(@NonNull String s) {
        for (SpecialiteBac specialiteBac : SpecialiteBac.values()) {
            if (specialiteBac.getNomOnisep().equals(s.trim())) {
                return specialiteBac;
            }
        }
        log.error("Spécialité à mapper : {}", s.trim());
        return null;
    }
}
