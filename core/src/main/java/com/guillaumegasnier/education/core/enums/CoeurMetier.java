package com.guillaumegasnier.education.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CoeurMetier {

    P("Savoir-faire principaux"),
    S("Savoir-faire secondaires"),
    E("Savoir-faire");

    private final String nom;

}
