package com.guillaumegasnier.education.core.domains.etablissements;


import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
    @EqualsAndHashCode.Include
    private String code;

    @NotBlank
    @EqualsAndHashCode.Include
    private String nom;

    private String nomCourt;

    public NatureEntity(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NatureEntity that = (NatureEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, nom);
    }
}
