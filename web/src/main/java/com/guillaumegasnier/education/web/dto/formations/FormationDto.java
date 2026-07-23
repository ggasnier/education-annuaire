package com.guillaumegasnier.education.web.dto.formations;

import lombok.Data;

@Data
public class FormationDto {

    private long id;

    private String nom;

    private String objectif;

    private String resultats;

    private String contenu;

    private Boolean certifiante;

    private Integer parcoursDeFormation;

    private Integer codeNiveauEntree;

    private Integer codeNiveauSortie;


    private String identifiantModule;

    private Integer positionnement;

    // Certification
}
