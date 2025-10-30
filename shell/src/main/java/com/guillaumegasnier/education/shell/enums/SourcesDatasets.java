package com.guillaumegasnier.education.shell.enums;

import com.guillaumegasnier.education.core.enums.Sources;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationResponse;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSGlobalDataset;
import com.guillaumegasnier.education.shell.datasets.references.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.guillaumegasnier.education.core.enums.Sources.*;

@Getter
@RequiredArgsConstructor
public enum SourcesDatasets {

    // References
    PAYS("Pays", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/3580bf65-1d11-4574-a2ca-903d64ad41bd", "pays.csv", StandardCharsets.UTF_8, ',', "GET", PaysDataset.class),
    REGIONS("Liste des régions", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/2486b351-5d85-4e1a-8d12-5df082c75104", "regions.csv", StandardCharsets.UTF_8, ',', "GET", RegionDataset.class),
    ACADEMIES("Liste des académies", EN, "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_ACADEMIE_ET_ASSIMILE&separator=,", "academies.csv", Charset.forName("windows-1252"), ',', "POST", AcademieDataset.class),
    ACADEMIES_DEPARTEMENTS("Lien entre académies et départements", EN, "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_LIEN_ACADEMIE_DEPARTEMENT&separator=,", "academies_departements.csv", Charset.forName("windows-1252"), ',', "POST", AcademieDepartementDataset.class),
    DEPARTEMENTS("Liste des départements", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/54a8263d-6e2d-48d5-b214-aa17cc13f7a0", "departements.csv", StandardCharsets.UTF_8, ',', "GET", DepartementDataset.class),
    DEPARTEMENTS_COMER("Liste des départements (DOM TOM)", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/d4f01365-a3a7-41b2-9907-01a6cb42c9f2", "departements_outre_mer.csv", StandardCharsets.UTF_8, ',', "GET", DepartementOutreMerDataset.class),
    COMMUNES("Liste des communes", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/91a95bee-c7c8-45f9-a8aa-f14cc4697545", "communes.csv", StandardCharsets.UTF_8, ',', "GET", CommuneDataset.class),
    COMMUNES_COMER("Liste des communes (DOM TOM)", INSEE, "https://www.data.gouv.fr/api/1/datasets/r/b797d73d-663c-4d3d-baf0-2d24b2d3a321", "communes_outre_mer.csv", StandardCharsets.UTF_8, ',', "GET", CommuneOutreMerDataset.class),
    NATURES("Natures d'établissements", EN, "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_NATURE_UAI&separator=,", "natures.csv", Charset.forName("windows-1252"), ',', "POST", NatureDataset.class),
    CONTRATS("Contrats d'établissements", EN, "https://bcn.depp.education.fr/bcn/index.php/export/CSV?n=N_CONTRAT_ETABLISSEMENT&separator=,", "contrats.csv", Charset.forName("windows-1252"), ',', "POST", ContratDataset.class),

    // Etablissements
    EN_ETABS_OUVERTS("Annuaire de l'éducation", EN, "https://www.data.gouv.fr/api/1/datasets/r/b22f04bf-64a8-495d-b8bb-d84dbc4c7983", "etablissements_ouverts.csv", StandardCharsets.UTF_8, ';', "GET", EnEtablissementDataset.class),
    EN_ETABS_FERMES("Etablissements fermés", EN, "https://www.data.gouv.fr/api/1/datasets/r/8cc21ef1-911a-46c9-9145-a865f320be48", "etablissements_fermes.csv", StandardCharsets.UTF_8, ';', "GET", EtablissementFermeDataset.class),
    ESR_ETABS_OUVERTS("Etablissements de l'enseignement supérieur", ESR, "https://www.data.gouv.fr/api/1/datasets/r/bcc3229a-beb2-4077-a8d8-50a065dfbbfa", "etablissements_ouverts.csv", StandardCharsets.UTF_8, ';', "GET", EsrEtablissementDataset.class),
    CARIF_ETABS_OUVERTS("Liste des organismes", CARIF, "https://catalogue-apprentissage.intercariforef.org/api/v1/es/search/etablissements/", "etablissements_ouverts.json", StandardCharsets.UTF_8, ',', "POST", CarifEtablissementResponse.class),
    ONISEP_ETABS_SUP("Idéo-Structures d'enseignement supérieur", ONISEP, "https://api.opendata.onisep.fr/downloads/5fa586da5c4b6/5fa586da5c4b6.csv", "etablissements_ouverts_esr.csv", StandardCharsets.UTF_8, ';', "GET", OnisepEtablissementSupDataset.class),
    TRAVAIL_ETABS("Liste Publique des Organismes de Formation", MTEDS, "https://www.data.gouv.fr/api/1/datasets/r/ac59a0f5-fa83-4b82-bf12-3c5806d4f19f", "organismes_formations.csv", StandardCharsets.UTF_8, ';', "GET", TravailOrganismeFormationDataset.class),

    // IPS
    IPS_ECOLES_1("Indices de position sociale des écoles (2016-2021)", EN, "https://www.data.gouv.fr/api/1/datasets/r/fda33536-a0e0-4cad-b1bf-b88d2a7586e6", "ips_ecoles_2016.csv", StandardCharsets.UTF_8, ';', "GET", IPSGlobalDataset.class),
    IPS_ECOLES_2("Indices de position sociale des écoles (à partir de 2022)", EN, "https://www.data.gouv.fr/api/1/datasets/r/896c2e97-6a64-4521-bcab-b5b0d3cf7065", "ips_ecoles_2022.csv", StandardCharsets.UTF_8, ';', "GET", IPSGlobalDataset.class),
    IPS_COLLEGES_1("IPS Collèges (depuis 2023)", EN, "https://www.data.gouv.fr/api/1/datasets/r/3c7fe02a-702b-4276-acfb-07d0572c18a2", "ips_colleges_2023.csv", StandardCharsets.UTF_8, ';', "GET", IPSGlobalDataset.class),
    IPS_COLLEGES_2("IPS Collèges (2022)", EN, "https://www.data.gouv.fr/api/1/datasets/r/28e511a7-af0d-48c7-a8bb-2f38ec003f49", "ips_colleges_2022.csv", StandardCharsets.UTF_8, ';', "GET", IPSGlobalDataset.class),
    IPS_COLLEGES_3("IPS Collèges (2016 - 2021)", EN, "https://www.data.gouv.fr/api/1/datasets/r/b63bd365-c589-48e4-b7d8-9e4f5db133c5", "ips_colleges_2016.csv", StandardCharsets.UTF_8, ';', "GET", IPSGlobalDataset.class),
    // Lycées

    // Compléments
    SECTIONS_SPORTIVES("Sections Sportives Scolaires", EN, "https://www.data.gouv.fr/api/1/datasets/r/c479b9b8-8224-46ef-b2f4-f6a3468726fc", "sections_sportives.csv", StandardCharsets.UTF_8, ';', "GET", SectionSportiveDataset.class),
    SECTIONS_SPORT_ETUDES("Implantation des sections Sport-Etudes", EN, "https://www.data.gouv.fr/api/1/datasets/r/654ba2b1-fc50-4f36-a053-dbf9056ca53a", "sections_sports-etudes.csv", StandardCharsets.UTF_8, ';', "GET", SectionSportEtudeDataset.class),
    SECTIONS_INTERNATIONALES("Sections internationales", EN, "https://www.data.gouv.fr/api/1/datasets/r/877befc2-2b3c-4e27-902b-abcf7f1e800f", "sections-internationales.csv", StandardCharsets.UTF_8, ';', "GET", SectionInternationaleDataset.class),
    LANGUES("Offre de langues dans les collèges et lycées", EN, "https://www.data.gouv.fr/api/1/datasets/r/60ca51e3-05b7-4b90-9433-ebbd68803f8c", "langues.csv", StandardCharsets.UTF_8, ';', "GET", LangueDataset.class),
    BINATIONALES("Etablissements avec sections binationales (Abibac, Bachibac et Esabac)", AUTRE, "https://www.data.gouv.fr/api/1/datasets/r/b4856bda-e942-4ad9-b016-a64dd281b558", "sections_binationales.csv", StandardCharsets.UTF_8, ',', "GET", SectionBinationaleDataset.class),
    SPECIALITES("Idéo-Enseignements de spécialité de première générale", ONISEP, "https://www.data.gouv.fr/api/1/datasets/r/2f4a36ad-b3fc-429d-92c1-6cd9c37cb900", "specialites.csv", StandardCharsets.UTF_8, ';', "GET", SpecialitePremiereDataset.class),

    //Certifications

    CERTIFICATIONS_RNCP("Répertoire national des certifications professionnelles et répertoire spécifique", FC, "", "export-fiches-rncp-v4-1-2025-10-23.zip", StandardCharsets.UTF_8, ',', "GET", null),

    // Formations
    FORMATIONS_CPF("Moncompteformation - L'offre de formation", CDC, "https://www.data.gouv.fr/api/1/datasets/r/205a72c5-725a-40c0-9c39-073454bdd553", "formations.csv", StandardCharsets.UTF_8, ';', "GET", CPFFormationDataset.class),
    // parcourssup
    
    // carif oref
    CARIF_FORMATIONS("Liste des formations", CARIF, "https://catalogue-apprentissage.intercariforef.org/api/v1/es/search/formation/", "formations.json", StandardCharsets.UTF_8, ',', "POST", CarifFormationResponse.class),

    // ONISEP

    AF_ONISEP_ESR("Idéo-Actions de formation initiale-Univers enseignement supérieur", ONISEP, "https://www.data.gouv.fr/api/1/datasets/r/b4d1eec0-5be8-4a2e-9a3d-02669d0bdc8e", "formations_esr.csv", StandardCharsets.UTF_8, ';', "GET", OnisepFormationDataset.class),

    // Default
    NULL(null, AUTRE, null, null, null, ',', null, null);

    private final String nom;
    private final Sources source;
    private final String url;
    private final String localPath;
    private final Charset charset;
    private final char separator;
    private final String httpMethod;
    private final Class<?> datasetClass;

}
