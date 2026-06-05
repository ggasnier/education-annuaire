package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_arborescence_secteur_activite_v460_utf8.csv
 */
@Getter
@Setter
public class ArboresenceSecteurActiviteDataset implements Dataset {

    //"code_sect_activite",
    // "libelle_sect_activite",
    // "code_ss_sect_activite",
    // "libelle_ss_sect_activite",
    // "code_ogr_rome",
    @CsvBindByName(column = "code_rome")
    private String codeRome;
    // "libelle_rome",
    // "principal"
}
