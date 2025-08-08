package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.*;
import com.guillaumegasnier.education.shell.datasets.references.*;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ReferenceService {

    String createOrUpdatePays(@NonNull List<PaysDataset> datasets);

    String createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets);

    String createOrUpdateRegions(@NonNull List<RegionDataset> datasets);

    String createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap);

    String createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets);

    HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets);

    void savePays(List<PaysEntity> entities);

    void saveAcademies(List<AcademieEntity> entities);

    void saveRegions(List<RegionEntity> entities);

    void saveDepartements(List<DepartementEntity> entities);

    void saveCommunes(List<CommuneEntity> entities);

    RegionEntity getRegion(String codeRegion);

    AcademieEntity getAcademie(String codeAcademie);

    DepartementEntity getDepartement(String codeDepartement);

    Optional<CommuneEntity> findCommune(String codeCommune);
}
