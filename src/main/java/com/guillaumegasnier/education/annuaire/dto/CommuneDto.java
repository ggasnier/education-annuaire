package com.guillaumegasnier.education.annuaire.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Commune")
public class CommuneDto extends CommuneRequestDto {

    protected String libelleDepartement;
}
