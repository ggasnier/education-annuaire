package com.guillaumegasnier.education.core.references.services;

import com.guillaumegasnier.education.core.references.entities.*;

import java.util.List;
import java.util.Optional;

public interface ReferenceService {

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
