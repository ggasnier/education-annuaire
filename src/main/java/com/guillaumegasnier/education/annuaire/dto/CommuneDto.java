package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Commune")
public class CommuneDto extends CommuneRequestDto {

    @JsonProperty(value = "libelle_departement")
    protected String libelleDepartement;
}
