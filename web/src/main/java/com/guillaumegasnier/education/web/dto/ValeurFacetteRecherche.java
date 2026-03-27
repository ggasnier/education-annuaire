package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ValeurFacetteDto")
public class ValeurFacetteRecherche {
    private String code;
    private String nom;
    private Integer total;
    private Boolean checked;

    public String getNom() {
        if (nom == null) {
            return code;
        }
        return nom;
    }
}
