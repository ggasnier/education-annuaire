package com.guillaumegasnier.education.annuaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Academie {

    IDF("11", "Ile de France", List.of("75"));

    private final String code;
    private final String nom;
    private final List<String> departements;
}
