package com.guillaumegasnier.education.core.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MetierBlocDto {

    /**
     * 3 ou 4
     */
    @EqualsAndHashCode.Include
    private int code;

    @EqualsAndHashCode.Include
    private int position;

    private String texte;
}
