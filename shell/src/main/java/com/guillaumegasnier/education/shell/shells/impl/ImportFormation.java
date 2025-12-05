package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import com.guillaumegasnier.education.shell.shells.ImportFormationShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
@AllArgsConstructor
public class ImportFormation implements ImportFormationShell {

    private final ShellFormationService shellFormationService;
    private final FileService fileService;

    @Override
    @ShellMethod(value = "Import organismes de formation")
    public void importOrganismesFormations() {
        shellFormationService.createOrUpdateOrganismes(fileService.importCSV(TRAVAIL_ETABS));
    }

    @Override
    @ShellMethod(value = "Import formations (CPF)")
    public void importFormationsCpf() {
        shellFormationService.createOrUpdateFormationsCpf(fileService.importCSV(FORMATIONS_CPF));
    }

    @Override
    @ShellMethod("Import des formations Parcoursup")
    public void importFormationsParcoursup() {
        shellFormationService.createOrUpdateFormationsParcoursup(fileService.importCSV(FORMATIONS_PARCOURSUP));
    }

    @Override
    @ShellMethod("Import des formations Onisep")
    public void importFormationsOnisepEsr() {
        shellFormationService.createOrUpdateFormationsOnisepEsr(fileService.importCSV(FORMATIONS_ONISEP_ESR));
    }

    @Override
    @ShellMethod(value = "Import formations Onisep au format LHEO")
    public void importFormationsOnisepLheo() {
        shellFormationService.createOrUpdateFormationsOnisepLheo(null);
    }

    @Override
    @ShellMethod("Import des formations Carif-Oref")
    public void importFormationsCarif() {
        shellFormationService.createOrUpdateFormationsCarif(fileService.importCarifFormations(FORMATIONS_CARIF));
    }

}
