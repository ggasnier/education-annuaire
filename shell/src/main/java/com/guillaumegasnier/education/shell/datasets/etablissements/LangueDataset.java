package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * UAI
 * Libellé
 * Adresse
 * Enseignements
 * Langues
 * Code departement
 * Département
 * Code region
 * Région
 * Code academie
 * Académie
 * Commune
 * Type d'établissement
 * Position
 * Secteur de l'établissement
 */
@Getter
@Setter
public class LangueDataset implements Dataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Enseignements")
    private String enseignement;

    @CsvBindByName(column = "Langues")
    private String langue;

    public String getEnseignement() {
        return enseignement.toLowerCase();
    }

    public String getLangue() {
        return langue.toLowerCase();
    }
}
