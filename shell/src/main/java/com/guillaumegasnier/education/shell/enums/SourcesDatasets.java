package com.guillaumegasnier.education.shell.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor
public enum SourcesDatasets {

    // References
    PAYS("Pays", "https://www.data.gouv.fr/api/1/datasets/r/3580bf65-1d11-4574-a2ca-903d64ad41bd", "datasets/v_pays_territoire_2025.csv", Charset.forName("windows-1252"), ',', "GET"),
    REGIONS("Liste des régions", "https://www.data.gouv.fr/api/1/datasets/r/2486b351-5d85-4e1a-8d12-5df082c75104", "datasets/v_region_2025.csv", Charset.forName("windows-1252"), ',', "GET"),
    ACADEMIES("Liste des académies", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_ACADEMIE_ET_ASSIMILE&separator=,", "datasets/n_academie_et_assimile_.csv", Charset.forName("windows-1252"), ',', "POST"),
    ACADEMIES_DEPARTEMENTS("Lien entre académies et départements", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_LIEN_ACADEMIE_DEPARTEMENT&separator=,", "datasets/n_lien_academie_departement_.csv", Charset.forName("windows-1252"), ',', "POST"),
    DEPARTEMENTS("Liste des départements", "https://www.data.gouv.fr/api/1/datasets/r/54a8263d-6e2d-48d5-b214-aa17cc13f7a0", "datasets/v_departement_2025.csv", Charset.forName("windows-1252"), ',', "GET"),
    DEPARTEMENTS_COMER("Liste des départements (DOM TOM)", "https://www.data.gouv.fr/api/1/datasets/r/d4f01365-a3a7-41b2-9907-01a6cb42c9f2", "datasets/v_comer_2025.csv", Charset.forName("windows-1252"), ',', "GET"),
    COMMUNES("Liste des communes", "https://www.data.gouv.fr/fr/datasets/r/91a95bee-c7c8-45f9-a8aa-f14cc4697545", "datasets/v_commune_2025.csv", Charset.forName("windows-1252"), ',', "GET"),
    COMMUNES_COMER("Liste des communes (DOM TOM)", "https://www.data.gouv.fr/api/1/datasets/r/b797d73d-663c-4d3d-baf0-2d24b2d3a321", "datasets/v_commune_comer_2025.csv", Charset.forName("windows-1252"), ',', "GET"),

    // Etablissements
    EN_ETABS_OUVERTS("Annuaire de l'éducation", "https://www.data.gouv.fr/fr/datasets/r/b22f04bf-64a8-495d-b8bb-d84dbc4c7983", "datasets/fr-en-annuaire-education.csv", StandardCharsets.UTF_8, ',', null),
    ESR_ETABS_OUVERTS(null, "https://www.data.gouv.fr/fr/datasets/r/bcc3229a-beb2-4077-a8d8-50a065dfbbfa", "datasets/fr-esr-principaux-etablissements-enseignement-superieur.csv", StandardCharsets.UTF_8, ',', null),
    CARIF_ETABS_OUVERTS(null, "https.post://catalogue-apprentissage.intercariforef.org/api/v1/es/search/etablissements/_search?scroll=5m", "datasets/etablissements_carif.csv", StandardCharsets.UTF_8, ',', "POST"),
    NATURES("Natures d'établissements", null, "datasets/n_nature_uai_.csv", null, ',', null),
    CONTRATS("Contrats d'établissements", null, "datasets/n_contrat_etablissement_.csv", null, ',', null),
    IPS_COLLEGES_1("IPS Collèges (2023 - )", null, "datasets/fr-en-ips-colleges-ap2023.csv", null, ',', null);

    private final String nom;
    private final String url;
    private final String localPath;
    private final Charset charset;
    private final char separator;
    private final String httpMethod;
}
