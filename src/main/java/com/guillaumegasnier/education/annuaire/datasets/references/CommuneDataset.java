package com.guillaumegasnier.education.annuaire.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommuneDataset {

    @CsvBindByName(column = "COM")
    protected String code;

    @CsvBindByName(column = "LIBELLE")
    protected String nom;

    @CsvBindByName(column = "DEP")
    protected String codeDepartement;
}
