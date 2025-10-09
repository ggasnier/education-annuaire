package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.references.CommuneEntity;
import com.guillaumegasnier.education.core.enums.EtatEtablissement;
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
@Table(name = "etablissements")
public class EtablissementEntity extends AbstractEntity {

    @Id
    @ValidUai
    @Column(columnDefinition = "VARCHAR(8)", unique = true, updatable = false, nullable = false, length = 8)
    private String uai;

    @ValidSiret
    @Column(columnDefinition = "VARCHAR(14)", length = 14)
    private String siret;

    @NotBlank
    private String nom;

    @Column(columnDefinition = "VARCHAR(1)", length = 1)
    @Enumerated(EnumType.STRING)
    private EtatEtablissement etat;

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

    @Column(columnDefinition = "VARCHAR(5)", length = 5)
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

    public UUID getId() {
        return UUID.nameUUIDFromBytes(uai.getBytes());
    }

}
