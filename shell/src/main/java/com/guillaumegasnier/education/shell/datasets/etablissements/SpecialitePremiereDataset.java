package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Identifiant action de formation
 * Enseignements de spécialité de classe de 1ère générale
 * Identifiant et fiche onisep lieu de cours
 * UAI lieu de cours
 * Libellé lieu de cours
 * Adresse lieu de cours
 * Boîte postale lieu de cours
 * Code postal lieu de cours
 * Commune lieu de cours
 * Département lieu de cours
 * Région lieu de cours
 * Académie lieu de cours
 */
@Getter
@Setter
@ToString
public class SpecialitePremiereDataset implements Dataset {

    @CsvBindByName(column = "UAI lieu de cours")
    private String uai;

    @CsvBindByName(column = "Enseignements de spécialité de classe de 1ère générale")
    private String enseignements;

    public List<SpecialiteBac> getSpecialites() {
        return Arrays.stream(enseignements.split("/"))
                .filter(s -> !s.isBlank())
                .map(SpecialiteBac::transformation)
                .filter(Objects::nonNull)
                .toList();
    }

}
