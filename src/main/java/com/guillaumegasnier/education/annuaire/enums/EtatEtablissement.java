package com.guillaumegasnier.education.annuaire.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EtatEtablissement {

    O("Ouvert"),
    F("Fermé"),
    C("À fermer"),
    A("À ouvrir");

    private final String nom;

}
