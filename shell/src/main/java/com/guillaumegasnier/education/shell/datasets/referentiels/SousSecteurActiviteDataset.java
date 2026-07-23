package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier:  unix_sous_secteur_activite_v460_utf8.csv
 */
@Getter
@Setter
public class SousSecteurActiviteDataset implements Dataset {

    @CsvBindByName(column = "code_ss_sect_activite")
    private int code;

    @CsvBindByName(column = "libelle_ss_sect_activite")
    private String nom;

    @CsvBindByName(column = "definition_ss_sect_activite")
    private String definition;

    @CsvBindByName(column = "code_sect_activite")
    private int codeSecteurActivite;
}
