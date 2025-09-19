package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaysDataset implements Dataset {

    @CsvBindByName(column = "CODEISO2")
    private String code;

    @CsvBindByName(column = "LIBCOG")
    private String nom;
}
