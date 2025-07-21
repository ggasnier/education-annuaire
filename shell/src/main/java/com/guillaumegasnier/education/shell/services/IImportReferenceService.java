package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.references.*;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;

public interface IImportReferenceService {

    String createOrUpdatePays(@NonNull List<PaysDataset> datasets);

    String createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets);

    String createOrUpdateRegions(@NonNull List<RegionDataset> datasets);

    String createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap);

    String createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets);

    HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets);
}
