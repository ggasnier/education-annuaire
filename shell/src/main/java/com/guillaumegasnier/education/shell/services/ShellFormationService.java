package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellFormationService {

    String createOrUpdateFormations(@NonNull List<CPFFormationDataset> datasets);

    String createOrUpdateFormationsOnisep(@NonNull List<OnisepFormationDataset> datasets);

    String createOrUpdateCertificationsRncp(FICHES fiches);

    String createOrUpdateFormationsCarif(@NonNull List<CarifFormationDataset> datasets);
}
