package com.guillaumegasnier.education.core.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EtablissementMetadataDto implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    /**
     * Effectif total de l'établissement
     */
    private Integer effectif;

    /**
     * Indice de position sociale
     */
    private IndicePositionSocialeDto ips;

    /**
     * Indicateurs de valeur ajoutée
     */
    private IndicateurValeurAjouteeDto iva;

    /**
     * Les personnels dans les établissements du premier et second degré
     */
    private PersonnelDto personnels;

}
