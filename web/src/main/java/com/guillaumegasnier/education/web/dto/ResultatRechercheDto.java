package com.guillaumegasnier.education.web.dto;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Deprecated
@Data
@Schema(name = "ResultatRecherche")
public class ResultatRechercheDto {

//    private String nom;
//    private String categorie;
//    private String key;
//    private Map<String, String> informations;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private Double score;
//    private Integer total;

    private final List<RechercheEtablissementEntity> results;
    private final long totalElements;
    private final int page;
    private final int size;
    private final long totalPages;

    public ResultatRechercheDto(List<RechercheEtablissementEntity> results, long totalElements, int page, int size) {
        this.results = results;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
        this.totalPages = (long) Math.ceil((double) totalElements / (double) size);
    }

}
