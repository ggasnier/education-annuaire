package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SectionSportiveDataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Sections scolaires")
    private String sections;

    public List<String> getSectionList() {
        return List.of(sections.split(","));
    }
}
