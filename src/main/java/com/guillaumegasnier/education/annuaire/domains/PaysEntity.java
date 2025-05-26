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
@Table(name = "pays")
public class PaysEntity {

    @Id
    @Column(columnDefinition = "CHAR(2)", length = 2, unique = true)
    private String code;

    @NotBlank
    private String nom;
}
