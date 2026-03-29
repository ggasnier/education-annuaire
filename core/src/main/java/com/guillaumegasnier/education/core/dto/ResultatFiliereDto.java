package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.enums.Filiere;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultatFiliereDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Code filière
     */
    private Filiere filiere;

    /**
     * Présents
     */
    private Integer effectif;

    /**
     * Taux d'accès : 2nd bac ou 6e 3e
     */
    private Double tauxAcces;

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
