package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_themes_v460_utf8.csv
 */
@Getter
@Setter
public class ThemesDataset implements Dataset {

    @CsvBindByName(column = "code_theme")
    private String code;

    @CsvBindByName(column = "libelle_theme")
    private String nom;

    @CsvBindByName(column = "definition_theme")
    private String definition;
}
