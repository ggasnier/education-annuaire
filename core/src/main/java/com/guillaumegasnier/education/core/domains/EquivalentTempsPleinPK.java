package com.guillaumegasnier.education.core.domains;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EquivalentTempsPleinPK implements Serializable {

    @ValidUai
    @Column(columnDefinition = "CHAR(8)", length = 8)
    private String uai;

    @NotNull
    private int anne;
}
