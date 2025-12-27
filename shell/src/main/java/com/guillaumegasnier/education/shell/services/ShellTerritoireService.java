package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.references.*;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;

public interface ShellTerritoireService {

    void createOrUpdatePays(@NonNull List<PaysDataset> datasets);

    void createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets);

    void createOrUpdateRegions(@NonNull List<RegionDataset> datasets);

    void createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap);

    void createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets);

    HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets);
}
