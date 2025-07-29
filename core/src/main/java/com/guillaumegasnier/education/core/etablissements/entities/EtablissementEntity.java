package com.guillaumegasnier.education.core.etablissements.entities;

import com.guillaumegasnier.education.core.commun.entities.AbstractEntity;
import com.guillaumegasnier.education.core.etablissements.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.etablissements.validations.ValidSiret;
import com.guillaumegasnier.education.core.etablissements.validations.ValidUai;
import com.guillaumegasnier.education.core.references.entities.CommuneEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

import java.util.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "etablissements")
public class EtablissementEntity extends AbstractEntity {

    @Id
    @ValidUai
    @Column(columnDefinition = "CHAR(8)", unique = true, updatable = false, nullable = false, length = 8)
    private String uai;

    @ValidSiret
    @Column(columnDefinition = "CHAR(14)", length = 14)
    private String siret;

    @NotBlank
    private String nom;

    @Column(columnDefinition = "CHAR(1)", length = 1)
    @Enumerated(EnumType.STRING)
    private EtatEtablissement etat;

    @ManyToOne
    @JoinColumn(name = "code_nature", foreignKey = @ForeignKey(name = "fk_etablissements_natures"))
    private NatureEntity nature;

    @ManyToOne
    @JoinColumn(name = "code_contrat", foreignKey = @ForeignKey(name = "fk_etablissements_contrats"))
    private ContratEntity contrat;

    private String adresse;

    private String complement;

    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "code_commune", foreignKey = @ForeignKey(name = "fk_etablissements_communes"))
    private CommuneEntity commune;

    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContactEntity> contacts = new ArrayList<>();

    @Column(name = "sources")
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

    public String getSourcesAsString() {
        return this.sources;
    }

}
