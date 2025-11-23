package com.guillaumegasnier.education.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeDto {

    private Set<ResultatFiliereDto> resultats;
}
