package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class AcademieDataset {

    @CsvBindByName(column = "ACADEMIE_ET_ASSIMILE")
    private String code;

    @CsvBindByName(column = "LIBELLE_70")
    private String nom;

    @CsvBindByName(column = "DATE_FERMETURE")
    private String dateFin;
}
