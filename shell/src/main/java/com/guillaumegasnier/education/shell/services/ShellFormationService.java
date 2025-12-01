package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellFormationService {

    String createOrUpdateCertificationsRncp(FICHES fiches);

    String createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets);

    String createOrUpdateFormationsCarif(@NonNull List<CarifFormationDataset> datasets);

    void createOrUpdateFormationsParcoursup(@NonNull List<ParcoursupFormationDataset> datasets);

    String createOrUpdateFormationsOnisepEsr(@NonNull List<OnisepFormationDataset> datasets);

    String createOrUpdateFormationsOnisepLheo(@NonNull LheoSubtype lheoSubtype);
}
