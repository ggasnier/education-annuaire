package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.enums.OuiNon;
import com.guillaumegasnier.education.core.enums.TransitionEcologique;
import lombok.Data;

@Data
public class MetierAppellationDto {
    private String nom;
    private TransitionEcologique transitionEco;
    private OuiNon transitionNum;
    private OuiNon transitionDemo;
    private OuiNon emploiReglemente;
    private OuiNon emploiCadre;
}
