package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.domains.references.CommuneEntity;
import com.guillaumegasnier.education.core.domains.references.PaysEntity;
import com.guillaumegasnier.education.core.dto.InformationsDto;
import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.ValidUai;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
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

    @Column(columnDefinition = "CHAR(5)", length = 5)
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "code_commune", foreignKey = @ForeignKey(name = "fk_etablissements_communes"))
    private CommuneEntity commune;

    @ManyToOne
    @JoinColumn(name = "code_pays", foreignKey = @ForeignKey(name = "fk_etablissements_pays"))
    private PaysEntity pays;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private InformationsDto informations = new InformationsDto();
    @Column(name = "sources", columnDefinition = "VARCHAR(50)", length = 50)
    private String sources;

    public InformationsDto getInformations() {
        if (informations == null) {
            informations = new InformationsDto();
        }
        return informations;
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
