package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Langue")
public class LangueDto {

    private String code;
    private String nom;
    private String niveau;
}
