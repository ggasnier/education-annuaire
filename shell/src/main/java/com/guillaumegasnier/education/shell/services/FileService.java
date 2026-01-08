package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

public interface FileService {

    Optional<BufferedReader> openFile(@NonNull String url, @NonNull Charset charset, String httpMethod);

    <T extends Dataset> List<T> importCSV(@NonNull SourcesDatasets source);

    /**
     * Sauvegarde la liste de résultats dans un fichier JSON
     */
    default <T> void saveResultAsJson(List<T> result, @NonNull SourcesDatasets sourcesDatasets) {
    }

    /**
     * Sauvegarde la liste de résultats dans un fichier CSV
     */
    default <T> void saveResultAsCsv(List<T> result, @NonNull SourcesDatasets sourcesDatasets) {
    }

    List<CarifEtablissementDataset> importCarifEtablissements(@NonNull SourcesDatasets source);

    List<CarifFormationDataset> importCarifFormations(@NonNull SourcesDatasets source);

    FICHES importXmlFromZip(@NonNull SourcesDatasets source);

    LheoSubtype importLheoSubtypeFromZip(@NonNull SourcesDatasets sourcesDatasets);
}
