package com.guillaumegasnier.education.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Indication du niveau d’association à la transition écologique du métier
 * <p>
 * Valeurs possibles :
 * <ul>
 *     <li>Emploi stratégique pour la Transition écologique</li>
 *     <li>Emploi Vert</li>
 *     <li>Emploi Blanc</li>
 *     <li>Emploi Brun</li>
 * </ul>
 */
@Getter
@RequiredArgsConstructor
public enum TransitionEcologique {

    S("Emploi stratégique pour la Transition écologique"),
    G("Emploi Vert"),
    W("Emploi Blanc"),
    B("Emploi Brun");

    private final String nom;
}
