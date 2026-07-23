package com.guillaumegasnier.education.web.dto.formations;

import lombok.Data;

@Data
public class ActionFormationDto {

    private long id;

    private String rythmeFormation;

    private String codePublicVise;

    private String infoPublicVise;

    private Boolean niveauEntreeObligatoire;

    private String modalitesAlternance;

    private Integer modalitesEnseignement;

    private String conditionsSpecifiques;

    private Boolean priseEnChargeFraisPossible;

    private Integer modalitesEntreesSorties;

    private String urlAction;

    private Integer dureeCycle;


    // Formation
    private long formationId;

    private String nom;

    private String certifiante;

    // Etablissement
}
