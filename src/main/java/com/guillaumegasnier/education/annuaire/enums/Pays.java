package com.guillaumegasnier.education.annuaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Pays {

    FR("France");

    private final String nom;
}
