package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_arborescence_secteur_naf_v460_utf8.csv
 */
@Getter
@Setter
public class ArborescenceSecteurNafDataset implements Dataset {

    //"code_secteur_naf",
    // "libelle_secteur_naf",
    // "code_ogr_rome",
    @CsvBindByName(column = "code_rome")
    private String codeRome;
    // "libelle_rome"
}
