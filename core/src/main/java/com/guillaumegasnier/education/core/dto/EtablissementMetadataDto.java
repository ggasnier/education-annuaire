package com.guillaumegasnier.education.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EtablissementMetadataDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Effectif total de l'établissement
     */
    private Integer effectifs;

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
