package com.guillaumegasnier.education.web.dto.recherche;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Deprecated
@Data
@Schema(name = "FacetteRecherche")
public class FacetteRechercheDTO {
    private String codeCategorie;
    private String nomCategorie;
    private List<ValeurFacetteRecherche> valeurs;

}
