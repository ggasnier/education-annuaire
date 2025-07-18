package com.guillaumegasnier.education.annuaire.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NatureDataset {

    @CsvBindByName(column = "NATURE_UAI")
    private String code;

    @CsvBindByName(column = "LIBELLE_EDITION")
    private String nom;

    @CsvBindByName(column = "DATE_FERMETURE")
    private String dateFin;
}
