package com.guillaumegasnier.education.annuaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SourcesDatasets {

    EN_ETABS_OUVERTS("Annuaire de l'éducation", "https://www.data.gouv.fr/fr/datasets/r/b22f04bf-64a8-495d-b8bb-d84dbc4c7983", "datasets/fr-en-annuaire-education.csv", "utf-8", "csv"),
    ESR_ETABS_OUVERTS("", "", "", "utf-8", "csv"),
    CARIF_ETABS_OUVERTS("", "", "", "utf-8", "csv"),
    REGIONS("Liste des régions", "", "datasets/v_region_2025.csv", "", ""),
    DEPARTEMENTS("Liste des départements", "", "datasets/v_departement_2025.csv", "", "");

    private final String nom;
    private final String url;
    private final String path;
    private final String encoding;
    private final String type;
}
