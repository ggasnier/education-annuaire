package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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
    @Column(columnDefinition = "BIGINT", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id", foreignKey = @ForeignKey(name = "fk_actions_formations"), columnDefinition = "BIGINT", nullable = false)
    private FormationEntity formation;

    /**
     * Cet élément décrit le rythme de la formation : temps plein, temps partiel, etc.
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
     * Cet élément permet de préciser les dates et la durée des périodes en centre ou en entreprise.
     * <p>
     * Texte de 1 à 3000 caractère(s).
     */
    @Size(max = 3000)
    @Column(columnDefinition = "TEXT")
    private String modalitesAlternance;

    /**
     * 0 formation entièrement présentielle
     * <p>
     * 1 formation mixte
     * <p>
     * 2 formation entièrement à distance
     * <p>
     * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/dict-modalites-enseignement.html#dict-modalites-enseignement">Référence</a>
     */
    private Integer modalitesEnseignement;

    /**
     * Cet élément indique les conditions spécifiques d’accès à la formation, les aptitudes requises, une tranche d’âge, etc. Il indique les prérequis pour avoir accès à la formation. Si il n’y a pas de conditions spécifiques, il convient de préciser “aucune” dans cet élément.
     * <p>
     * Texte de 1 à 3000 caractère(s).
     */
    @Size(max = 3000)
    @Column(columnDefinition = "TEXT")
    private String conditionsSpecifiques;

    /**
     * Cet élément indique si une prise en charge des frais de formation est possible (renvoi vers les conseillers en charge de l’élaboration des parcours). Si une prise en charge des frais est possible, le détail des conditions de cette prise en charge devrait être donné dans l’élément «Détails des conditions de prise en charge».
     */
    private Boolean priseEnChargeFraisPossible;

    /**
     * Lieu de formation
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_actions_formations_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    /**
     * Cet élément contient un code permettant de dire si la formation est à dates fixes ou avec des entrées/sorties permanentes.
     * <p>
     * 0 entrées/sorties à dates fixes
     * <p>
     * 1 entrées/sorties permanentes
     *
     */
    private Integer modalitesEntreesSorties;

    /**
     * Cet élément contient une adresse de site web de l’action de formation.
     */
    private String urlAction;

    /**
     * Durée du cycle de formation en années
     */
    private Integer dureeCycle;

    /**
     * TODO
     * <p>
     * Cet élément permet de préciser une session de formation. La date de début de session (Période) est une date prévisionnelle d’entrée en session.
     */
    private String session;

    /**
     * Cet élément permet d’indiquer l’adresse où obtenir des informations sur la formation.
     */
    private String adresseInformation;

    /**
     * Cet élément permet d’indiquer la date d’une séance d’information sur la formation (une séance d’information ayant lieu à un endroit précis).
     */
    private LocalDate dateInformation;

    /**
     * Cet élément précise l’organisation matérielle de la formation, en précisant des aspects pratiques concernant la restauration. Si cet élément est vide, cela implique qu’il n’y a pas de restauration.
     * <p>
     * Texte de 1 à 250 caractère(s).
     */
    private String restauration;

    /**
     * Cet élément précise les possibilités d’hébergement. Si cet élément est vide, cela implique qu’il n’y pas de possibilités d’hébergement.
     * <p>
     * Texte de 1 à 250 caractère(s).
     */
    private String hebergement;

    /**
     * Cet élément précise l’organisation matérielle de la formation, en précisant les aspects pratiques liés aux transports. Si cet élément est vide, cela implique qu’il n’y a pas de transport prévu dans le cadre de la formation.
     * <p>
     * Texte de 1 à 250 caractère(s).
     */
    private String transport;

    /**
     * Cet élément précise l’organisation matérielle de la formation, en précisant les aspects pratiques liés aux accès et aménagements prévus pour les handicapés. Si cet élément est vide, cela implique qu’il n’y a pas d’aménagement spécifique.
     * <p>
     * Texte de 1 à 250 caractère(s).
     */
    private String accesHandicapes;

    /**
     * Code langue au format ISO 639-1
     * <p>
     * Texte de 2 à 2 caractère(s).
     */
    @Column(columnDefinition = "BPCHAR(2)")
    private String langueFormation;

    /**
     * Cet élément permet de préciser les modalités de recrutement et d’admission (tests, entretiens).
     * <p>
     * Texte de 0 à 3000 caractère(s).
     */
    @Size(max = 3000)
    @Column(columnDefinition = "TEXT")
    private String modalitesRecrutement;

    /**
     * Cet élément précise les modalités pédagogiques, dont l’individualisation de la formation, les possibilités d’autoformation avec une éventuelle durée, etc.
     * <p>
     * Texte de 0 à 200 caractère(s).
     */
    private String modalitesPedagogiques;

    /**
     * Cet élément contient un code de modalité pédagogique. Ce code est issu du FORMACODE. Il est obligatoire de préciser grâce à l’attribut “ref” la version du FORMACODE utilisée (par exemple “V10” pour la version 10 du FORMACODE).
     * <p>
     * Texte de 5 à 5 caractère(s).
     */
    private String codeModalitePedagogique;

    /**
     * Équipement à la charge des apprenants
     */
    private String equipement;

    /**
     * Frais restants à la charge du stagiaire (Les frais d’équipement doivent être précisés dans des éléments « Équipements à la charge de l’apprenant ».)
     * <p>
     * Texte de 0 à 200 caractère(s).
     */
    private String fraisRestants;

    /**
     * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/code-perimetre-recrutement.html#code-perimetre-recrutement">Référence</a>
     */
    private String codePerimetreRecrutement;

    private String infosPerimetreRecrutement;

    private String prixHoraireTTC;

    private String prixTotalTTC;

    /**
     * Cet élément donne la durée indicative moyenne de la formation pour le stagiaire. Elle peut être exprimée en année, semestre, mois, jour ou heure. Elle indique un délai entre le début et la fin de la formation, à la différence de l’élément « Total du nombre d’heures » qui donne une charge en nombre d’heures s’inscrivant dans ce délai.
     * <p>
     * Texte de 0 à 150 caractère(s).
     */
    private String dureeIndicative;

    private String nombreHeuresCentre;

    private String nombreHeuresEntreprise;

    private String nombreHeuresTotal;

    private String detailConditionsPriseEnCharge;

    private String conventionnement;

    private String dureeConventionnee;

    private String organismeFormateur;

    private String organismeFinanceur;

    /**
     * Le numéro extrait de AF.99999
     */
    private Integer onisepId;

    /**
     * Le code Interne Formation
     */
    private Integer parcoursupId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActionFormationEntity entity = (ActionFormationEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
