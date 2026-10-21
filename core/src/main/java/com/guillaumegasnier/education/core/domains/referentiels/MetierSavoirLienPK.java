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
public class MetierSavoirLienPK implements Serializable {

    @Column(columnDefinition = "BPCHAR(5)", length = 5, nullable = false)
    private String codeMetier;

    @Column(nullable = false)
    private int codeSavoir;
}
