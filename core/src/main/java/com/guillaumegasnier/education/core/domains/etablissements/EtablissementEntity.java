package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.organismes.OrganismeEntity;
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

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "etablissements")
public class EtablissementEntity extends AbstractEntity {

    /**
     * Code UAI de l'établissement scolaire
     */
    @Id
    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8, unique = true, nullable = false)
    private String uai;

    /**
     * Numéro SIRET de l’entreprise
     */
    @ValidSiret
    @Column(columnDefinition = "BPCHAR(14)", length = 14)
    private String siret;

    @NotBlank
    private String nom;

    private Boolean actif;

    @Column
    private LocalDate dateOuverture;

    @Column
    private LocalDate dateFermeture;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String adresse;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String complement;

    @Column(columnDefinition = "BPCHAR(5)", length = 5)
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "code_commune", foreignKey = @ForeignKey(name = "fk_etablissements_communes"))
    private CommuneEntity commune;

    @ManyToOne
    @JoinColumn(name = "code_nature", foreignKey = @ForeignKey(name = "fk_etablissements_natures"))
    private NatureEntity nature;

    @ManyToOne
    @JoinColumn(name = "code_contrat", foreignKey = @ForeignKey(name = "fk_etablissements_contrats"))
    private ContratEntity contrat;

    /**
     * Organisme de formation
     */
    @ManyToOne
    @JoinColumn(name = "nda", foreignKey = @ForeignKey(name = "fk_etablissements_organismes"))
    private OrganismeEntity organisme;

    @Column(name = "sources", columnDefinition = "VARCHAR(50)", length = 50)
    private String sources;

    public NatureEntity getNature() {
        if (nature == null) return new NatureEntity("$", "Non renseigné");
        return nature;
    }

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

}
