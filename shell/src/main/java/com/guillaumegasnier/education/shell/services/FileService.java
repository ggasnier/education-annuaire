package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

public interface FileService {

    Optional<BufferedReader> openFile(@NonNull String url, @NonNull Charset charset, @NonNull String httpMethod);

    <T extends Dataset> List<T> importCSV(@NonNull SourcesDatasets source);

    /**
     * Sauvegarde la liste de résultats dans un fichier JSON
     */
    <T> void saveResultAsJson(List<T> result, @NonNull SourcesDatasets sourcesDatasets);

    List<CarifEtablissementDataset> importCarifEtablissements(@NonNull SourcesDatasets source);

    List<CarifFormationDataset> importCarifFormations(@NonNull SourcesDatasets source);

    FICHES importXmlFromZip(@NonNull SourcesDatasets source);

    LheoSubtype importLheoSubtypeFromZip(@NonNull SourcesDatasets sourcesDatasets);

    default List<RomeDataset> importRome(@NonNull SourcesDatasets sourcesDatasets) {
        return List.of();
    }

    default void importRomeFromZip(@NonNull SourcesDatasets sourcesDatasets) {

    }

    default <T extends Dataset> List<T> importRomeData(@NonNull SourcesDatasets sourcesDatasets, String fileName, Class<T> datasetClass) {
        return List.of();
    }
}
