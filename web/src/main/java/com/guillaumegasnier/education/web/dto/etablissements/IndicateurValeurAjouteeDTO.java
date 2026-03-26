package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.Filiere;
import lombok.Data;

@Data
public class IndicateurValeurAjouteeDTO {

    private Integer annee;

    private Integer effectifs;

    private Filiere filiere;

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

    /**
     * Taux de réussite en 3 ans
     */
    private Double tauxAcces;
}
