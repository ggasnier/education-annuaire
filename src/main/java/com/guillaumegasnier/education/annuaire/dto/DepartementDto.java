package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Departement")
public class DepartementDto {

    @JsonProperty(required = true)
    private String code;

    @JsonProperty(required = true)
    private String nom;

    @JsonProperty(value = "code_academie")
    private String codeAcademie;

    @JsonProperty(value = "nom_academie")
    private String nomAcademie;

    @JsonProperty(value = "code_region")
    private String codeRegion;

    @JsonProperty(value = "nom_region")
    private String nomRegion;
}
