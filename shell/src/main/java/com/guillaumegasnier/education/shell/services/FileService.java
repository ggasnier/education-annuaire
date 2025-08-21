package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

public interface FileService {

    Optional<BufferedReader> openFile(@NonNull String url, @NonNull Charset charset, String httpMethod);

    <T extends Dataset> List<T> importCSV(@NonNull SourcesDatasets source);

    List<CarifEtablissementDataset> importJsonCarif(@NonNull SourcesDatasets source);

}
