package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_referentiel_competence_v460_utf8.csv
 */
@Getter
@Setter
public class CompetenceDataset implements Dataset {

    /**
     *
     */
    @CsvBindByName(column = "code_ogr")
    private int codeOgr;

    @CsvBindByName(column = "nature")
    private String nature;

    @CsvBindByName(column = "libelle_competence")
    private String libelleCompetence;

    @CsvBindByName(column = "definition")
    private String definition;

    @CsvBindByName(column = "code_ogr_macro_comp")
    private int codeOgrMacroCompetence;

    @CsvBindByName(column = "code_macro_comp")
    private String codeMacroCompetence;

    @CsvBindByName(column = "cat_comp")
    private String catCompetence;

    @CsvBindByName(column = "sous_cat_comp")
    private String sousCatCompetence;

    @CsvBindByName(column = "transition_eco")
    private String transitionEco;

    @CsvBindByName(column = "transition_num")
    private String transitionNum;

    @CsvBindByName(column = "libelle_heterogeneite")
    private String libelleHeterogeneite;

    @CsvBindByName(column = "libelle_origine")
    private String libelleOrigine;
}
