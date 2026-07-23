package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InternatDataset implements Dataset {

    @EqualsAndHashCode.Include
    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "lits disponibles 2024-2025")
    private int disponible2024;
    @CsvBindByName(column = "lits occupés 2024-2025")
    private int occupes2024;

    @CsvBindByName(column = "lits disponibles 2023-2024")
    private int disponible2023;
    @CsvBindByName(column = "lits occupés 2023-2024")
    private int occupes2023;

    @CsvBindByName(column = "lits disponibles 2022-2023")
    private int disponible2022;
    @CsvBindByName(column = "lits occupés 2022-2023")
    private int occupes2022;

    @CsvBindByName(column = "lits disponibles 2021-2022")
    private int disponible2021;
    @CsvBindByName(column = "lits occupés 2021-2022")
    private int occupes2021;

    @CsvBindByName(column = "lits disponibles 2020-2021")
    private int disponible2020;
    @CsvBindByName(column = "lits occupés 2020-2021")
    private int occupes2020;

    // Taux d'occupation 2024-2025;
    // Taux d'occupation 2023-2024;
    // Taux d'occupation 2022-2023;
    // Taux d'occupation 2021-2022;
    // Taux d'occupation 2020-2021;

    // Nom du TER
    // TER

    // Appellation officielle
    // Libellé de la commune
    // Latitude WGS84
    // Longitude WGS84
    // Libellé de la nature de l'UAI
    // Libellé du département ou de la collectivité
    // Libellé de la région;Libellé de l'académie
    // Latitude et longitude WGS84;Sigle de l'UAI
    // Web
    // Arts
    // Cinéma
    // Théâtre
    // Sport
    // Internationale
    // Européenne
    // Appartenance_Education_Prioritaire
    // Fiche_onisep
    // Sections spécifiques
    // Libellé cité éducative
    // Niveau de labellisation G2030

    // Session
    // Cité éducative
    // G2030
    // Dispositifs
}
