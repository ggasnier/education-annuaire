package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Fichier: unix_arborescence_competences_v460_utf8
 */
@Getter
@Setter
@ToString
public class ArborescenceCompetenceDataset {

    @CsvBindByName(column = "code_domaine_competence")
    private int codeDomaineCompetence;

    @CsvBindByName(column = "domaine_competence")
    private String domaineCompetence;

    @CsvBindByName(column = "code_enjeu")
    private String codeEnjeu;

    @CsvBindByName(column = "enjeu")
    private String enjeu;

    @CsvBindByName(column = "code_objectif")
    private String codeObjectif;

    @CsvBindByName(column = "objectif")
    private String objectif;

    @CsvBindByName(column = "code_macro_competence")
    private String codeMacroCompetence;

    @CsvBindByName(column = "code_ogr_macro_competence")
    private int codeOgrMacroCompetence;

    @CsvBindByName(column = "libelle_macro_competence")
    private String libelleMacroCompetence;

    @CsvBindByName(column = "code_ogr_competence")
    private int codeOgrCompetence;

    @CsvBindByName(column = "libelle_competence")
    private String libelleCompetence;
}
