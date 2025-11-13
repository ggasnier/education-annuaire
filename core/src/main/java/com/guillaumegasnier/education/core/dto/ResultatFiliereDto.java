package com.guillaumegasnier.education.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultatFiliereDto implements Serializable {

    /**
     * Code filière
     * <p>
     * TODO à transforme en enum
     */
    private String code;

    /**
     * Présents
     */
    private Integer effectif;

    /**
     * Taux de réussite
     */
    private Double taux;

    /**
     * Valeur ajoutée du taux de reussite
     */
    private Double valeurAjoutee;

    /**
     * Taux de mentions
     */
    private Double tauxMentions;

    /**
     * Valeur ajoutée du taux de mentions
     */
    private Double valeurAjouteeTauxMentions;
}
