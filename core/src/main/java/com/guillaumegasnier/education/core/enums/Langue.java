package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Slf4j
@Getter
@AllArgsConstructor
public enum Langue {

    FR("Français", "français"),
    LA("Latin", "latin"),
    CO("Corse", "corse"),
    EN("Anglais", "anglais"),
    ES("Espagnol", "espagnol"),
    IT("Italien", "italien"),
    EL("Grec", "grec"),
    OC("Occitan/Provençal", "occitan"),
    JP("Japonais", "japonais"),
    SV("Suédois", "suédois"),
    BR("Breton", "breton"),
    TR("Turc", "turc"),
    RU("Russe", "russe"),
    PL("Polonais", "polonais"),
    TA("Tamoul", "tamoul"),
    DA("Danois", "danois"),
    TY("Tahitien", "tahitien"),
    NL("Néerlandais", "néerlandais"),
    PT("Portugais", "portugais"),
    FA("Persan", "persan"),
    HE("Hébreu moderne", "hébreu moderne"),
    ZH("Chinois", "chinois"),
    AR("Arabe", "arabe"),
    VI("Vietnamien", "vietnamien"),
    EU("Basque", "basque"),
    HT("Créole", "créole"),
    KO("Coréen", "coréen"),
    UK("Ukrainien", "ukrainien"),
    CA("Catalan", "catalan"),
    NO("Norvégien", "norvégien"),
    HY("Arménien", "arménien"),
    LI("Monégasque", "monégasque"), // le code n'existe pas officiellement
    DH("Drehu (lifou)", "drehu (lifou)"), // le code n'existe pas officiellement
    PF("Langues océaniennes", "langues rares océaniennes"), // le code n'existe pas officiellement
    DE("Allemand", "allemand");

    private final String nom;
    private final String nomMin;

    @Nullable
    public static Langue transformation(@NonNull String s) {
        for (Langue langue : Langue.values()) {
            if (langue.getNomMin().equals(s.trim())) {
                return langue;
            }
        }
        if (s.trim().equals("provençal"))
            return Langue.OC;
        if (s.trim().equals("américain"))
            return Langue.EN;
        log.error("Pas de mapping pour {}", s.trim());
        return null;
    }
}
