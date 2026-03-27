package com.guillaumegasnier.education.web.dto.referentiels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MetierDetailsDto {

    private String code;

    private String nom;

    /**
     * Lignes de définition
     */
    private List<String> definitions;

    /**
     * Condition(s) d'accès au métier
     */
    private List<String> access;

    /**
     * Autres appellations
     */
    private List<String> appellations;
}
