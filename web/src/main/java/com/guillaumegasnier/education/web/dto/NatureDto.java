package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@Schema(name = "NatureEtablissement")
public class NatureDto {
    private String code;
    private String nom;
}
