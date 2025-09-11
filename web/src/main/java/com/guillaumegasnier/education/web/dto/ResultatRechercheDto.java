package com.guillaumegasnier.education.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Schema(name = "ResultatRecherche")
public class ResultatRechercheDto {

    private UUID id;
    private String nom;
    private String categorie;
    private String key;
    private Map<String, String> informations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double score;
    private Integer total;

}
