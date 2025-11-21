package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.enums.SectionInternationale;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class SectionInternationalePK {

    @ValidUai
    @Column(columnDefinition = "BPCHAR(8)", length = 8)
    private String uai;

    @Enumerated(EnumType.STRING)
    private SectionInternationale section;

    private String niveau;
}
