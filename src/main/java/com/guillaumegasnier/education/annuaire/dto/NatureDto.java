package com.guillaumegasnier.education.annuaire.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "NatureEtablissement")
public class NatureDto {
    private String code;
    private String nom;
}
