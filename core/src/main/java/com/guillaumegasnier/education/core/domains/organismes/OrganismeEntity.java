package com.guillaumegasnier.education.core.domains.organismes;

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
@Table(name = "organismes")
public class OrganismeEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(11)", length = 11, unique = true, nullable = false)
    private String nda;

    @NotBlank
    private String nom;
}
