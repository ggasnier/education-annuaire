package com.guillaumegasnier.education.web.dto.etablissements;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormationDto {

    @EqualsAndHashCode.Include
    private Long id;

    private String nom;

    private String certifiante;
}
