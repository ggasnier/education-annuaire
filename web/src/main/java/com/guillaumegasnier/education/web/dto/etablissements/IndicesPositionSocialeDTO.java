package com.guillaumegasnier.education.web.dto.etablissements;

import lombok.Data;

@Data
public class IndicesPositionSocialeDTO {

    private Integer annee;

    private Integer effectifs;

    private Double indice;

    private Double ecartType;

    private Double indiceAcademie;

    private Double indiceDepartement;

    private Double indiceNational;
}
