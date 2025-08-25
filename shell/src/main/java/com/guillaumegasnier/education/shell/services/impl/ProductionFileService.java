package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.dto.CarifEtablissementResponse;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import com.guillaumegasnier.education.shell.services.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("prod")
public class ProductionFileService implements FileService {

    private final RestClient restClient;

    public ProductionFileService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<BufferedReader> openFile(@NonNull String url, @NonNull Charset charset, String httpMethod) {
        if (httpMethod.equals("POST")) {
            try {
                InputStream rawInputStream;
                HttpURLConnection conn;
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                String payload = "";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payload.getBytes(charset);
                    os.write(input, 0, input.length);
                }

//                int status = conn.getResponseCode();
//                int contentLength = conn.getContentLength();
//                String contentType = conn.getContentType();

//                log.info("HTTP POST → [{}]: Code: {}, Taille: {}, Type: {}", url, status, contentLength, contentType);

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
                URL url2 = new URL(url);
                rawInputStream = url2.openStream();
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
    public List<CarifEtablissementDataset> importJsonCarif(@NonNull SourcesDatasets source) {

        log.info("Début import {}", source.getNom());
        log.info("URL {}", source.getUrl());

        String scrollId = null;
        int totalFetched = 0;
        int page = 1;
        int totalRecupere = 0;
        final String scrollDuration = "5m";
        List<CarifEtablissementDataset> etablissementDatasetList = new ArrayList<>();

        do {
            CarifEtablissementResponse response;
            log.debug("Page : {}", page++);

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
                        .uri(String.format(source.getUrl() + "scroll?scroll=%s&scroll_id=%s", scrollDuration, URLEncoder.encode(scrollId, StandardCharsets.UTF_8)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(String.format("{\"scroll\": true,\"scroll_id\": \"%1$s\",\"activeQuery\":{\"scroll\": \"1m\",\"scroll_id\": \"%1$s\"}}", scrollId))
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

        log.info("Fin import {}, lignes importées : {}", source.getNom(), totalFetched);

        return etablissementDatasetList;
    }
}
