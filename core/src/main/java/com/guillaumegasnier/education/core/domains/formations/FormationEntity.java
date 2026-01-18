package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractSourcesEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Offre de formation
 * <p>
 * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/formation.html#formation">Référence</a>
 */
@Getter
@Setter
@Entity
@Table(name = "formations")
public class FormationEntity extends AbstractSourcesEntity {

    @Id
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID id;

    /**
     * Cet élément décrit l’intitulé de la formation. Si la formation a comme résultat l’obtention d’un diplôme, le
     * contenu de cet élément devrait utiliser une dénomination conforme aux tables de l’Éducation Nationale. Si la
     * formation a comme résultat un titre ou une certification, le contenu devrait utiliser une dénomination conforme
     * au contenu du Répertoire National des Certifications Professionnelles (RNCP).
     */
    @NotNull
    private String nom;

    /**
     * Cet élément décrit l’objectif de la formation. Il décrit la ou les compétences à acquérir, à améliorer ou à
     * entretenir.
     */
    @Column(columnDefinition = "TEXT")
    private String objectif;

    /**
     * Cet élément décrit les résultats attendus de la formation (titre, diplôme, certificat, attestation, …) et
     * précise les modalités de reconnaissance ou de validation. De la même manière que pour l’élément
     * “intitule-formation”, les diplômes, titres ou certifications devraient utiliser des dénominations conformes aux
     * tables de l’Éducation Nationale ou au Répertoire National des Certifications Professionnelles (RNCP).
     */
    @Column(columnDefinition = "TEXT")
    private String resultats;

    /**
     * Cet élément décrit le contenu de la formation et fournit une description détaillée des différents sujets traités
     * dans la formation.
     */
    @Column(columnDefinition = "TEXT")
    private String contenu;

    /**
     * Cet élément indique si la formation permet d’obtenir une certification (diplôme, titre ou certificat de
     * qualification).
     */
    private Boolean certifiante;

    /**
     * Cet élément permet de qualifier le type de parcours de formation.
     * <p>
     * 1 en groupe (non personnalisable)
     * <p>
     * 2 individualisé
     * <p>
     * 3  modularisé
     * <p>
     * 4 mixte
     */
    private Integer parcoursDeFormation;

    /**
     * Cet élément décrit le niveau à l’entrée en formation. Ce niveau peut être souhaité ou exigé en fonction de la
     * valeur du code contenu dans l’élément “niveau-entree-obligatoire”. Règle de gestion: si il y a plusieurs niveaux
     * d’entrée possibles, prendre le niveau minimal.
     * <p>
     * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/dict-niveaux.html#dict-niveaux">Référence</a>
     */
    private Integer codeNiveauEntree;

    /**
     * Cet élément permet de préciser le code d’une certification dans le service Certifinfo, dans les RNCP et RS de
     * France Compétences, ou son code CPF fourni par la Caisse des dépôts.
     */
    //@OneToMany
    //private Set<CertificationEntity> certifications = new HashSet<>();

    /**
     * Cet élément décrit le niveau de sortie de la formation.
     * <p>
     * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/dict-niveaux.html#dict-niveaux">Référence</a>
     */
    private Integer codeNiveauSortie;

    /**
     * Les actions de formation (établissements, dates, etc.)
     */
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActionFormationEntity> actions = new ArrayList<>();

    /**
     * Cet élément décrit l’établissement responsable de la formation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_formations_etablissements"))
    private EtablissementEntity etablissement;

    /**
     * Cet élément décrit l’organisme de formation responsable de la formation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nda", foreignKey = @ForeignKey(name = "fk_formations_etablissements"))
    private OrganismeEntity organisme;

    /**
     * Cet identifiant permet de donner un identifiant à une action (qui dès lors devient un module). Cet identifiant
     * n’a pas de forme normalisée, il peut donc être créé librement en fonction des besoins de chaque structure.
     */
    @Column(columnDefinition = "TEXT")
    private String identifiantModule;

    /**
     * Cet élément contient un code permettant d’indiquer le type de positionnement.
     * <p>
     * <a href="https://dev.lheo.org/languages/lheo/2.3/lheo/dict-type-positionnement.html#dict-type-positionnement">Référence</a>
     */
    private Integer positionnement;

    /**
     * Le numéro extrait de FOR.99999
     */
    private Integer onisepId;

    /**
     * Identifiant Parcoursup
     */
    private Integer parcoursupId;

    // contacts
    // url
    // modules
    // Modules prérequis
}
