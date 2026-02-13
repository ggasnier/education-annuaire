package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeDto {

    private Set<ResultatFiliereDto> resultats;

    @JsonIgnore
    private Double tauxAcces;

    @JsonIgnore
    public ResultatFiliereDto getResultat() {
        return resultats.stream().filter(r -> r.getCode().equals("BREVETG")).findFirst().orElse(null);
    }
}
