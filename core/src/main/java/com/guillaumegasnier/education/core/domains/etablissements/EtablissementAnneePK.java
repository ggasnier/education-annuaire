package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class EtablissementAnneePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private int annee;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;
}
