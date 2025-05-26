package com.guillaumegasnier.education.annuaire.domains;

import com.guillaumegasnier.education.annuaire.enums.EtatEtablissement;
import com.guillaumegasnier.education.annuaire.validations.ValidSiret;
import com.guillaumegasnier.education.annuaire.validations.ValidUai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements")
public class EtablissementEntity {

    @Id
    @ValidUai
    @Column(columnDefinition = "CHAR(8)", unique = true, updatable = false, nullable = false, length = 8)
    private String uai;

    @ValidSiret
    @Column(columnDefinition = "CHAR(14)", length = 14)
    private String siret;

    @NotBlank
    private String nom;

    @NotNull
    @Column(columnDefinition = "CHAR(1)", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatEtablissement etat;

    @ManyToOne
    @JoinColumn(name = "nature_code")
    private NatureEntity nature;

    private String adresse;

    private String complement;

    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "commune_code")
    private CommuneEntity commune;

    // Position (POSTGIS)

}
