package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommuneRequestDto {

    /**
     * TODO gestion de communes autre que france
     */
    @JsonProperty(required = true)
    @Size(min = 5, max = 5)
    protected String code;

    @JsonProperty(required = true)
    protected String nom;

    @JsonProperty(value = "pays_code", required = true)
    @Size(min = 2, max = 2)
    protected String codePays;

    @JsonProperty(value = "departement_code")
    protected String codeDepartement;
}
