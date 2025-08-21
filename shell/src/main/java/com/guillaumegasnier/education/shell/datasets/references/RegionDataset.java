package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegionDataset implements Dataset {

    @CsvBindByName(column = "REG")
    private String code;

    @CsvBindByName(column = "LIBELLE")
    private String nom;
}
