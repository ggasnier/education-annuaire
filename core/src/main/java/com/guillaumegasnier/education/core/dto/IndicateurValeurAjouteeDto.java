package com.guillaumegasnier.education.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeDto {

    private Set<ResultatFiliereDto> resultats;

    public ResultatFiliereDto getResultat() {
        return resultats.stream().filter(r -> r.getCode().equals("BREVETG")).findFirst().orElse(null);
    }
}
