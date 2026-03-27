package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommuneDataset implements Dataset {

    @CsvBindByName(column = "COM")
    protected String code;

    @CsvBindByName(column = "LIBELLE")
    protected String nom;

    @CsvBindByName(column = "DEP")
    protected String codeDepartement;
}
