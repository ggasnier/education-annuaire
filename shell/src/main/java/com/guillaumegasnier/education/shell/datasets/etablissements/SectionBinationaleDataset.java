package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * type_bac
 * nom_pays
 * nom_academie
 * uai
 * nom_etablissement
 * nom_commune
 * date_ouverture_section
 * date_premiere_session
 */
@Getter
@Setter
public class SectionBinationaleDataset implements Dataset {

    @CsvBindByName(column = "uai")
    private String uai;

    @CsvBindByName(column = "type_bac")
    private String typeBac;

    @CsvBindByName(column = "nom_pays")
    private String nomPays;

    @CsvBindByName(column = "nom_academie")
    private String nomAcademie;

    @CsvBindByName(column = "nom_etablissement")
    private String nomEtablissement;

    @CsvBindByName(column = "nom_commune")
    private String nomCommune;

    @Nullable
    public OptionEtablissement getOption() {
        if (typeBac == null) return null;

        return switch (typeBac) {
            case "Abibac" -> OptionEtablissement.ABIBAC;
            case "Bachibac" -> OptionEtablissement.BACHIBAC;
            case "Esabac" -> OptionEtablissement.ESABAC;
            default -> null;
        };
    }
}
