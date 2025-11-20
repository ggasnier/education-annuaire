package com.guillaumegasnier.education.core.domains.organismes;

import com.guillaumegasnier.education.core.validations.ValidSiret;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entreprises")
public class EntrepriseEntity {

    /**
     * Numéro SIRET de l’entreprise (formateur, certificateur, etc.)
     */
    @Id
    @ValidSiret
    @Column(columnDefinition = "BPCHAR(14)", length = 14)
    private String siret;

    private String nom;
}
