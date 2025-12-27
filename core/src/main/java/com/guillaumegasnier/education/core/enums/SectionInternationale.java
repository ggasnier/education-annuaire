package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * À fusionner avec Langue
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum SectionInternationale {

    UK(null, "BRITANNIQUE"),
    US(null, "AMÉRICAINE"),
    AU(null, "AUSTRALIENNE"),
    BR(null, "BRÉSILIENNE"),
    SE(null, "SUÉDOISE"),
    RU(null, "RUSSE"),
    AR(null, "ARABE"),
    NL(null, "NÉERLANDAISE"),
    KR(null, "CORÉENNE"),
    DE(null, "ALLEMANDE"),
    DK(null, "DANOISE"),
    NO(null, "NORVÉGIENNE"),
    ES(null, "ESPAGNOLE"),
    CN(null, "CHINOISE"),
    IT(null, "ITALIENNE"),
    PT(null, "PORTUGAISE"),
    JP(null, "JAPONAISE"),
    PL(null, "POLONAISE");

    private final String nom;
    private final String nomDataset;

    public static SectionInternationale transformation(@NonNull String s) {

        for (SectionInternationale sectionInternationale : SectionInternationale.values()) {
            if (sectionInternationale.getNomDataset().equals(s.trim())) {
                return sectionInternationale;
            }
        }
        log.warn("Pas de section pour {}", s);
        return null;
    }
}
