package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.impl.ImportReferenceService;
import com.guillaumegasnier.education.shell.shells.IReferenceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportReferenceShell implements IReferenceShell {

    private final ImportReferenceService importReferenceService;
    private final FileService fileService;

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    public ImportReferenceShell(ImportReferenceService importReferenceService, FileService fileService) {
        this.importReferenceService = importReferenceService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import pays")
    public String importPays() {
        return importReferenceService.createOrUpdatePays(fileService.importCSV(PAYS, PaysDataset.class));
    }

    @Override
    @ShellMethod(value = "Import académies")
    public String importAcademies() {
//        String location = env.equals("local") ? ACADEMIES.getPath() : ACADEMIES.getUrl();
//        return importReferenceService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES, AcademieDataset.class, ',', Charset.forName("windows-1252")));
        return importReferenceService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES, AcademieDataset.class));
    }

    @Override
    @ShellMethod(value = "Import régions")
    public String importRegions() {
//        String location = env.equals("local") ? REGIONS.getPath() : REGIONS.getUrl();
        return importReferenceService.createOrUpdateRegions(fileService.importCSV(REGIONS, RegionDataset.class));
    }

    @Override
    @ShellMethod(value = "Import départements")
    public String importDepartements() {
//        String location = env.equals("local") ? DEPARTEMENTS.getPath() : DEPARTEMENTS.getUrl();
//        String location2 = env.equals("local") ? DEPARTEMENTS_COMER.getPath() : DEPARTEMENTS_COMER.getUrl();
//        String location3 = env.equals("local") ? ACADEMIES_DEPARTEMENTS.getPath() : ACADEMIES_DEPARTEMENTS.getUrl();
        HashMap<String, String> lienDepartementAcademie = importReferenceService.setAcademieDepartement(fileService.importCSV(ACADEMIES_DEPARTEMENTS, AcademieDepartementDataset.class));

        return importReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS, DepartementDataset.class), lienDepartementAcademie)
                + importReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS_COMER, DepartementOutreMerDataset.class), lienDepartementAcademie);
    }

    @Override
    @ShellMethod(value = "Import communes")
    public String importCommunes() {
//        String location = env.equals("local") ? COMMUNES.getPath() : COMMUNES.getUrl();
//        String location2 = env.equals("local") ? COMMUNES_COMER.getPath() : COMMUNES_COMER.getUrl();

        return importReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES, CommuneDataset.class))
                + "\n" + importReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES_COMER, CommuneOutreMerDataset.class));
    }
}
