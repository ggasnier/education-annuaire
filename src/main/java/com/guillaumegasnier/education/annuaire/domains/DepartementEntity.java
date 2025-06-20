package com.guillaumegasnier.education.annuaire.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "departements")
public class DepartementEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(3)", length = 3, unique = true)
    private String code;

    @NotBlank
    @Column(columnDefinition = "VARCHAR(50)", length = 50, unique = true, nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "code_region", foreignKey = @ForeignKey(name = "fk_departements_regions"))
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "code_academie", foreignKey = @ForeignKey(name = "fk_departements_academies"))
    private AcademieEntity academie;
}
