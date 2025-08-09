package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferenceService;
import com.guillaumegasnier.education.shell.shells.ImportReferenceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportReferenceShellImpl implements ImportReferenceShell {

    private final ShellReferenceService shellReferenceService;
    private final FileService fileService;

    @Autowired
    public ImportReferenceShellImpl(ShellReferenceService shellReferenceService, FileService fileService) {
        this.shellReferenceService = shellReferenceService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import pays")
    public String importPays() {
        return shellReferenceService.createOrUpdatePays(fileService.importCSV(PAYS, PaysDataset.class));
    }

    @Override
    @ShellMethod(value = "Import académies")
    public String importAcademies() {
        return shellReferenceService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES, AcademieDataset.class));
    }

    @Override
    @ShellMethod(value = "Import régions")
    public String importRegions() {
        return shellReferenceService.createOrUpdateRegions(fileService.importCSV(REGIONS, RegionDataset.class));
    }

    @Override
    @ShellMethod(value = "Import départements")
    public String importDepartements() {
        HashMap<String, String> lienDepartementAcademie = shellReferenceService.setAcademieDepartement(fileService.importCSV(ACADEMIES_DEPARTEMENTS, AcademieDepartementDataset.class));

        return shellReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS, DepartementDataset.class), lienDepartementAcademie)
                + shellReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS_COMER, DepartementOutreMerDataset.class), lienDepartementAcademie);
    }

    @Override
    @ShellMethod(value = "Import communes")
    public String importCommunes() {
        return shellReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES, CommuneDataset.class))
                + "\n" + shellReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES_COMER, CommuneOutreMerDataset.class));
    }
}
