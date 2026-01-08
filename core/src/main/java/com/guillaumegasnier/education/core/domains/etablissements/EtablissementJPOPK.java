package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementJPOPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

    private LocalDate date;
}
