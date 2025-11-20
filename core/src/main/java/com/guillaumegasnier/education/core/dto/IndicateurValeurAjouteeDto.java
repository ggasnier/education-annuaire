package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
public class IndicateurValeurAjouteeDto implements IndicateurValeurAjoutee, Serializable {

    @Serial
    private final static long serialVersionUID = 1L;
    
    private Set<ResultatFiliereDto> resultats;
}
