package com.guillaumegasnier.education.core.domains.referentiels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * RNCP et RS seulement : ne doit pas être confondu avec {@link CertifInfoEntity}
 */
@Getter
@Setter
@Entity
@Table(name = "certifications")
public class CertificationNationaleEntity {

    /**
     * RNCP ou RS + les chiffres
     */
    @Id
    private String code;

    private String nom;
}
