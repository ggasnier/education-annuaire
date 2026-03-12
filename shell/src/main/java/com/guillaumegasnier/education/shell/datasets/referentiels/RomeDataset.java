package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * Fichier : unix_referentiel_code_rome_v460_utf8.csv
 */
@Getter
@Setter
@Slf4j
public class RomeDataset extends AbtractRomeDataset {

    /**
     * Identifiant technique de la fiche du métier
     */
    @CsvBindByName(column = "code_fiche_metier")
    private int codeFicheMetier;

    /**
     * Libellé du métier
     */
    @CsvBindByName(column = "libelle_rome")
    private String nom;

    /**
     * Identifiant fonctionnel du métier dont peut dériver/hériter le métier
     */
    @CsvBindByName(column = "code_rome_parent")
    private String codeRomeParent;

}
