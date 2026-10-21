package com.guillaumegasnier.education.web.dto.territoires;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Commune")
public record CommuneDTO(String code,
                         String nom) {
}
