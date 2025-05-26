package com.guillaumegasnier.education.annuaire.domains;

import com.guillaumegasnier.education.annuaire.validations.ValidUai;
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
    private String uai;

    public IndicePositionSocialePK(String uai, int annee) {
        this.uai = uai;
        this.annee = annee;
    }
}
