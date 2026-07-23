package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_coherence_item_v460_utf8.csv
 */
@Getter
@Setter
public class CoherenceItemDataset implements Dataset {

    /**
     * Identifiant fonctionnel du métier
     */
    @CsvBindByName(column = "code_rome")
    private String codeRome;

    @CsvBindByName(column = "code_ogr")
    private int codeOgr;

    @CsvBindByName(column = "coeur_metier")
    private String coeurMetier;
}
