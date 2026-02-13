package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractSourcesEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.etablissements.ValidUai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "etablissements")
public class EtablissementEntity extends AbstractSourcesEntity {

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

    @Column(columnDefinition = "BPCHAR(2)", length = 2)
    @Enumerated(EnumType.STRING)
    private Secteur secteur;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nda", foreignKey = @ForeignKey(name = "fk_etablissements_organismes"))
    private OrganismeEntity organisme;

    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL)
    private Set<EtablissementIdentifiantEntity> identifiants = new HashSet<>();

    @OneToOne(mappedBy = "etablissement")
    private EtablissementMasaEntity masa;

    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<EtablissementOptionEntity> options = new ArrayList<>();

    public NatureEntity getNature() {
        if (nature == null) return new NatureEntity("$", "Non renseigné");
        return nature;
    }

}
