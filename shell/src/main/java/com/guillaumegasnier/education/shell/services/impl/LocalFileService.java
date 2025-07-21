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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("local")
public class LocalFileService implements FileService {

    @Override
    public Optional<BufferedReader> openFile(@NonNull String filePath, @NonNull Charset charset, String httpMethod) {
        try {
            InputStream rawInputStream = Files.newInputStream(Paths.get(filePath));
            InputStream filteredInputStream = charset.equals(StandardCharsets.UTF_8)
                    ? BOMInputStream.builder()
                    .setInputStream(rawInputStream)
                    .setByteOrderMarks(ByteOrderMark.UTF_8)
                    .setInclude(false)
                    .get()
                    : rawInputStream;

            return Optional.of(new BufferedReader(new InputStreamReader(filteredInputStream, charset)));
        } catch (IOException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> List<T> importCSV(@NonNull SourcesDatasets source, Class<? extends T> type) {
        log.info("Début import {}", source.getLocalPath());

        List<T> result = new ArrayList<>();

        openFile(source.getLocalPath(), source.getCharset(), null).ifPresentOrElse(reader -> {
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
        }, () -> log.error("Impossible de lire le fichier : {}", source.getLocalPath()));

        log.info("Fin import {}, lignes importées : {}", source.getLocalPath(), result.size());
        return result;
    }
}
