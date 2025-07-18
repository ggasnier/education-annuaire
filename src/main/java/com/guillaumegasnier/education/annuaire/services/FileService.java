package com.guillaumegasnier.education.annuaire.services;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FileService {

//    private final ImportMapper importMapper;
//
//    @Deprecated
//    private final Map<Class<?>, Function<?, ?>> mappers = new HashMap<>();
//
//    public FileService(ImportMapper importMapper) {
//        this.importMapper = importMapper;
//        mappers.put(EnEtablissementDataset.class, (Function<EnEtablissementDataset, EtablissementEntity>) importMapper::toEtablissementEntity);
//        mappers.put(RegionDataset.class, (Function<RegionDataset, RegionEntity>) importMapper::toRegionEntity);
//        mappers.put(DepartementDataset.class, (Function<DepartementDataset, DepartementEntity>) importMapper::toDepartementEntity);
//    }

    /**
     * Ouvre un fichier local ou distant avec l'encodage spécifié, en filtrant le BOM si nécessaire.
     *
     * @param filePathOrUrl Chemin local absolu ou URL HTTP(S) du fichier
     * @param charset       Encodage à utiliser (ex: StandardCharsets.UTF_8)
     * @return Un {@code Optional<BufferedReader>} encapsulant le lecteur du fichier, ou vide en cas d'erreur
     * <p>
     * Note : L'appelant est responsable de fermer le {@code BufferedReader}.
     */
    public Optional<BufferedReader> openFile(@NonNull String filePathOrUrl, @NonNull Charset charset) {
        try {
            InputStream rawInputStream;

            if (filePathOrUrl.startsWith("http://") || filePathOrUrl.startsWith("https://")) {
                // GET simple
                URL url = new URL(filePathOrUrl);
                rawInputStream = url.openStream();
            } else if (filePathOrUrl.startsWith("https.post://")) {
                // Appel POST simplifié, sans config avancée
                String url = filePathOrUrl.replaceFirst("https.post://", "https://");
                log.info("Méthode POST : {}", url);
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");

                // Exemple de charge utile (à adapter)
                String payload = "{\"size\":1000,\"query\":{\"bool\":{\"must\":[{\"bool\":{\"must\":[{\"term\":{\"published\":\"true\"}},{\"match_all\":{}}]}}]}}}";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payload.getBytes(charset);
                    os.write(input, 0, input.length);
                }

                int status = conn.getResponseCode();
                int contentLength = conn.getContentLength();
                String contentType = conn.getContentType();

                log.info("HTTP POST → [{}]: Code: {}, Taille: {}, Type: {}", url, status, contentLength, contentType);
                
                rawInputStream = conn.getInputStream();
            } else {
                // Lecture fichier local
                rawInputStream = Files.newInputStream(Paths.get(filePathOrUrl));
            }

            InputStream filteredInputStream = charset.equals(StandardCharsets.UTF_8)
                    ? BOMInputStream.builder()
                    .setInputStream(rawInputStream)
                    .setByteOrderMarks(ByteOrderMark.UTF_8)
                    .setInclude(false)
                    .get()
                    : rawInputStream;

            return Optional.of(new BufferedReader(new InputStreamReader(filteredInputStream, charset)));

        } catch (IOException e) {
            log.error("Erreur lors de l'ouverture du fichier/URL [{}] : {}", filePathOrUrl, e.getMessage(), e);
            return Optional.empty();
        }
    }

    /**
     * Importe un fichier CSV et le convertit en objets via un mapper.
     *
     * @param filePath  Chemin du fichier CSV
     * @param type      Classe du type cible à parser (annoté avec @CsvBindByName ou équivalent)
     * @param separator Séparateur CSV (ex: ';' ou ',')
     * @param <T>       Type de l’objet CSV source
     * @return Liste des objets mappés
     */
    public <T> List<T> importCSV(String filePath, Class<? extends T> type, char separator, @NonNull Charset charset) {
        log.info("Début import {}", filePath);

        List<T> result = new ArrayList<>();

        openFile(filePath, charset).ifPresentOrElse(reader -> {
            try (reader) {
                List<T> beans = new CsvToBeanBuilder<T>(reader)
                        .withType(type)
                        .withSeparator(separator)
                        .build()
                        .parse();

                result.addAll(beans);

            } catch (Exception e) {
                log.error("Erreur pendant le parsing CSV : {}", e.getMessage(), e);
            }
        }, () -> log.error("Impossible de lire le fichier : {}", filePath));

        log.info("Fin import {}, lignes importées : {}", filePath, result.size());
        return result;
    }

}
