package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "IPSRequest", description = "Informations sur un IPS à créer ou mettre à jour")
public class IPSRequestDto {

    @JsonProperty(required = true)
    protected Integer annee;

    protected Double indice;

    @JsonProperty(value = "ecart_type")
    protected Double ecartType;
}
