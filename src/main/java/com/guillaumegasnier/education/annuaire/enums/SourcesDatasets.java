package com.guillaumegasnier.education.annuaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SourcesDatasets {

    EN_ETABS_OUVERTS("Annuaire de l'éducation", "https://www.data.gouv.fr/fr/datasets/r/b22f04bf-64a8-495d-b8bb-d84dbc4c7983", "datasets/fr-en-annuaire-education.csv", "utf-8", "csv"),
    ESR_ETABS_OUVERTS("", "https://www.data.gouv.fr/fr/datasets/r/bcc3229a-beb2-4077-a8d8-50a065dfbbfa", "datasets/fr-esr-principaux-etablissements-enseignement-superieur.csv", "utf-8", "csv"),
    CARIF_ETABS_OUVERTS("", "https.post://catalogue-apprentissage.intercariforef.org/api/v1/es/search/etablissements/_search?scroll=5m\n", "datasets/etablissements_carif.csv", "utf-8", "csv"),
    ACADEMIES("Liste des académies", "", "datasets/n_academie_et_assimile_.csv", "", ""),
    REGIONS("Liste des régions", "", "datasets/v_region_2025.csv", "", ""),
    DEPARTEMENTS("Liste des départements", "", "datasets/v_departement_2025.csv", "", ""),
    DEPARTEMENTS_COMER("Liste des départements (DOM TOM)", "", "datasets/v_comer_2025.csv", "", ""),
    ACADEMIES_DEPARTEMENTS("", "", "datasets/n_lien_academie_departement_.csv", "", ""),
    COMMUNES("Liste des communes", "https://www.data.gouv.fr/fr/datasets/r/91a95bee-c7c8-45f9-a8aa-f14cc4697545", "datasets/v_commune_2025.csv", "", ""),
    COMMUNES_COMER("Liste des communes (DOM TOM)", "", "datasets/v_commune_comer_2025.csv", "", ""),
    NATURES("Natures d'établissements", "", "datasets/n_nature_uai_.csv", "", ""),
    CONTRATS("Contrats d'établissements", "", "datasets/n_contrat_etablissement_.csv", "", ""),
    IPS_COLLEGES_1("IPS Collèges (2023 - )", "", "datasets/fr-en-ips-colleges-ap2023.csv", "", "");

    private final String nom;
    private final String url;
    private final String path;
    private final String encoding;
    private final String type;
}
