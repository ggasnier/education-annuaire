package com.guillaumegasnier.education.core.domains.referentiels;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class MetierCompetencePK implements Serializable {

    @Column(columnDefinition = "BPCHAR(5)", name = "metier_code")
    private String metierCode;

    @Column(name = "competence_code")
    private int competenceCode;
}
