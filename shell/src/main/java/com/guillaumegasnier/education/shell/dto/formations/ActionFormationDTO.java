package com.guillaumegasnier.education.shell.dto.formations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ActionFormationDTO {

    @EqualsAndHashCode.Include
    private Long actionFormationId;
    private Long formationId;
    private String rythmeFormation;
    private String codePublicVise;
    private String infoPublicVise;
    private Boolean niveauEntreeObligatoire;
    private String modalitesAlternance;
    private Integer modalitesEnseignement;
    private String conditionsSpecifiques;
    private Boolean priseEnChargeFraisPossible;
    private String uai;
    private Integer modalitesEntreesSorties;
    private String urlAction;
    private Integer dureeCycle;
    private String adresseInformation;
    private LocalDate dateInformation;
    private String restauration;
    private String hebergement;
    private String transport;
    private String accesHandicapes;
    private String langueFormation;
    private String modalitesRecrutement;
    private String modalitesPedagogiques;
    private String codeModalitePedagogique;
    private String equipement;
    private String fraisRestants;
    private String codePerimetreRecrutement;
    private String infosPerimetreRecrutement;
    private String prixHoraireTTC;
    private String prixTotalTTC;
    private String dureeIndicative;
    private String nombreHeuresCentre;
    private String nombreHeuresEntreprise;
    private String nombreHeuresTotal;
    private String detailConditionsPriseEnCharge;
    private String conventionnement;
    private String dureeConventionnee;
    private String organismeFormateur;
    private String organismeFinanceur;

    private Integer onisepId;
    private Integer parcoursupId;
    private String carifId;
}
