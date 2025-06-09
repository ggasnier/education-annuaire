package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CommuneRequest")
public class CommuneRequestDto {

    /**
     * TODO gestion de communes autre que france
     */
    @JsonProperty(required = true)
    @Size(min = 5, max = 5)
    protected String code;

    @JsonProperty(required = true)
    protected String nom;

    @JsonProperty(value = "code_pays", required = true)
    @Size(min = 2, max = 2)
    protected String codePays;

    @JsonProperty(value = "code_departement")
    protected String codeDepartement;
}
