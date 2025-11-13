package com.guillaumegasnier.education.core.dto;

import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class IndicateurValeurAjouteeDto implements IndicateurValeurAjoutee {

    private Set<ResultatFiliereDto> resultats;
}
