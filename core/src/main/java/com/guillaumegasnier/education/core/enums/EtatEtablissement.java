package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Deprecated
@Getter
@AllArgsConstructor
public enum EtatEtablissement {

    O("Ouvert"),
    F("Fermé"),
    C("À fermer"),
    A("À ouvrir");

    private final String nom;

}
