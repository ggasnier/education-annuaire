package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.references.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CoreReferenceService {

    Optional<CommuneEntity> findCommune(@NonNull String code);

    void savePays(List<PaysEntity> entities);

    void saveAcademies(List<AcademieEntity> entities);

    void saveRegions(List<RegionEntity> entities);

    void saveDepartements(List<DepartementEntity> entities);

    void saveCommunes(List<CommuneEntity> entities);

    RegionEntity getRegion(String codeRegion);

    AcademieEntity getAcademie(String codeAcademie);

    DepartementEntity getDepartement(String codeDepartement);
}
