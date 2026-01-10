package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Secteur {

    PU("Public"),
    PV("Privé");

    private final String nom;
}
