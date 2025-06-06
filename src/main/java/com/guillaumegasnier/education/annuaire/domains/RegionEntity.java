package com.guillaumegasnier.education.annuaire.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class RegionEntity {

    @Id
    @Column(columnDefinition = "CHAR(2)", length = 2, unique = true, nullable = false)
    private String code;

    @NotBlank
    @Column(columnDefinition = "VARCHAR(50)", length = 50, unique = true, nullable = false)
    private String nom;

}
