package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EuroscolDataset implements Dataset {

    @CsvBindByName(column = "RNE")
    private String uai;

    @CsvBindByName(column = "Nom_etablissement")
    private String nomEtablissement;
}
