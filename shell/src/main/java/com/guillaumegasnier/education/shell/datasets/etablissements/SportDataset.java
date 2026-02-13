package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SportDataset implements Dataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Sections scolaires")
    private String sections;

    @CsvBindByName(column = "Pratique proposée")
    private String nomSport;

    public String getUai() {
        return uai.toUpperCase();
    }

    public List<String> getSectionList() {
        if (sections != null)
            return List.of(sections.split(","));
        if (nomSport != null)
            return List.of(nomSport.split(","));
        return List.of();
    }

}
