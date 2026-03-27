package com.guillaumegasnier.education.core.dto;

import lombok.Data;

@Data
public class EtablissementMetadataDto {

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
