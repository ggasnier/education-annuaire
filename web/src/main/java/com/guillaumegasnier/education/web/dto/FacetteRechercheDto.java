package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "FacetteRecherche")
public class FacetteRechercheDto {
    private String codeCategorie;
    private String nomCategorie;
    private List<ValeurFacetteRecherche> valeurs;
}
