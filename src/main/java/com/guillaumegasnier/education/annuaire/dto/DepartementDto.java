package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Departement")
public class DepartementDto {

    @JsonProperty(required = true)
    private String code;

    @JsonProperty(required = true)
    private String nom;

    @JsonProperty(value = "academie_code")
    private String codeAcademie;

    @JsonProperty(value = "academie_nom")
    private String nomAcademie;

    @JsonProperty(value = "region_code")
    private String codeRegion;

    @JsonProperty(value = "region_nom")
    private String nomRegion;
}
