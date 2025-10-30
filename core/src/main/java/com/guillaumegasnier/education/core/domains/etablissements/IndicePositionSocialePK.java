package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class IndicePositionSocialePK implements Serializable {

    @NotNull
    private int annee;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

}
