package com.guillaumegasnier.education.shell.datasets.references;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademieDepartementDataset implements Dataset {

    @CsvBindByName(column = "DEPARTEMENT_INSEE_3")
    private String codeDepartement;

    @CsvBindByName(column = "ACADEMIE_ET_ASSIMILE")
    private String codeAcademie;

    @CsvBindByName(column = "DATE_FERMETURE")
    private String dateFin;

    public String getCodeDepartement() {
        if (codeDepartement.startsWith("0"))
            return codeDepartement.substring(1, 3);
        return codeDepartement;
    }
}
