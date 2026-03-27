package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * Fichier : unix_texte_v460_utf8.csv
 */
@Getter
@Setter
public class RomeBlocDataset extends AbtractRomeDataset {

    @CsvBindByName(column = "code_rome")
    private String codeRome;

    /**
     * 3 : definition
     * <p>
     * 4 : acces_metier
     */
    @CsvBindByName(column = "code_compo_bloc")
    private int codeCompoBloc;

    /**
     * 3 : definition
     * <p>
     * 4 : acces_metier
     */
    @CsvBindByName(column = "libelle_type_texte")
    private String libelleTypeTexte;

    /**
     * Ordre
     */
    @CsvBindByName(column = "position_phrase")
    private int position;

    @CsvBindByName(column = "libelle_texte")
    private String texte;
}
