package com.guillaumegasnier.education.core.dto;

import lombok.Data;

@Data
public class RechercheFacetteValeurDTO {

    /**
     * Code de la valeur de la facette
     */
    private String code;

    /**
     * Nom associé à la valeur
     */
    private String nom;

    /**
     * Indicateur de sélection
     */
    private String checked;

    /**
     * Nombre de résultats avec cette valeur
     */
    private int total;
}
