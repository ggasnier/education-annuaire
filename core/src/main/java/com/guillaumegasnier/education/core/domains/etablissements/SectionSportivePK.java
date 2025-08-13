package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SectionSportivePK {

    @ValidUai
    @Column(columnDefinition = "CHAR(8)", length = 8)
    private String uai;

    @NotNull
    @Column(columnDefinition = "VARCHAr(50)", length = 50)
    private String nom;
}
