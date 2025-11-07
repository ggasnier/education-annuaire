package com.guillaumegasnier.education.core.domains.etablissements;


import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Nature d'un établissement</p>
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "natures")
public class NatureEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(3)", length = 3, unique = true)
    private String code;

    @NotBlank
    private String nom;

    private String nomCourt;

    public NatureEntity(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }
}
