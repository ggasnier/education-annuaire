package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.references.entities.DepartementEntity;
import com.guillaumegasnier.education.core.references.services.ReferenceService;
import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.mappers.ImportMapper;
import com.guillaumegasnier.education.shell.services.IImportReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ImportReferenceService implements IImportReferenceService {

    private final ReferenceService referenceService;
    private final ImportMapper importMapper;

    @Autowired
    public ImportReferenceService(ReferenceService referenceService, ImportMapper importMapper) {
        this.referenceService = referenceService;
        this.importMapper = importMapper;
    }

    /**
     * TODO
     *
     * @param datasets
     * @return
     */
    @Override
    public String createOrUpdatePays(@NonNull List<PaysDataset> datasets) {
        return "";
    }

    @Override
    public String createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets) {
        referenceService.saveAcademies(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(importMapper::toAcademieEntity)
                .toList());
        return String.format("Import terminé : %d académie(s) enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateRegions(@NonNull List<RegionDataset> datasets) {
        referenceService.saveRegions(datasets.stream()
                .map(importMapper::toRegionEntity)
                .toList());
        return String.format("Import terminé : %d région(s) enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap) {
        List<DepartementEntity> departements = datasets.stream()
                .map(dataset -> {
                    DepartementEntity entity = importMapper.toDepartementEntity(dataset);

                    // Association à une région si le code est renseigné
                    String codeRegion = dataset.getCodeRegion();
                    if (codeRegion != null && !codeRegion.isEmpty()) {
                        entity.setRegion(referenceService.getRegion(codeRegion));
                    }

                    // Association à une académie si le mapping existe
                    String codeAcademie = codeAcademieMap.get(dataset.getCode());
                    if (codeAcademie != null && !codeAcademie.isEmpty()) {
                        entity.setAcademie(referenceService.getAcademie(codeAcademie));
                    }

                    return entity;
                })
                .toList();

        referenceService.saveDepartements(departements);

        return String.format("Import terminé : %d département(s) enregistré(s).", departements.size());
    }

    @Override
    public String createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets) {
        referenceService.saveCommunes(datasets.stream().map(dataset -> {
            var entity = importMapper.toCommuneEntity(dataset);
            if (!dataset.getCodeDepartement().isEmpty()) {
                entity.setDepartement(referenceService.getDepartement(dataset.getCodeDepartement()));
            } else {
                entity.setDepartement(referenceService.getDepartement(dataset.getCode().substring(0, 2)));
            }

            return entity;
        }).toList());

        return String.format("Import terminé : %d communes(s) enregistré(s).", datasets.size());
    }

    @Override
    public HashMap<String, String> setAcademieDepartement(@NonNull List<AcademieDepartementDataset> datasets) {
        HashMap<String, String> departments = new HashMap<>();
        datasets.forEach(
                dataset -> {
                    if (dataset.getDateFin() != null && dataset.getDateFin().isEmpty()) {
                        departments.computeIfAbsent(dataset.getCodeDepartement(), k -> dataset.getCodeAcademie());
                    }
                }
        );
        return departments;
    }
}
