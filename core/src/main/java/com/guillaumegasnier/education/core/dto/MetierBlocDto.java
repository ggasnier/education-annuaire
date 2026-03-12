package com.guillaumegasnier.education.core.dto;

import lombok.Data;

@Data
public class MetierBlocDto {

    /**
     * 3 ou 4
     */
    private int code;

    private int position;

    private String texte;
}
