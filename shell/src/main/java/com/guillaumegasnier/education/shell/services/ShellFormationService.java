package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellFormationService {

    void createOrUpdateOrganismes(@NonNull List<TravailOrganismeFormationDataset> datasets);

    void createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets);

    void createOrUpdateFormationsCarif(@NonNull List<CarifFormationDataset> datasets);

    void createOrUpdateFormationsParcoursup(@NonNull List<ParcoursupFormationDataset> datasets);

    void createOrUpdateFormationsOnisepEsr(@NonNull List<OnisepFormationDataset> datasets);

    void createOrUpdateFormationsOnisepLheo(LheoSubtype lheoSubtype);
}
