package com.guillaumegasnier.education.shell.dto.etablissements;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternatDTO {

    private String uai;

    private int annee;

    private int disponible;

    private int occupe;

    public InternatDTO(String uai, int annee, int disponible, int occupe) {
        this.uai = uai;
        this.annee = annee;

        if (disponible > occupe) {
            this.disponible = disponible;
            this.occupe = occupe;
        } else {
            this.disponible = occupe;
            this.occupe = disponible;
        }
    }
}
