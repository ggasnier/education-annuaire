package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * RNCP et RS seulement : ne doit pas être confondu avec {@link CertifInfoEntity}
 */
@Getter
@Setter
@Entity
@Table(name = "certifications")
public class CertificationNationaleEntity extends AbstractEntity {

    /**
     * RNCP ou RS + les chiffres
     */
    @Id
    private String code;

    private String nom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "certifications_metiers",
            joinColumns = @JoinColumn(name = "certification_code"),
            inverseJoinColumns = @JoinColumn(name = "metier_code"),
            foreignKey = @ForeignKey(name = "fk_certifications_metiers_c"),
            inverseForeignKey = @ForeignKey(name = "fk_certifications_metiers_m"))
    private List<MetierEntity> metiers;
}
