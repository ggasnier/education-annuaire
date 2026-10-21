package com.guillaumegasnier.education.web.dto.etablissements;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Contact")
public record ContactDTO(
        String code,
        String nom,
        String valeur) {
}
