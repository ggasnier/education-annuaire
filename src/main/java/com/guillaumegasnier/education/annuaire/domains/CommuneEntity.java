package com.guillaumegasnier.education.annuaire.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "communes")
public class CommuneEntity {

    @Id
    @Column(columnDefinition = "CHAR(5)", length = 5, unique = true)
    private String code;

    @NotBlank
    private String nom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pays_code", foreignKey = @ForeignKey(name = "fk_communes_pays"))
    private PaysEntity pays;

    @ManyToOne
    @JoinColumn(name = "departement_code", foreignKey = @ForeignKey(name = "fk_communes_departements"))
    private DepartementEntity departement;
}
