package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Set<ResultatFiliereDto> resultats;

    @JsonIgnore
    private Double tauxAcces;

//    @JsonIgnore
//    public ResultatFiliereDto getResultat() {
//        return resultats.stream().filter(r -> r.getCode().equals("BREVETG")).findFirst().orElse(null);
//    }
}
