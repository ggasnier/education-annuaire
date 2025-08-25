package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Stream;

/**
 * Rentrée scolaire
 * Academie
 * Section
 * UAI
 * Nom établissement
 * Niveau
 * Ville
 * Premiere_session_OIB_ou_DNB_option_internationale_a_venir
 * Ouverture de la section internationale
 * Latitude
 * Longitude
 * Position
 */
@Getter
@Setter
@ToString
public class SectionInternationaleDataset implements Dataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Section")
    private String section;

    @CsvBindByName(column = "Niveau")
    private String niveau;

    public List<String> getNiveaux() {
        return Stream.of(niveau.split("et")).map(String::trim).map(String::toUpperCase).toList();
    }
}
