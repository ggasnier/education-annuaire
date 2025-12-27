package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Commune")
public class CommuneDto {

    protected String code;

    protected String nom;

}
