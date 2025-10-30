package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.references.CommuneEntity;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "etablissements",
        indexes = {@Index(name = "idx_uai_not_null", columnList = "uai")},
        uniqueConstraints = {@UniqueConstraint(name = "unique_uai_not_null", columnNames = {"uai"})})
public class EtablissementEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID id;

    /**
     * Code UAI de l’organisme de formation/l'établissement scolaire
     */
    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8, unique = true)
    private String uai;

    /**
     * Numéro SIRET de l’organisme de formation
     */
    @ValidSiret
    @Column(columnDefinition = "BPCHAR(14)", length = 14)
    private String siret;

    /**
     * Numéro de déclaration d’activité de l’organisme responsable de l’offre.
     */
    @Column(columnDefinition = "BPCHAR(11)", length = 11)
    private String numeroDeclarationActivite;

    @NotBlank
    private String nom;

    private Boolean actif;

    @Column
    private LocalDate dateOuverture;

    @Column
    private LocalDate dateFermeture;

    @ManyToOne
    @JoinColumn(name = "code_nature", foreignKey = @ForeignKey(name = "fk_etablissements_natures"))
    private NatureEntity nature;

    @ManyToOne
    @JoinColumn(name = "code_contrat", foreignKey = @ForeignKey(name = "fk_etablissements_contrats"))
    private ContratEntity contrat;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String adresse;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String complement;

    @Column(columnDefinition = "BPCHAR(5)", length = 5)
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "code_commune", foreignKey = @ForeignKey(name = "fk_etablissements_communes"))
    private CommuneEntity commune;

    @Column(name = "sources", columnDefinition = "VARCHAR(50)", length = 50)
    private String sources;

    public Set<String> getSources() {
        if (sources == null || sources.isBlank()) return new HashSet<>();
        return new HashSet<>(Arrays.asList(sources.split("\\|")));
    }

    public void setSources(Set<String> sources) {
        this.sources = String.join("|", sources);
    }

    public void addSource(@NonNull String source) {
        Set<String> sourcesSet = getSources();
        sourcesSet.add(source);
        this.sources = String.join("|", sourcesSet);
    }

//    public UUID getId() {
//        return UUID.nameUUIDFromBytes(uai.getBytes());
//    }

}
