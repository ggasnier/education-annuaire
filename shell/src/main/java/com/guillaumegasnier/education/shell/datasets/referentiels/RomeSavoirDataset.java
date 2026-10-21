package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RomeSavoirDataset implements Dataset {

    @CsvBindByName(column = "code_ogr")
    protected int codeOgr;

    @CsvBindByName(column = "code_rome")
    private String codeRome;

    @CsvBindByName(column = "coeur_metier")
    private String coeurMetier;

    public String getCoeurMetier() {
        if (coeurMetier == null || coeurMetier.isBlank()) {
            return "S";
        }
        return "P";
    }
}
