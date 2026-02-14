package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import com.guillaumegasnier.education.shell.services.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
@Profile("local")
public class LocalFileService implements FileService {

    @Value("${app.datasets")
    private String datasetsPath = "";

    @Override
    public Optional<BufferedReader> openFile(@NonNull String filePath, @NonNull Charset charset, @NonNull String httpMethod) {
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
        log.info("Début import csv {}", sourcesDatasets.getLocalPath());

        Path outPath = Paths.get("datasets", sourcesDatasets.getSource().name().toLowerCase(), sourcesDatasets.getLocalPath());

        List<T> result = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) sourcesDatasets.getDatasetClass();

        openFile(String.valueOf(outPath), StandardCharsets.UTF_8, "GET").ifPresentOrElse(reader -> {
            try (reader) {
                List<T> beans = new CsvToBeanBuilder<T>(reader)
                        .withType(clazz)
                        .withSeparator(sourcesDatasets.getSeparator())
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
    public <T> void saveResultAsJson(List<T> result, @NonNull SourcesDatasets sourcesDatasets) {
        log.error("Méthode saveResultAsJson non implémentée car inutile");
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
    public FICHES importXmlFromZip(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import zip {}", sourcesDatasets.getLocalPath());

        Path outPath = Paths.get("datasets", sourcesDatasets.getSource().name().toLowerCase(), sourcesDatasets.getLocalPath());

        if (!Files.exists(outPath)) {
            log.error("Le fichier xml {} n'existe pas", outPath.getFileName());
            return null;
        }

        try (InputStream inputStream = Files.newInputStream(outPath)) {
            JAXBContext context = JAXBContext.newInstance(FICHES.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<FICHES> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), FICHES.class);

            return jaxbElement.getValue();

        } catch (JAXBException | IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    @Override
    public LheoSubtype importLheoSubtypeFromZip(@NonNull SourcesDatasets sourcesDatasets) {
        log.info("Début import zip {}", sourcesDatasets.getLocalPath());

        Path outPath = Paths.get("datasets", sourcesDatasets.getSource().name().toLowerCase(), sourcesDatasets.getLocalPath());

        if (!Files.exists(outPath)) {
            log.error("Le fichier xml {} n'existe pas", outPath.getFileName());
            return null;
        }

        try (InputStream inputStream = Files.newInputStream(outPath)) {
            JAXBContext context = JAXBContext.newInstance(LheoSubtype.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<LheoSubtype> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), LheoSubtype.class);

            return jaxbElement.getValue();

        } catch (JAXBException | IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    @Override
    public List<RomeDataset> importCSVFromZip(SourcesDatasets sourcesDatasets) {
        return List.of();
    }
}
