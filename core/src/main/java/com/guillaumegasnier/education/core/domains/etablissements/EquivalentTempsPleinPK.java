package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EquivalentTempsPleinPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "VARCHAR(8)", length = 8)
    private String uai;

    @NotNull
    private int anne;
}
