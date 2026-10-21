package com.guillaumegasnier.education.web.dto.etablissements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Langue")
public class LangueDTO {

    private String code;
    private String nom;
    private String emoji;
    private String enseignement;
}
