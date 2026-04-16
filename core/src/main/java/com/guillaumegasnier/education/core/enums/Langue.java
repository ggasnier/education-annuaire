package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Sert pour
 * <ul>
 *     <li>La langues lv1, lv2, lca</li>
 *     <li>Les sections bilingues</li>
 *     <li>Les sections internationales</li>
 *     <li>Les sections européennes</li>
 *     <li>Les sections langues orientales</li>
 * </ul>
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum Langue {

    FR("Français", "🇫🇷", "français", Categorie.LV),
    LA("Latin", "🏛️", "latin", Categorie.LC),
    CO("Corse", "\uD83C\uDFF4\uDB40\uDC66\uDB40\uDC72\uDB40\uDC63\uDB40\uDC6F\uDB40\uDC72\uDB40\uDC7F", "corse", Categorie.LV),
    EN("Anglais", "🇬🇧", "anglais", Categorie.LV),
    US("Américain", "\uD83C\uDDFA\uD83C\uDDF8", "américain", Categorie.LV),
    ES("Espagnol", "🇪🇸", "espagnol", Categorie.LV),
    IT("Italien", "🇮🇹", "italien", Categorie.LV),
    EL("Grec", "🇬🇷", "grec", Categorie.LC),
    OC("Occitan", "🏵️", "occitan", Categorie.LV),
    JP("Japonais", "🇯🇵", "japonais", Categorie.LV),
    SV("Suédois", "🇸🇪", "suédois", Categorie.LV),
    BR("Breton", "🏴", "breton", Categorie.LV),
    TR("Turc", "🇹🇷", "turc", Categorie.LV),
    RU("Russe", "🇷🇺", "russe", Categorie.LV),
    PL("Polonais", "🇵🇱", "polonais", Categorie.LV),
    TA("Tamoul", "🇮🇳", "tamoul", Categorie.LV),
    DA("Danois", "🇩🇰", "danois", Categorie.LV),
    TY("Tahitien", "🇵🇫", "tahitien", Categorie.LV),
    NL("Néerlandais", "🇳🇱", "néerlandais", Categorie.LV),
    PT("Portugais", "🇵🇹", "portugais", Categorie.LV),
    FA("Persan", "🇮🇷", "persan", Categorie.LV),
    HE("Hébreu moderne", "🇮🇱", "hébreu moderne", Categorie.LV),
    ZH("Chinois", "🇨🇳", "chinois", Categorie.LV),
    AR("Arabe", "🇸🇦", "arabe", Categorie.LV),
    VI("Vietnamien", "🇻🇳", "vietnamien", Categorie.LV),
    EU("Basque", "🏴", "basque", Categorie.LV),
    HT("Créole", "🇭🇹", "créole", Categorie.LV),
    KO("Coréen", "🇰🇷", "coréen", Categorie.LV),
    UK("Ukrainien", "🇺🇦", "ukrainien", Categorie.LV),
    CA("Catalan", "🏴", "catalan", Categorie.LV),
    NO("Norvégien", "🇳🇴", "norvégien", Categorie.LV),
    HY("Arménien", "🇦🇲", "arménien", Categorie.LV),
    LB("Luxembourgeois", "\uD83C\uDDF1\uD83C\uDDFA", "luxembourgeois", Categorie.LV),
    LI("Monégasque", "🇲🇨", "monégasque", Categorie.LV), // le code n'existe pas officiellement
    DH("Drehu (lifou)", "🇳🇨", "drehu (lifou)", Categorie.LV), // le code n'existe pas officiellement
    PF("Langues océaniennes", "🌊", "langues rares océaniennes", Categorie.LV), // le code n'existe pas officiellement
    DE("Allemand", "🇩🇪", "allemand", Categorie.LV),
    GS("Alsacien", "", "langues et cultures régionales : langues régionales d’Alsace", Categorie.LV),
    FS("Langue des signes française", "", "langue des signes française", Categorie.LV);

    private final String nom;
    private final String emoji;
    private final String nomMin;
    private final Categorie categorie;

    @Nullable
    public static Langue transformation(@NonNull String s) {
        for (Langue langue : Langue.values()) {
            if (langue.getNomMin().equals(s.trim())) {
                return langue;
            }
        }
        if (s.trim().equals("provençal"))
            return Langue.OC;
        log.error("Pas de mapping pour {}", s.trim());
        return null;
    }


    @Getter
    @AllArgsConstructor
    public enum Categorie {
        EU("Sections européennes", "bg-cyan-100 text-cyan-800"),
        LO("Sections de langues orientales", "bg-amber-100 text-amber-800"),
        SI("Sections internationales", "bg-indigo-100 text-indigo-800"),
        BI("Sections bilingues", "bg-pink-100 text-pink-800"),
        LV("Langues vivantes", "bg-orange-100 text-orange-800"),
        LC("Langues anciennes", "bg-green-100 text-green-800");

        private final String nom;
        private final String couleur;
    }
}
