package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.references.CommuneEntity;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "organismes")
public class OrganismeEntity extends AbstractEntity {

    /**
     * Numéro de Déclaration d'Activité
     */
    @Id
    @Column(columnDefinition = "BPCHAR(11)", length = 11)
    private String nda;

    private String nom;

    @ValidSiret
    @Column(columnDefinition = "BPCHAR(14)", length = 14)
    private String siret;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String adresse;

    @Column(columnDefinition = "VARCHAR(5)", length = 5)
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "code_commune", foreignKey = @ForeignKey(name = "fk_organismes_communes"))
    private CommuneEntity commune;

    /**
     * Certification Qualiopi pour Action De Formation
     */
    private Boolean actionsDeFormation;

    /**
     * Certification Qualiopi pour Bilan de Compétences
     */
    private Boolean bilansDeCompetences;

    /**
     * Certification Qualiopi pour Validation des Acquis de l'Expérience
     */
    private Boolean validationAcquisExperience;

    /**
     * Certification Qualiopi pour Action De Formation par Apprentissage
     */
    private Boolean actionsDeFormationParApprentissage;

    /**
     * Possède au moins une des 4 certifications Qualiopi
     */
    private Boolean certificationQualiopi;

    private LocalDate dateDerniereDeclaration;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    // TODO
    @ManyToMany
    private List<SpecialiteEntity> specialites;

}
