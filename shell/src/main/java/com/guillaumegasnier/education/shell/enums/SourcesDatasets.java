package com.guillaumegasnier.education.shell.enums;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2021Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2022Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2023Dataset;
import com.guillaumegasnier.education.shell.datasets.references.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor
public enum SourcesDatasets {

    // References
    PAYS("Pays", "https://www.data.gouv.fr/api/1/datasets/r/3580bf65-1d11-4574-a2ca-903d64ad41bd", "datasets/v_pays_territoire_2025.csv", StandardCharsets.UTF_8, ',', "GET", PaysDataset.class),
    REGIONS("Liste des régions", "https://www.data.gouv.fr/api/1/datasets/r/2486b351-5d85-4e1a-8d12-5df082c75104", "datasets/v_region_2025.csv", StandardCharsets.UTF_8, ',', "GET", RegionDataset.class),
    ACADEMIES("Liste des académies", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_ACADEMIE_ET_ASSIMILE&separator=,", "datasets/n_academie_et_assimile_.csv", Charset.forName("windows-1252"), ',', "POST", AcademieDataset.class),
    ACADEMIES_DEPARTEMENTS("Lien entre académies et départements", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_LIEN_ACADEMIE_DEPARTEMENT&separator=,", "datasets/n_lien_academie_departement_.csv", Charset.forName("windows-1252"), ',', "POST", AcademieDepartementDataset.class),
    DEPARTEMENTS("Liste des départements", "https://www.data.gouv.fr/api/1/datasets/r/54a8263d-6e2d-48d5-b214-aa17cc13f7a0", "datasets/v_departement_2025.csv", StandardCharsets.UTF_8, ',', "GET", DepartementDataset.class),
    DEPARTEMENTS_COMER("Liste des départements (DOM TOM)", "https://www.data.gouv.fr/api/1/datasets/r/d4f01365-a3a7-41b2-9907-01a6cb42c9f2", "datasets/v_comer_2025.csv", StandardCharsets.UTF_8, ',', "GET", DepartementOutreMerDataset.class),
    COMMUNES("Liste des communes", "https://www.data.gouv.fr/fr/datasets/r/91a95bee-c7c8-45f9-a8aa-f14cc4697545", "datasets/v_commune_2025.csv", StandardCharsets.UTF_8, ',', "GET", CommuneDataset.class),
    COMMUNES_COMER("Liste des communes (DOM TOM)", "https://www.data.gouv.fr/api/1/datasets/r/b797d73d-663c-4d3d-baf0-2d24b2d3a321", "datasets/v_commune_comer_2025.csv", StandardCharsets.UTF_8, ',', "GET", CommuneOutreMerDataset.class),

    // Etablissements
    EN_ETABS_OUVERTS("Annuaire de l'éducation", "https://www.data.gouv.fr/fr/datasets/r/b22f04bf-64a8-495d-b8bb-d84dbc4c7983", "datasets/fr-en-annuaire-education.csv", StandardCharsets.UTF_8, ';', "GET", EnEtablissementDataset.class),
    EN_ETABS_FERMES("Etablissements fermés", "https://www.data.gouv.fr/api/1/datasets/r/8cc21ef1-911a-46c9-9145-a865f320be48", "datasets/fr-en-etablissements-fermes.csv", StandardCharsets.UTF_8, ';', "GET", EtablissementFermeDataset.class),
    ESR_ETABS_OUVERTS("Etablissements de l'enseignement supérieur", "https://www.data.gouv.fr/api/1/datasets/r/bcc3229a-beb2-4077-a8d8-50a065dfbbfa", "datasets/fr-esr-principaux-etablissements-enseignement-superieur.csv", StandardCharsets.UTF_8, ';', "GET", EsrEtablissementDataset.class),
    CARIF_ETABS_OUVERTS("Liste des organismes", "https://catalogue-apprentissage.intercariforef.org/api/v1/es/search/etablissements/", "datasets/etablissements_carif.csv", StandardCharsets.UTF_8, ',', "POST", CarifEtablissementDataset.class),
    ONISEP_ETABS_SUP("Idéo-Structures d'enseignement supérieur", "https://api.opendata.onisep.fr/downloads/5fa586da5c4b6/5fa586da5c4b6.csv", "datasets/etablissements_onisep_sup.csv", StandardCharsets.UTF_8, ';', "GET", OnisepEtablissementSupDataset.class),
    NATURES("Natures d'établissements", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_NATURE_UAI&separator=,", "datasets/n_nature_uai_.csv", Charset.forName("windows-1252"), ',', "POST", NatureDataset.class),
    CONTRATS("Contrats d'établissements", "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_CONTRAT_ETABLISSEMENT&separator=,", "datasets/n_contrat_etablissement_.csv", Charset.forName("windows-1252"), ',', "POST", ContratDataset.class),
    IPS_COLLEGES_1("IPS Collèges (depuis 2023)", "https://www.data.gouv.fr/api/1/datasets/r/3c7fe02a-702b-4276-acfb-07d0572c18a2", "datasets/fr-en-ips-colleges-ap2023.csv", StandardCharsets.UTF_8, ';', "GET", IPSCollege2023Dataset.class),
    IPS_COLLEGES_2("IPS Collèges (2022)", "https://www.data.gouv.fr/api/1/datasets/r/28e511a7-af0d-48c7-a8bb-2f38ec003f49", "datasets/fr-en-ips-colleges-ap2022.csv", StandardCharsets.UTF_8, ';', "GET", IPSCollege2022Dataset.class),
    IPS_COLLEGES_3("IPS Collèges (2016 - 2021)", "https://www.data.gouv.fr/api/1/datasets/r/b63bd365-c589-48e4-b7d8-9e4f5db133c5", "datasets/fr-en-ips_colleges.csv", StandardCharsets.UTF_8, ';', "GET", IPSCollege2021Dataset.class),

    SECTIONS_SPORTIVES("Sections Sportives Scolaires", "https://www.data.gouv.fr/api/1/datasets/r/c479b9b8-8224-46ef-b2f4-f6a3468726fc", null, StandardCharsets.UTF_8, ';', "GET", SectionSportiveDataset.class),
    SECTIONS_INTERNATIONALES("Sections internationales", "https://www.data.gouv.fr/api/1/datasets/r/877befc2-2b3c-4e27-902b-abcf7f1e800f", "datasets/fr-en-sections-internationales.csv", StandardCharsets.UTF_8, ';', "GET", SectionInternationaleDataset.class),
    LANGUES("Offre de langues dans les collèges et lycées", "https://www.data.gouv.fr/api/1/datasets/r/60ca51e3-05b7-4b90-9433-ebbd68803f8c", null, StandardCharsets.UTF_8, ';', "GET", LangueDataset.class),
    BINATIONALES("Etablissements avec sections binationales (Abibac, Bachibac et Esabac)", "https://www.data.gouv.fr/api/1/datasets/r/b4856bda-e942-4ad9-b016-a64dd281b558", null, StandardCharsets.UTF_8, ',', "GET", SectionBinationaleDataset.class),
    SPECIALITES("Idéo-Enseignements de spécialité de première générale", "https://www.data.gouv.fr/api/1/datasets/r/2f4a36ad-b3fc-429d-92c1-6cd9c37cb900", null, StandardCharsets.UTF_8, ';', "GET", SpecialitePremiereDataset.class),
    NULL(null, null, null, null, ',', null, null);

    // Formations

    private final String nom;
    private final String url;
    private final String localPath;
    private final Charset charset;
    private final char separator;
    private final String httpMethod;
    private final Class<? extends Dataset> datasetClass;

}
