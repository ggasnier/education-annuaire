package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellFormationService {
    String createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets);

    String createOrUpdateFormationsOnisepIdf();
}
