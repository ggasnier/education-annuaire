package com.guillaumegasnier.education.shell.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementResponse;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationResponse;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import com.guillaumegasnier.education.shell.services.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("prod")
public class ProductionFileService implements FileService {

    private final RestClient restClient;

    @Value("${app.datasets}")
    private String datasetsPath;

    public ProductionFileService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<BufferedReader> openFile(@NonNull String url, @NonNull Charset charset, @NonNull String httpMethod) {
        if (httpMethod.equals("POST")) {
            try {
                InputStream rawInputStream;
                HttpURLConnection conn;
                var uri = new URI(url);
                conn = (HttpURLConnection) uri.toURL().openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                String payload = "";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payload.getBytes(charset);
                    os.write(input, 0, input.length);
                }

                rawInputStream = conn.getInputStream();

                InputStream filteredInputStream = charset.equals(StandardCharsets.UTF_8)
                        ? BOMInputStream.builder()
                        .setInputStream(rawInputStream)
                        .setByteOrderMarks(ByteOrderMark.UTF_8)
                        .setInclude(false)
                        .get()
                        : rawInputStream;

                return Optional.of(new BufferedReader(new InputStreamReader(filteredInputStream, charset)));
            } catch (Exception e) {
                log.error(e.getMessage());
                return Optional.empty();
            }
        } else {
            try {
                InputStream rawInputStream;
                var uri = new URI(url);
                rawInputStream = uri.toURL().openStream();
                InputStream filteredInputStream = charset.equals(StandardCharsets.UTF_8)
                        ? BOMInputStream.builder()
                        .setInputStream(rawInputStream)
                        .setByteOrderMarks(ByteOrderMark.UTF_8)
                        .setInclude(false)
                        .get()
                        : rawInputStream;

                return Optional.of(new BufferedReader(new InputStreamReader(filteredInputStream, charset)));
            } catch (Exception e) {
                log.error(e.getMessage());
                return Optional.empty();
            }
        }
    }

    @Override
    public <T extends Dataset> List<T> importCSV(@NonNull SourcesDatasets source) {
        log.info("Début import {}", source.getNom());
        log.info("URL {}", source.getUrl());

        List<T> result = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) source.getDatasetClass();

        // Sauvegarde du CSV
        openFile(source.getUrl(), source.getCharset(), source.getHttpMethod()).ifPresentOrElse(reader -> {
            Path outPath = Paths.get(datasetsPath, source.getSource().name().toLowerCase(), source.getLocalPath());
            try {
                Files.createDirectories(outPath.getParent());
                try (BufferedWriter writer = Files.newBufferedWriter(outPath, source.getCharset())) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                log.info("CSV sauvegardé dans : {}", outPath.toAbsolutePath());
            } catch (Exception e) {
                log.error("Erreur lors de la sauvegarde du CSV brut : {}", e.getMessage(), e);
            }
        }, () -> log.error("Impossible de lire le fichier : {}", source.getUrl()));

        openFile(source.getUrl(), source.getCharset(), source.getHttpMethod()).ifPresentOrElse(reader -> {
            try (reader) {
                List<T> beans = new CsvToBeanBuilder<T>(reader)
                        .withType(clazz)
                        .withSeparator(source.getSeparator())
                        .build()
                        .parse();

                result.addAll(beans);

            } catch (Exception e) {
                log.error("Erreur pendant le parsing CSV : {}", e.getMessage(), e);
            }
        }, () -> log.error("Impossible de lire le fichier : {}", source.getUrl()));

        log.info("Fin import {}, lignes importées : {}", source.getNom(), result.size());
        return result;
    }

    @Override
    public List<CarifEtablissementDataset> importCarifEtablissements(@NonNull SourcesDatasets source) {
        log.info("Début import {}", source.getNom());
        log.info("URL {}", source.getUrl());

        String scrollId = null;
        int totalFetched = 0;
        int totalRecupere = 0;
        final String scrollDuration = "5m";
        List<CarifEtablissementDataset> etablissementDatasetList = new ArrayList<>();

        do {
            CarifEtablissementResponse response;

            if (scrollId == null) {
                // 1er appel
                response = restClient.post()
                        .uri(source.getUrl() + "_search?scroll=" + scrollDuration)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body("{\"size\":1000,\"query\":{\"bool\":{\"must\":[{\"bool\":{\"must\":[{\"term\":{\"published\":\"true\"}},{\"match_all\":{}}]}}]}}}")
                        .retrieve()
                        .body(CarifEtablissementResponse.class);
            } else {
                // appels suivants
                response = restClient.post()
                        .uri(String.format(source.getUrl() + "scroll?scroll=%s&scroll_id=%s", scrollDuration,
                                URLEncoder.encode(scrollId, StandardCharsets.UTF_8)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(String.format(
                                "{\"scroll\": true,\"scroll_id\": \"%1$s\",\"activeQuery\":{\"scroll\": \"1m\",\"scroll_id\": \"%1$s\"}}",
                                scrollId))
                        .retrieve()
                        .body(CarifEtablissementResponse.class);
            }

            try {
                if (response != null) {
                    scrollId = response.getScrollId();
                    totalRecupere = response.getHits().getHits().size();
                    for (CarifEtablissementResponse.Hit hit : response.getHits().getHits()) {
                        etablissementDatasetList.add(hit.getSource());
                    }
                    totalFetched += totalRecupere;
                } else {
                    log.error("Response null");
                    break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } while (totalRecupere != 0);

        saveResultAsJson(etablissementDatasetList, source);

        log.info("Fin import {}, lignes importées : {}", source.getNom(), totalFetched);

        return etablissementDatasetList;
    }

    @Override
    public List<CarifFormationDataset> importCarifFormations(@NonNull SourcesDatasets source) {
        log.info("Début import {}", source.getNom());
        log.info("URL {}", source.getUrl());

        String scrollId = null;
        int totalFetched = 0;
        int totalRecupere = 0;
        final String scrollDuration = "5m";
        List<CarifFormationDataset> formationDatasetList = new ArrayList<>();

        String body = """
                {
                  "size": 1000,
                  "query": {
                    "bool": {
                      "must": [
                        {
                          "bool": {
                            "must": [
                              {
                                "term": {
                                  "published": "true"
                                }
                              },
                              {
                                "bool": {
                                  "should": [
                                    {
                                      "terms": {
                                        "tags.keyword": [
                                          "2026"
                                        ]
                                      }
                                    }
                                  ]
                                }
                              }
                            ]
                          }
                        }
                      ]
                    }
                  }
                }
                """;

        do {
            CarifFormationResponse response;

            if (scrollId == null) {
                // 1er appel
                response = restClient.post()
                        .uri(source.getUrl() + "_search?scroll=" + scrollDuration)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(body)
                        .retrieve()
                        .body(CarifFormationResponse.class);
            } else {
                // appels suivants
                response = restClient.post()
                        .uri(String.format(source.getUrl() + "scroll?scroll=%s&scroll_id=%s", scrollDuration,
                                URLEncoder.encode(scrollId, StandardCharsets.UTF_8)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(String.format(
                                "{\"scroll\": true,\"scroll_id\": \"%1$s\",\"activeQuery\":{\"scroll\": \"1m\",\"scroll_id\": \"%1$s\"}}",
                                scrollId))
                        .retrieve()
                        .body(CarifFormationResponse.class);
            }

            try {
                if (response != null) {
                    scrollId = response.getScrollId();
                    totalRecupere = response.getHits().getHits().size();
                    for (CarifFormationResponse.Hit hit : response.getHits().getHits()) {
                        formationDatasetList.add(hit.getSource());
                    }
                    totalFetched += totalRecupere;
                } else {
                    log.error("Response null");
                    break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } while (totalRecupere != 0);

        saveResultAsJson(formationDatasetList, source);

        log.info("Fin import {}, lignes importées : {}", source.getNom(), totalFetched);

        return formationDatasetList;
    }

//    @Override
//    public FICHES importXmlFromZip(@NonNull SourcesDatasets sourcesDatasets) {
//        log.info("Début import {}", sourcesDatasets.getNom());
//
//        try {
//            var uri = new URI(sourcesDatasets.getUrl());
//            try (InputStream inputStream = uri.toURL().openStream();
//                 java.util.zip.ZipInputStream zipInputStream = new java.util.zip.ZipInputStream(inputStream)) {
//
//                java.util.zip.ZipEntry entry;
//                while ((entry = zipInputStream.getNextEntry()) != null) {
//                    if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
//                        Path outPath = Paths.get("datasets", sourcesDatasets.getSource().name().toLowerCase(),
//                                sourcesDatasets.getLocalPath());
//                        Files.createDirectories(outPath.getParent());
//
//                        try (OutputStream outputStream = Files.newOutputStream(outPath)) {
//                            byte[] buffer = new byte[8192];
//                            int bytesRead;
//                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
//                                outputStream.write(buffer, 0, bytesRead);
//                            }
//                        }
//                        log.info("Fichier XML enregistré dans : {}", outPath.toAbsolutePath());
//
//                        JAXBContext context = JAXBContext.newInstance(FICHES.class);
//                        Unmarshaller unmarshaller = context.createUnmarshaller();
//                        JAXBElement<FICHES> jaxbElement = unmarshaller
//                                .unmarshal(new StreamSource(outPath.toFile()), FICHES.class);
//
//                        return jaxbElement.getValue();
//                    }
//                }
//
//                log.error("Aucun fichier XML trouvé dans le ZIP");
//                return null;
//            }
//        } catch (Exception e) {
//            log.error("Erreur lors de l'import du fichier LHEO : {}", e.getMessage(), e);
//            return null;
//        }
//    }

    @Override
    public FICHES importXmlFromZip(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import {}", sourcesDatasets.getNom());

        try {
            // Télécharger le fichier zip depuis sourcesDatasets.getUrl()
            //log.info("Téléchargement du fichier ZIP depuis : {}", sourcesDatasets.getUrl());
            URL url = new URL(sourcesDatasets.getUrl());

            try (InputStream inputStream = url.openStream();
                 java.util.zip.ZipInputStream zipInputStream = new java.util.zip.ZipInputStream(inputStream)) {

                // Extraire du fichier zip le fichier xml (il y en a un seul)
                java.util.zip.ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
                        log.info("Fichier XML trouvé : {}", entry.getName());

                        //if (!entry.getName().equals(sourcesDatasets.getLocalPath()))
                        //    log.warn("Erreur de paramétrage sur le fichier local : {} vs {}", entry.getName(), sourcesDatasets.getLocalPath());

                        // Enregistrer le fichier en local
                        Path outPath = Paths.get(datasetsPath, sourcesDatasets.getSource().name().toLowerCase(),
                                sourcesDatasets.getLocalPath());
                        Files.createDirectories(outPath.getParent());

                        // Copier le contenu du zip vers le fichier local
                        try (OutputStream outputStream = Files.newOutputStream(outPath)) {
                            byte[] buffer = new byte[8192];
                            int bytesRead;
                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                        log.info("Fichier XML enregistré dans : {}", outPath.toAbsolutePath());

                        // Renvoyer le contenu du fichier xml dans la classe FICHES avec JAXB
                        JAXBContext context = JAXBContext.newInstance(FICHES.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        JAXBElement<FICHES> jaxbElement = unmarshaller
                                .unmarshal(new StreamSource(outPath.toFile()), FICHES.class);
                        //log.info("Fichier XML parsé avec succès");

                        return jaxbElement.getValue();
                    }
                }

                log.error("Aucun fichier XML trouvé dans le ZIP");
                return null;
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'import du fichier LHEO : {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public LheoSubtype importLheoSubtypeFromZip(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import {}", sourcesDatasets.getNom());

        try {
            var uri = new URI(sourcesDatasets.getUrl());
            try (InputStream inputStream = uri.toURL().openStream();
                 java.util.zip.ZipInputStream zipInputStream = new java.util.zip.ZipInputStream(inputStream)) {

                java.util.zip.ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
                        log.info("Fichier XML trouvé : {}", entry.getName());

                        if (!entry.getName().equals(sourcesDatasets.getLocalPath()))
                            log.warn("Erreur de paramétrage sur le fichier local : {} vs {}", entry.getName(), sourcesDatasets.getLocalPath());

                        Path outPath = Paths.get(datasetsPath, sourcesDatasets.getSource().name().toLowerCase(),
                                sourcesDatasets.getLocalPath());
                        Files.createDirectories(outPath.getParent());

                        try (OutputStream outputStream = Files.newOutputStream(outPath)) {
                            byte[] buffer = new byte[8192];
                            int bytesRead;
                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                        log.info("Fichier XML enregistré dans : {}", outPath.toAbsolutePath());

                        JAXBContext context = JAXBContext.newInstance(LheoSubtype.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        JAXBElement<LheoSubtype> jaxbElement = unmarshaller
                                .unmarshal(new StreamSource(outPath.toFile()), LheoSubtype.class);

                        return jaxbElement.getValue();
                    }
                }

                log.error("Aucun fichier XML trouvé dans le ZIP");
                return null;
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'import du fichier LHEO : {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public <T> void saveResultAsJson(List<T> result, @NonNull SourcesDatasets sourcesDatasets) {
        Path outPath = Paths.get(datasetsPath, sourcesDatasets.getSource().name().toLowerCase(),
                sourcesDatasets.getLocalPath());

        if (result == null || result.isEmpty()) {
            log.warn("Aucun résultat à sauvegarder pour la source: {}", sourcesDatasets);
            return;
        }

        try {
            Files.createDirectories(outPath.getParent());
        } catch (IOException e) {
            log.error("Impossible de créer un fichier pour {}: {}", sourcesDatasets, e.getMessage());
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // Désactiver l'erreur pour les beans vides
            objectMapper.findAndRegisterModules(); // Pour supporter LocalDateTime, etc.

            objectMapper.writeValue(outPath.toFile(), result);

            log.info("Résultat sauvegardé dans : {}", outPath.toAbsolutePath());
            log.info("Taille du fichier : {} Mo", Files.size(outPath) / 1024 / 1024);

        } catch (IOException e) {
            log.error("Erreur lors de la sauvegarde du fichier JSON : {}", e.getMessage(), e);
        }
    }

    @Override
    public List<RomeDataset> importCSVFromZip(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import {}", sourcesDatasets.getNom());

        try {
            var uri = new URI(sourcesDatasets.getUrl());
            try (InputStream inputStream = uri.toURL().openStream();
                 java.util.zip.ZipInputStream zipInputStream = new java.util.zip.ZipInputStream(inputStream)) {

                java.util.zip.ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (!entry.isDirectory() && entry.getName().equals(sourcesDatasets.getLocalPath())) {

                        Path outPath = Paths.get(datasetsPath, sourcesDatasets.getSource().name().toLowerCase(),
                                sourcesDatasets.getLocalPath());
                        Files.createDirectories(outPath.getParent());

                        // Copier le contenu du zip vers le fichier local
                        try (OutputStream outputStream = Files.newOutputStream(outPath)) {
                            byte[] buffer = new byte[8192];
                            int bytesRead;
                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                        log.info("Fichier csv enregistré dans : {}", outPath.toAbsolutePath());

                        List<RomeDataset> result = new ArrayList<>();

                        try (BufferedReader reader = Files.newBufferedReader(outPath, sourcesDatasets.getCharset())) {
                            List<RomeDataset> beans = new CsvToBeanBuilder<RomeDataset>(reader)
                                    .withType(RomeDataset.class)
                                    .withSeparator(sourcesDatasets.getSeparator())
                                    .build()
                                    .parse();

                            result.addAll(beans);
                        } catch (Exception e) {
                            log.error("Erreur pendant le parsing CSV : {}", e.getMessage(), e);
                        }

                        return result;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'import du fichier ZIP : {}", e.getMessage(), e);
            return null;
        }

        return List.of();
    }
}
