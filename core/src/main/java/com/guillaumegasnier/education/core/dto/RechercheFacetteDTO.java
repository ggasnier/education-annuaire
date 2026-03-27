package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RechercheFacetteDTO {

    /**
     * Code de la facette (codeNature, codeCommune, etc.)
     */
    private String code;

    /**
     * Nom de la facette (nomNature, nomCommune, etc.)
     */
    private String nom;

    /**
     * Pour la facette, liste des valeurs possibles
     */
    private List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();

}
