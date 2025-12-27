package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellTerritoireService;
import com.guillaumegasnier.education.shell.shells.ImportTerritoireShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
@AllArgsConstructor
public class ImportTerritoire implements ImportTerritoireShell {

    private final ShellTerritoireService shellTerritoireService;
    private final FileService fileService;

    @Override
    @ShellMethod(value = "Import toutes les références")
    public void importTerritoires() {
        importPays();
        importRegions();
        importAcademies();
        importDepartements();
        importCommunes();
    }

    @Override
    @ShellMethod(value = "Import pays")
    public void importPays() {
        shellTerritoireService.createOrUpdatePays(fileService.importCSV(PAYS));
    }

    @Override
    @ShellMethod(value = "Import académies")
    public void importAcademies() {
        shellTerritoireService.createOrUpdateAcademies(fileService.importCSV(ACADEMIES));
    }

    @Override
    @ShellMethod(value = "Import régions")
    public void importRegions() {
        shellTerritoireService.createOrUpdateRegions(fileService.importCSV(REGIONS));
    }

    @Override
    @ShellMethod(value = "Import départements")
    public void importDepartements() {
        HashMap<String, String> lienDepartementAcademie = shellTerritoireService.setAcademieDepartement(fileService.importCSV(ACADEMIES_DEPARTEMENTS));
        shellTerritoireService.createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS), lienDepartementAcademie);
        shellTerritoireService.createOrUpdateDepartements(fileService.importCSV(DEPARTEMENTS_COMER), lienDepartementAcademie);
    }

    @Override
    @ShellMethod(value = "Import communes")
    public void importCommunes() {
        shellTerritoireService.createOrUpdateCommunes(fileService.importCSV(COMMUNES));
        shellTerritoireService.createOrUpdateCommunes(fileService.importCSV(COMMUNES_COMER));
    }
}
