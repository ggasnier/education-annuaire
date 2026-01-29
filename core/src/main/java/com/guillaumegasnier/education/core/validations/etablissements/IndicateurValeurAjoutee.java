package com.guillaumegasnier.education.core.validations.etablissements;

import com.guillaumegasnier.education.core.dto.ResultatFiliereDto;

import java.util.Set;

public interface IndicateurValeurAjoutee {

    Double getTauxAcces();

    Set<ResultatFiliereDto> getResultats();
}
