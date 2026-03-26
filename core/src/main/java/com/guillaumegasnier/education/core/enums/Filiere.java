package com.guillaumegasnier.education.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Filiere {

    // Brevet
    BREVETG("Brevet général"),
    BREVETPRO("Brevet professionnel"),

    // Bac général (ancien)
    S("Bac Scientifique"),
    L("Bac Littéraire"),
    ES("Bac Économique et social"),

    // Bac général (nouveau, depuis 2020)
    GENERALE("Bac général"),

    // Bac technologique
    STMG("Bac Sciences et technologies du management et de la gestion (STMG)"),
    STHR("Bac Sciences et technologies de l'hôtellerie et de la restauration (STHR)"),
    ST2S("Bac Sciences et technologies de la santé et du social (ST2S)"),
    STAV("Bac sciences et technologies de l'agronomie et du vivant (STAV)"),
    STD2A("Bac Sciences et technologies du design et des arts appliqués (STD2A)"),
    STI2D("Bac Sciences et technologies de l'industrie et du développement durable (STI2D)"),
    STL("Bac Sciences et technologies de laboratoire (STL)"),
    S2TMD("Bac Sciences et technologies du théâtre, de la musique et de la danse (S2TMD)"),

    // Bac professionnel — domaines NSF agrégés (nomenclature IVA Éducation Nationale)
    PRO_NSF20("Spécialités pluri-technologiques de la production"),       // NSF 20
    PRO_NSF22("Transformations"),                                          // NSF 22
    PRO_NSF23("Génie civil, construction, bois"),                          // NSF 23
    PRO_NSF24("Matériaux souples"),                                        // NSF 24
    PRO_NSF25("Mécanique, électricité, électronique"),                     // NSF 25
    PRO_NSF26("Production industrielle"),                                  // NSF 26 (Production)
    PRO_NSF30("Spécialités plurivalentes des services"),                   // NSF 30
    PRO_NSF31("Échanges et gestion"),                                      // NSF 31
    PRO_NSF32("Communication et information"),                             // NSF 32
    PRO_NSF33("Services aux personnes"),                                   // NSF 33
    PRO_NSF34("Services à la collectivité"),                               // NSF 34
    PRO_NSF35("Services généraux");                                        // NSF 35 (Services)

    private final String nom;
}
