package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;

@Data
public class FacetteEntity {

    private String code;
    private String valeur;
    private Integer total;
}
