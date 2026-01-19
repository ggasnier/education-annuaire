package com.guillaumegasnier.education.shell.services.impl;


import com.guillaumegasnier.education.core.domains.territoires.DepartementEntity;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.mappers.ReferenceMapper;
import com.guillaumegasnier.education.shell.services.ShellTerritoireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShellReferenceServiceImpl implements ShellTerritoireService {

    private final CoreTerritoireService coreReferenceService;
    private final ReferenceMapper referenceMapper;

    @Override
    public void createOrUpdatePays(@NonNull List<PaysDataset> datasets) {
        coreReferenceService.savePays(datasets.stream().map(referenceMapper::toPaysEntity).toList());
        String.format("Import terminé : %d pays enregistrée(s).", datasets.size());
    }

    @Override
    public void createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets) {
        coreReferenceService.saveAcademies(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(referenceMapper::toAcademieEntity)
                .toList());
        String.format("Import terminé : %d académie(s) enregistrée(s).", datasets.size());
    }

    @Override
    public void createOrUpdateRegions(@NonNull List<RegionDataset> datasets) {
        coreReferenceService.saveRegions(datasets.stream()
                .map(referenceMapper::toRegionEntity)
                .toList());
        String.format("Import terminé : %d région(s) enregistrée(s).", datasets.size());
    }

    @Override
    public void createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap) {
        List<DepartementEntity> departements = datasets.stream()
                .map(dataset -> {
                    DepartementEntity entity = referenceMapper.toDepartementEntity(dataset);

                    // Association à une région si le code est renseigné
                    String codeRegion = dataset.getCodeRegion();
                    if (codeRegion != null && !codeRegion.isEmpty()) {
                        entity.setRegion(coreReferenceService.getRegion(codeRegion));
                    }

                    // Association à une académie si le mapping existe
                    String codeAcademie = codeAcademieMap.get(dataset.getCode());
                    if (codeAcademie != null && !codeAcademie.isEmpty()) {
                        entity.setAcademie(coreReferenceService.getAcademie(codeAcademie));
                    }

                    return entity;
                })
                .toList();

        coreReferenceService.saveDepartements(departements);

        String.format("Import terminé : %d département(s) enregistré(s).", departements.size());
    }

    @Override
    public void createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets) {
        var pays = coreReferenceService.getPays("FR");

        coreReferenceService.saveCommunes(datasets.stream().map(dataset -> {
            var entity = referenceMapper.toCommuneEntity(dataset);
            if (!dataset.getCodeDepartement().isEmpty()) {
                entity.setDepartement(coreReferenceService.getDepartement(dataset.getCodeDepartement()));
            } else {
                entity.setDepartement(coreReferenceService.getDepartement(dataset.getCode().substring(0, 2)));
            }

            entity.setPays(pays);

            return entity;
        }).toList());

        String.format("Import terminé : %d communes(s) enregistré(s).", datasets.size());
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
