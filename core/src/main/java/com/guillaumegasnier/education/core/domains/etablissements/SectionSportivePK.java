package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class SectionSportivePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ValidUai
    @Column(columnDefinition = "VARCHAR(8)", length = 8)
    private String uai;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sport sport;
}
