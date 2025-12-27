package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademieDataset implements Dataset {

    @CsvBindByName(column = "ACADEMIE_ET_ASSIMILE")
    private String code;

    @CsvBindByName(column = "LIBELLE_70")
    private String nom;
    
    @CsvBindByName(column = "DATE_FERMETURE")
    private String dateFin;

    public String getNom() {
        if (nom.length() > 50) return nom.substring(0, 50);
        return nom;
    }
}
