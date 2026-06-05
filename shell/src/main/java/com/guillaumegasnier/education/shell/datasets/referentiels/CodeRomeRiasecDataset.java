package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_referentiel_code_rome_riasec_v460_utf8.csv
 */
@Getter
@Setter
public class CodeRomeRiasecDataset implements Dataset {

    @CsvBindByName(column = "code_rome")
    private String codeRome;
    // "riasec_majeur",
    // "riasec_mineur"
}
