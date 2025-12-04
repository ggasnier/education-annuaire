package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementLanguePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "BPCHAR(2)", length = 2)
    private Langue langue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "BPCHAR(2)", length = 2)
    private Langue.Categorie categorie;

    /**
     * LV1, LV2, etc.
     */
    @NotNull
    @Column(columnDefinition = "BPCHAR(3)", length = 3)
    private String enseignement;

}