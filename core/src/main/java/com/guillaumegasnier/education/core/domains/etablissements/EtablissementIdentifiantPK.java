package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementIdentifiantPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

    /**
     * ONISEP
     * MASA
     */
    @Column(columnDefinition = "VARCHAR(8)", length = 8)
    private String clef;

    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String valeur;
}
