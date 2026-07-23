package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_item_v460_utf8.csv
 */
@Getter
@Setter
public class ItemDataset implements Dataset {

    @CsvBindByName(column = "code_ogr")
    private int codeOgr;

    @CsvBindByName(column = "libelle")
    private String libelle;

    @CsvBindByName(column = "code_rubrique")
    private int codeRubrique;

}
