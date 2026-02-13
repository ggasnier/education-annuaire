package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class RomeDataset {
    @CsvBindByName(column = "code_rome")
    private String code;
    //code_fiche_metier
    //code_ogr
    @CsvBindByName(column = "libelle_rome")
    private String nom;
    //transition_eco
    //transition_num
    //transition_demo
    //emploi_reglemente
    //emploi_cadre
    //code_rome_parent
}
