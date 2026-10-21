package com.guillaumegasnier.education.web.dto.territoires;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Departement")
public record DepartementDTO(@JsonProperty(required = true)
                             String code,
                             @JsonProperty(required = true)
                             String nom,
                             @JsonProperty(value = "code_academie")
                             String codeAcademie,
                             @JsonProperty(value = "nom_academie")
                             String nomAcademie,
                             @JsonProperty(value = "code_region")
                             String codeRegion,
                             @JsonProperty(value = "nom_region")
                             String nomRegion) {
}
