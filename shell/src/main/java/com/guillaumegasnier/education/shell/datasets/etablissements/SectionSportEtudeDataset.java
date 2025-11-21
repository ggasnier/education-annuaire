package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * UAI
 * Pratique proposée
 * Nom de l'établissement
 * Type d'établissement
 * Statut public ou prive
 * Nom de commune
 * Département
 * Académie
 * Région
 * Web
 * Mail
 * Fiche onisep
 * position
 * Code commune
 * Code département
 * Code Académie
 * Code Région
 */
@Getter
@Setter
@Deprecated
public class SectionSportEtudeDataset implements Dataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Pratique proposée")
    private String nomSport;

    public String getUai() {
        return uai.toUpperCase();
    }
}
