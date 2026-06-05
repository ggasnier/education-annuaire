package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_arborescence_centre_interet_v460_utf8.csv
 */
@Getter
@Setter
public class ArborescenceCentreInteretDataset implements Dataset {

    @CsvBindByName(column = "code_centre_interet")
    private int code;

    @CsvBindByName(column = "libelle_centre_interet")
    private String libelle;

    @CsvBindByName(column = "code_ogr_rome")
    private int codeOgrRome;

    @CsvBindByName(column = "code_rome")
    private String codeRome;

    @CsvBindByName(column = "libelle_rome")
    private String libelleRome;

    @CsvBindByName(column = "principal")
    private String principal;
}
