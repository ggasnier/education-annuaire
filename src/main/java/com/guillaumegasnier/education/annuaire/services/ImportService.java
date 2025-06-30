package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.datasets.DepartementDataset;
import com.guillaumegasnier.education.annuaire.datasets.EnEtablissementDataset;
import com.guillaumegasnier.education.annuaire.datasets.RegionDataset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.guillaumegasnier.education.annuaire.enums.SourcesDatasets.*;

@Slf4j
@Service
public class ImportService {

    private final EtablissementService etablissementService;
    private final ReferenceService referenceService;
    private final FileService fileService;

    @Value("${spring.profiles.active}")
    private String env;

    public ImportService(EtablissementService etablissementService, ReferenceService referenceService, FileService fileService) {
        this.etablissementService = etablissementService;
        this.referenceService = referenceService;
        this.fileService = fileService;
    }

    public String importEnEtablissements() {
        String location = env.equals("local") ? EN_ETABS_OUVERTS.getPath() : EN_ETABS_OUVERTS.getUrl();
        return etablissementService.createOrUpdateEtablissement(fileService.importCSV(location, EnEtablissementDataset.class, ';', StandardCharsets.UTF_8));
    }

    public String importRegions() {
        String location = env.equals("local") ? REGIONS.getPath() : REGIONS.getUrl();
        return referenceService.createOrUpdateRegion(fileService.importCSV(location, RegionDataset.class, ',', StandardCharsets.UTF_8));
    }

    public String importDepartements() {
        String location = env.equals("local") ? DEPARTEMENTS.getPath() : DEPARTEMENTS.getUrl();
        return referenceService.createOrUpdateDepartement(fileService.importCSV(location, DepartementDataset.class, ',', StandardCharsets.UTF_8));
    }
}
