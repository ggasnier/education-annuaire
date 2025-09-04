package com.guillaumegasnier.education.web.dto.etablissements;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "Option")
public record OptionDto(String code, String nom) {

}
