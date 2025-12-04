package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Répertoire Opérationnel des Métiers et des Emplois
 */
@Getter
@Setter
@Entity
@Table(name = "romes")
public class RomeEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BPCHAR(5)", length = 5, nullable = false)
    private String code;

    private String nom;

    @ManyToMany(mappedBy = "romes")
    private Set<CertificationEntity> certifications = new HashSet<>();

}
