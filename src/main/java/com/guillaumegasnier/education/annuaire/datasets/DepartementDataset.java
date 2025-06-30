package com.guillaumegasnier.education.annuaire.datasets;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementDataset {

    @CsvBindByName(column = "DEP")
    private String code;

    @CsvBindByName(column = "LIBELLE")
    private String nom;

    @CsvBindByName(column = "REG")
    private String codeRegion;

}
