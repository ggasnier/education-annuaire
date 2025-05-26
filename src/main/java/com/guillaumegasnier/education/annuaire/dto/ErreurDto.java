package com.guillaumegasnier.education.annuaire.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Erreur")
public class ErreurDto {

    private String details;

    public ErreurDto(String details) {
        this.details = details;
    }
}
