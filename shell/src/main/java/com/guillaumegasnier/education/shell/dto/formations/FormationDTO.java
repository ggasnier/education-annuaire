package com.guillaumegasnier.education.shell.dto.formations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormationDTO {

    @EqualsAndHashCode.Include
    private UUID id;
    private String nom;
    private String objectif;
    private String resultats;
    private String contenu;
    private Boolean certifiante;
    private Integer parcoursDeFormation;
    private Integer codeNiveauEntree;
    private Integer codeNiveauSortie;
    private String uai;
    private String nda;
    private String identifiantModule;
    private Integer positionnement;
    private Integer onisepId;
    private Integer parcoursupId;
//    private String carifId;
}
