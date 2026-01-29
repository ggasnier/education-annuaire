package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ResultatEntity {

    private Long id;
    private String nom;
    private String categorie;
    private String key;
    private Map<String, String> informations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double score;
    private Integer total;
}
