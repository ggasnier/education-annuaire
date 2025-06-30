package com.guillaumegasnier.education.annuaire.datasets;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegionDataset {

    @CsvBindByName(column = "REG")
    private String code;

    @CsvBindByName(column = "LIBELLE")
    private String nom;
}
