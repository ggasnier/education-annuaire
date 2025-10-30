package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * RNCP
 * RS
 * Certif Info
 */
@Getter
@Setter
@Entity
@Table(name = "certifications")
public class CertificationEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID id;

    /**
     * Cet élément contient un code d’une certification dans le Répertoire National des Certifications
     * Professionnelles, géré par France Compétences. Il est soit composé uniquement de chiffres, soit composé du
     * préfixe RNCP suivi de chiffres.
     */
    @Column(name = "code_rncp", columnDefinition = "VARCHAR(10)", length = 10)
    private String codeRNCP;

    /**
     * Cet élément contient le code d’une certification dans le Répertoire Spécifique, attribué par France Compétences.
     * Il est composé du préfixe RS suivi de chiffres.
     */
    @Column(name = "code_rs", columnDefinition = "VARCHAR(10)", length = 10)
    private String codeRS;

    /**
     * Cet élément contient un code d’une certification éligible au CPF (Compte Personnel de Formation), attribué par
     * la Caisse des dépôts. Il est composé du préfixe CPF suivi de chiffres.
     */
    @Column(name = "code_cpf", columnDefinition = "VARCHAR(10)", length = 10)
    private String codeCPF;

    /**
     * Cet élément contient un code d’une certification dans le référentiel Certif Info. Certif Info est un référentiel
     * des certifications élaboré en partenariat entre les CARIF-OREF, la CNCP, l’ONISEP, la DGEFP, Pôle emploi et le
     * Centre INFFO ( <a href="http://www.certifinfo.org">voir</a>). Règle de gestion: les codes de Certif Info qui seront utilisés ne
     * doivent être que ceux correspondant à des certifications de droit, ou certifications sur demande qui ont été
     * enregistrées au RNCP.
     */
    @Column(name = "code_certifinfo", columnDefinition = "VARCHAR(10)", length = 10)
    private String codeCertifInfo;

    /**
     * Intitulé de la certification défini lors de la décision d'enregistrement.
     */
    private String nom;

    @ManyToMany
    @JoinTable(name = "certifications_romes",
            joinColumns = @JoinColumn(name = "certification_id"),
            inverseJoinColumns = @JoinColumn(name = "rome_code"))
    private Set<RomeEntity> romes = new HashSet<>();
}
