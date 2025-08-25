package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementDataset implements Dataset {

    @CsvBindByName(column = "DEP")
    protected String code;

    @CsvBindByName(column = "LIBELLE")
    protected String nom;

    @CsvBindByName(column = "REG")
    protected String codeRegion;

}
