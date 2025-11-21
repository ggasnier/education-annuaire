package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Remplace
 */
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class EtablissementSportPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sport sport;

    /**
     * Section sportive ou sport etudes
     */
    @Column(columnDefinition = "BPCHAR(2)", length = 2)
    private String categorie;
}
