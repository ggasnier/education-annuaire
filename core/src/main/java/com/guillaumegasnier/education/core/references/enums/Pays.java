package com.guillaumegasnier.education.core.references.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Pays {

    FR("France");

    private final String nom;
}
