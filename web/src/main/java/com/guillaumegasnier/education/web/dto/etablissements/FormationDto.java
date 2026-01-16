package com.guillaumegasnier.education.web.dto.etablissements;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormationDto {

    @EqualsAndHashCode.Include
    private UUID id;
    private String nom;
}
