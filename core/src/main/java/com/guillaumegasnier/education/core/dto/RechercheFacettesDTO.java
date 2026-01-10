package com.guillaumegasnier.education.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Data
public class RechercheFacettesDTO {

    /**
     * Liste des facettes
     */
    private List<RechercheFacetteDTO> facettes = new ArrayList<>();
}
