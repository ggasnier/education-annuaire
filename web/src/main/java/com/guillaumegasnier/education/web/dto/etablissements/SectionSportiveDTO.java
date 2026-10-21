package com.guillaumegasnier.education.web.dto.etablissements;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SectionSportive")
public record SectionSportiveDTO(
        String code,
        String nom) {
}
