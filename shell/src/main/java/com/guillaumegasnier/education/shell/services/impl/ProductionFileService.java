package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import com.guillaumegasnier.education.shell.services.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("prod")
public class ProductionFileService implements FileService {

    @Override
    public Optional<BufferedReader> openFile(String url, Charset charset, String httpMethod) {
        if (httpMethod.equals("POST")) {
            try {
                InputStream rawInputStream;
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                String payload = "";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payload.getBytes(charset);
                    os.write(input, 0, input.length);
                }

                int status = conn.getResponseCode();
                int contentLength = conn.getContentLength();
                String contentType = conn.getContentType();

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
    public <T> List<T> importCSV(@NonNull SourcesDatasets source, Class<? extends T> type) {
        log.info("Début import {}", source.getUrl());

        List<T> result = new ArrayList<>();

        openFile(source.getUrl(), source.getCharset(), source.getHttpMethod()).ifPresentOrElse(reader -> {
            try (reader) {
                List<T> beans = new CsvToBeanBuilder<T>(reader)
                        .withType(type)
                        .withSeparator(source.getSeparator())
                        .build()
                        .parse();

                result.addAll(beans);

            } catch (Exception e) {
                log.error("Erreur pendant le parsing CSV : {}", e.getMessage(), e);
            }
        }, () -> log.error("Impossible de lire le fichier : {}", source.getUrl()));

        log.info("Fin import {}, lignes importées : {}", source.getUrl(), result.size());
        return result;
    }
}
