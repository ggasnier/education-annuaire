package com.guillaumegasnier.education.core.domains.formations;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LienOnisepPK implements Serializable {

    private Integer onisepId;

    /**
     * PSF : Parcoursup : formation
     * PSA : Parcoursup : action de formation
     * CFD : Numéro de formation
     */
    private String clef;

    private String valeur;
}