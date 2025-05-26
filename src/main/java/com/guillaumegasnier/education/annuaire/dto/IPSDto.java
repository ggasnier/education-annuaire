package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "IPS", description = "Informations sur l'IPS d'un établissement")
public class IPSDto extends IPSRequestDto {

    @JsonProperty(required = true)
    private String uai;

}
