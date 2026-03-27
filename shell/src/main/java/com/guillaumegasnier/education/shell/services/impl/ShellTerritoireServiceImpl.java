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
public class ShellTerritoireServiceImpl implements ShellTerritoireService {

    private final CoreTerritoireService coreTerritoireService;
    private final ReferenceMapper referenceMapper;

    @Override
    public void createOrUpdatePays(@NonNull List<PaysDataset> datasets) {
        coreTerritoireService.savePays(datasets.stream().map(referenceMapper::toPaysEntity).toList());
        log.info("Import terminé : {} pays enregistrés.", datasets.size());
    }

    @Override
    public void createOrUpdateAcademies(@NonNull List<AcademieDataset> datasets) {
        coreTerritoireService.saveAcademies(datasets.stream()
                .filter(dataset -> dataset.getDateFin() != null && dataset.getDateFin().isEmpty())
                .map(referenceMapper::toAcademieEntity)
                .toList());
        log.info("Import terminé : {} académies enregistrées.", datasets.size());
    }

    @Override
    public void createOrUpdateRegions(@NonNull List<RegionDataset> datasets) {
        coreTerritoireService.saveRegions(datasets.stream()
                .map(referenceMapper::toRegionEntity)
                .toList());
        log.info("Import terminé : {} régions enregistrées.", datasets.size());
    }

    @Override
    public void createOrUpdateDepartements(@NonNull List<DepartementDataset> datasets, @NonNull HashMap<String, String> codeAcademieMap) {
        List<DepartementEntity> departements = datasets.stream()
                .map(dataset -> {
                    DepartementEntity entity = referenceMapper.toDepartementEntity(dataset);

                    // Association à une région si le code est renseigné
                    String codeRegion = dataset.getCodeRegion();
                    if (codeRegion != null && !codeRegion.isEmpty()) {
                        entity.setRegion(coreTerritoireService.getRegion(codeRegion));
                    }

                    // Association à une académie si le mapping existe
                    String codeAcademie = codeAcademieMap.get(dataset.getCode());
                    if (codeAcademie != null && !codeAcademie.isEmpty()) {
                        entity.setAcademie(coreTerritoireService.getAcademie(codeAcademie));
                    }

                    return entity;
                })
                .toList();

        coreTerritoireService.saveDepartements(departements);

        log.info("Import terminé : {} départements enregistrés.", departements.size());
    }

    @Override
    public void createOrUpdateCommunes(@NonNull List<CommuneDataset> datasets) {
        var pays = coreTerritoireService.getPays("FR");

        coreTerritoireService.saveCommunes(datasets.stream().map(dataset -> {
            var entity = referenceMapper.toCommuneEntity(dataset);
            if (!dataset.getCodeDepartement().isEmpty()) {
                entity.setDepartement(coreTerritoireService.getDepartement(dataset.getCodeDepartement()));
            } else {
                entity.setDepartement(coreTerritoireService.getDepartement(dataset.getCode().substring(0, 2)));
            }

            entity.setPays(pays);

            return entity;
        }).toList());

        log.info("Import terminé : {} communess enregistrés.", datasets.size());
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
