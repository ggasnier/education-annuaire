package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * <action>
 * <rythme-formation>...</rythme-formation> <!-- [1,1] -->
 * <code-public-vise>...</code-public-vise> <!-- [1,10] -->
 * <info-public-vise>...</info-public-vise> <!-- [0,1] -->
 * <niveau-entree-obligatoire>...</niveau-entree-obligatoire> <!-- [1,1] -->
 * <modalites-alternance>...</modalites-alternance> <!-- [1,1] -->
 * <modalites-enseignement>...</modalites-enseignement> <!-- [1,1] -->
 * <conditions-specifiques>...</conditions-specifiques> <!-- [1,1] -->
 * <prise-en-charge-frais-possible>...</prise-en-charge-frais-possible> <!-- [1,1] -->
 * <lieu-de-formation>...</lieu-de-formation> <!-- [0,N] -->
 * <modalites-entrees-sorties>...</modalites-entrees-sorties> <!-- [1,1] -->
 * <url-action>...</url-action> <!-- [0,1] -->
 * <duree-cycle>...</duree-cycle> <!-- [0,1] -->
 * <session>...</session> <!-- [1,N] -->
 * <adresse-information>...</adresse-information> <!-- [0,1] -->
 * <date-information>...</date-information> <!-- [0,3] -->
 * <restauration>...</restauration> <!-- [0,1] -->
 * <hebergement>...</hebergement> <!-- [0,1] -->
 * <transport>...</transport> <!-- [0,1] -->
 * <acces-handicapes>...</acces-handicapes> <!-- [0,1] -->
 * <langue-formation>...</langue-formation> <!-- [0,5] -->
 * <modalites-recrutement>...</modalites-recrutement> <!-- [0,1] -->
 * <modalites-pedagogiques>...</modalites-pedagogiques> <!-- [0,1] -->
 * <code-modalite-pedagogique>...</code-modalite-pedagogique> <!-- [0,5] -->
 * <equipement>...</equipement> <!-- [0,N] -->
 * <frais-restants>...</frais-restants> <!-- [0,1] -->
 * <code-perimetre-recrutement>...</code-perimetre-recrutement> <!-- [0,1] -->
 * <infos-perimetre-recrutement>...</infos-perimetre-recrutement> <!-- [0,1] -->
 * <prix-horaire-TTC>...</prix-horaire-TTC> <!-- [0,1] -->
 * <prix-total-TTC>...</prix-total-TTC> <!-- [0,1] -->
 * <duree-indicative>...</duree-indicative> <!-- [0,1] -->
 * <nombre-heures-centre>...</nombre-heures-centre> <!-- [0,1] -->
 * <nombre-heures-entreprise>...</nombre-heures-entreprise> <!-- [0,1] -->
 * <nombre-heures-total>...</nombre-heures-total> <!-- [0,1] -->
 * <detail-conditions-prise-en-charge>...</detail-conditions-prise-en-charge> <!-- [0,1] -->
 * <conventionnement>...</conventionnement> <!-- [0,1] -->
 * <duree-conventionnee>...</duree-conventionnee> <!-- [0,1] -->
 * <organisme-formateur>...</organisme-formateur> <!-- [0,N] -->
 * <organisme-financeur>...</organisme-financeur> <!-- [0,8] -->
 * <enseignement>...</enseignement> <!-- [0,N] -->
 * <extras>...</extras> <!-- [0,N] -->
 * </action>
 */
@Getter
@Setter
@Entity
@Table(name = "formations_actions")
public class ActionFormationEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "formation_id", foreignKey = @ForeignKey(name = "fk_actions_formations"), columnDefinition = "UUID", nullable = false)
    private FormationEntity formation;

    /**
     * Cet élément décrit le rythme de la formation: temps plein, temps partiel, etc.
     */
    @Column(columnDefinition = "TEXT)")
    private String rythmeFormation;

    /**
     * Cet élément contient un code de public visé. Ce code est issu du FORMACODE. Il est obligatoire de préciser grâce
     * à l’attribut “ref” la version du FORMACODE utilisée (par exemple “V10” pour la version 10 du FORMACODE).
     */
    @Column(columnDefinition = "BPCHAR(5)")
    private String codePublicVise;

    /**
     * Cette donnée permet à l’organisme d’ajouter des caractéristiques à l’article sélectionné dans la table « public
     * visé », dès lors qu’elles ne donnent pas lieu à discrimination (portée juridique).
     */
    @Column(columnDefinition = "VARCHAR(250)")
    private String infoPublicVise;

    /**
     * Cet élément précise si le niveau à l’entrée en formation décrit dans l’élément “code-niveau-entree” est
     * obligatoire ou uniquement indicatif.
     */
    private Boolean niveauEntreeObligatoire;

    /**
     *
     */
    private String modalitesAlternance;

    private String modalitesEnseignement;

    private String conditionsSpecifiques;

    private String priseEnChargeFraisPossible;

    @ManyToOne
    @JoinColumn(name = "uai", foreignKey = @ForeignKey(name = "fk_actions_formations_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    private String modalitesEntreesSorties;

    private String urlAction;

    private String dureeCycle;

    private String session;

    private String adresseInformation;

    private String dateInformation;

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
}
