package com.guillaumegasnier.education.web.dto.territoires;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Academie")
public record AcademieDTO(String code,
                          String nom) {
}
