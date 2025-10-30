package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import com.guillaumegasnier.education.shell.services.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
    public <T extends Dataset> List<T> importCSV(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import {}", sourcesDatasets.getLocalPath());

        Path outPath = Paths.get("datasets", sourcesDatasets.getSource().name().toLowerCase(), sourcesDatasets.getLocalPath());

        List<T> result = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) sourcesDatasets.getDatasetClass();

        openFile(String.valueOf(outPath), StandardCharsets.UTF_8, null).ifPresentOrElse(reader -> {
            try (reader) {
                List<T> beans = new CsvToBeanBuilder<T>(reader)
                        .withType(clazz)
                        .withSeparator(',')
                        .build()
                        .parse();

                result.addAll(beans);

            } catch (Exception e) {
                log.error("Erreur pendant le parsing CSV : {}", e.getMessage(), e);
            }
        }, () -> log.error("Impossible de lire le fichier : {}", outPath));

        log.info("Fin import {}, lignes importées : {}", outPath, result.size());
        return result;
    }

    @Override
    public List<CarifEtablissementDataset> importCarifEtablissements(@NonNull SourcesDatasets source) {
        return List.of();
    }

    @Override
    public List<CarifFormationDataset> importCarifFormations(@NonNull SourcesDatasets source) {
        return List.of();
    }

    @Override
    public FICHES importXmlFromZip(@NonNull SourcesDatasets source) {
        log.info("Début import {}", source.getLocalPath());

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(FICHES.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        if (!Files.exists(Path.of(source.getLocalPath()))) {
            log.error("Le fichier zip {} n'existe pas", source.getLocalPath());
            return null;
        }

        try (ZipFile zipFile = new ZipFile(source.getLocalPath())) {
            Enumeration<? extends ZipEntry> zipEnumeration = zipFile.entries();

            while (zipEnumeration.hasMoreElements()) {
                ZipEntry zipEntry = zipEnumeration.nextElement();
                try (InputStream inputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry))) {
                    return (FICHES) jaxbContext.createUnmarshaller().unmarshal(inputStream);
                }
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'ouverture du fichier zip {} : {}", source.getLocalPath(), e.getMessage(), e);
        }

        return null;
    }

}
