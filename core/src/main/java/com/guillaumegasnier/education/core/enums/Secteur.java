package com.guillaumegasnier.education.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Secteur {

    PU("Public"),
    PV("Privé");

    private final String nom;
}
