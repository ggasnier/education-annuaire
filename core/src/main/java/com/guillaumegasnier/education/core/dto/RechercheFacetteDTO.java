package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
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
     * Pour la facette, liste des valeurs possibles (non triées)
     */
    private List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();

    /**
     * Retourne les valeurs triées : d'abord celles dont checked n'est pas null,
     * puis par total décroissant.
     */
    public List<RechercheFacetteValeurDTO> getValeurs() {
        return valeurs.stream()
                .sorted(Comparator
                        .comparing((RechercheFacetteValeurDTO v) -> v.getChecked() == null)
                        .thenComparingInt(v -> -v.getTotal()))
                .toList();
    }

}
