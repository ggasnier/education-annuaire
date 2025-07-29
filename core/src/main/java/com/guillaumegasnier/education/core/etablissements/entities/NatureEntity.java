package com.guillaumegasnier.education.core.etablissements.entities;


import com.guillaumegasnier.education.core.commun.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Nature d'un établissement</p>
 */
@Getter
@Setter
@Entity
@Table(name = "natures")
public class NatureEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "CHAR(3)", length = 3, unique = true)
    private String code;

    @NotBlank
    private String nom;

    private String nomCourt;
}
