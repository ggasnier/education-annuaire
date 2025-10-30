package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferenceService;
import com.guillaumegasnier.education.shell.shells.ImportReferenceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @ShellMethod(value = "Import toutes les références")
    public String importAll() {
        List<String> rapports = new ArrayList<>();
        rapports.add(importPays());
        rapports.add(importRegions());
        rapports.add(importAcademies());
        rapports.add(importDepartements());
        rapports.add(importCommunes());
        return rapports.toString();
    }

    @Override
    @ShellMethod(value = "Import pays")
    public String importPays() {
        return shellReferenceService.createOrUpdatePays(fileService.importCSV(PAYS));
    }

    @Override
    @ShellMethod(value = "Import académies")
    public String importAcademies() {
        return shellReferenceService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES));
    }

    @Override
    @ShellMethod(value = "Import régions")
    public String importRegions() {
        return shellReferenceService.createOrUpdateRegions(fileService.importCSV(REGIONS));
    }

    @Override
    @ShellMethod(value = "Import départements")
    public String importDepartements() {
        HashMap<String, String> lienDepartementAcademie = shellReferenceService.setAcademieDepartement(fileService.importCSV(ACADEMIES_DEPARTEMENTS));

        return shellReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS), lienDepartementAcademie)
                + shellReferenceService
                .createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS_COMER), lienDepartementAcademie);
    }

    @Override
    @ShellMethod(value = "Import communes")
    public String importCommunes() {
        return shellReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES))
                + "\n" + shellReferenceService
                .createOrUpdateCommunes(fileService.importCSV(COMMUNES_COMER));
    }
}
