package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.core.enums.OuiNon;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_referentiel_savoir_v461_utf8.csv
 */
@Getter
@Setter
public class SavoirDataset implements Dataset {

    @CsvBindByName(column = "code_ogr_savoir")
    protected int codeOgr;

    @CsvBindByName(column = "libelle_savoir")
    private String libelle;

    @CsvBindByName(column = "categorie_savoir")
    private String categorie;

    @CsvBindByName(column = "sous_categorie_savoir")
    private String sousCategorie;

    @CsvBindByName(column = "transition_eco")
    private String transitionEco;

    @CsvBindByName(column = "transition_num")
    private String transitionNum;

    private int codeRubrique;

    public OuiNon getTransitionEco() {
        if (transitionEco != null && transitionEco.equals("O")) {
            return OuiNon.O;
        } else {
            return OuiNon.N;
        }
    }
}
