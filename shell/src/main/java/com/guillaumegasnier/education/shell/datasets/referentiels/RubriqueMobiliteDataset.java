package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_rubrique_mobilite_v460_utf8.csv
 */
@Getter
@Setter
public class RubriqueMobiliteDataset implements Dataset {

    @CsvBindByName(column = "code_rome")
    private String codeRome;

    @CsvBindByName(column = "code_rome_cible")
    private String codeRomeCible;

    @CsvBindByName(column = "numero_ordre")
    private int ordre;
}
