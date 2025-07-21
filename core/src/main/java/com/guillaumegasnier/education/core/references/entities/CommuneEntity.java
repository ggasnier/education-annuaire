package com.guillaumegasnier.education.core.references.entities;

import com.guillaumegasnier.education.core.commun.entities.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "communes")
public class CommuneEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "CHAR(5)", length = 5, unique = true)
    private String code;

    @NotBlank
    private String nom;

    @Column(columnDefinition = "CHAR(2)", length = 2, nullable = false)
    private String codePays;

    @ManyToOne
    @JoinColumn(name = "code_departement", foreignKey = @ForeignKey(name = "fk_communes_departements"))
    private DepartementEntity departement;
}
