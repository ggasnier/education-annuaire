package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.references.*;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ReferenceService;
import com.guillaumegasnier.education.shell.shells.ImportReferenceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportReferenceShellImpl implements ImportReferenceShell {

    private final ReferenceService referenceService;
    private final FileService fileService;

    @Autowired
    public ImportReferenceShellImpl(ReferenceService referenceService, FileService fileService) {
        this.referenceService = referenceService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import pays")
    public String importPays() {
        return referenceService.createOrUpdatePays(fileService.importCSV(PAYS, PaysDataset.class));
    }

    @Override
    @ShellMethod(value = "Import académies")
    public String importAcademies() {
        return referenceService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES, AcademieDataset.class));
    }

    @Override
    @ShellMethod(value = "Import régions")
    public String importRegions() {
        return referenceService.createOrUpdateRegions(fileService.importCSV(REGIONS, RegionDataset.class));
    }

    @Override
    @ShellMethod(value = "Import départements")
    public String importDepartements() {
        HashMap<String, String> lienDepartementAcademie = referenceService.setAcademieDepartement(fileService.importCSV(ACADEMIES_DEPARTEMENTS, AcademieDepartementDataset.class));

        return referenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS, DepartementDataset.class), lienDepartementAcademie)
                + referenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS_COMER, DepartementOutreMerDataset.class), lienDepartementAcademie);
    }

    @Override
    @ShellMethod(value = "Import communes")
    public String importCommunes() {
        return referenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES, CommuneDataset.class))
                + "\n" + referenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES_COMER, CommuneOutreMerDataset.class));
    }
}
