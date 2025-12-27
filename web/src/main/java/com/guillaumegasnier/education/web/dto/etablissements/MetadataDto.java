package com.guillaumegasnier.education.web.dto.etablissements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetadataDto {

    private Integer annee;

    private Integer effectifs;

    private Double indice;

    private Double ecartType;

    private Double indiceAcademie;

    private Double indiceDepartement;

    private Double indiceNational;

    private Double tauxReussite;

    private Double tauxMentions;

    private Double valeurAjoutee;
}
