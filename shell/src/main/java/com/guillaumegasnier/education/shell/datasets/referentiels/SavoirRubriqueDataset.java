package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavoirRubriqueDataset implements Dataset {

    @CsvBindByName(column = "code_ogr")
    protected int codeOgr;

    @CsvBindByName(column = "libelle")
    private String libelle;

    @CsvBindByName(column = "code_rubrique")
    private int codeRubrique;
}
