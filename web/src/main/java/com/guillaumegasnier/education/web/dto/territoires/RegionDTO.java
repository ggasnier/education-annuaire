package com.guillaumegasnier.education.web.dto.territoires;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Region")
public record RegionDTO(String code,
                        String nom) {
}
