package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import com.guillaumegasnier.education.shell.shells.ImportFormationShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportFormationShellImpl implements ImportFormationShell {

    private final ShellFormationService shellFormationService;
    private final FileService fileService;

    @Autowired
    public ImportFormationShellImpl(ShellFormationService shellFormationService, FileService fileService) {
        this.shellFormationService = shellFormationService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import formations (CPF)")
    public String importFormationsCpf() {
        return shellFormationService.createOrUpdateFormationsCpf(fileService.importCSV(CPF_FORMATIONS));
    }

    @Override
    @ShellMethod(value = "Import organismes de formation")
    public String importOrganismesFormations() {
        return "";
    }

    @Override
    @ShellMethod(value = "Import formations Onisep IDF")
    public String importFormationsOnisepIdf() {
        return shellFormationService.createOrUpdateFormationsOnisepIdf();
    @ShellMethod("Import des formations CPF")
    public String importFormationsCPF() {
        return shellFormationService.createOrUpdateFormations(fileService.importCSV(FORMATIONS_CPF));
    }

    @Override
    @ShellMethod("Import des formations Carif-Oref")
    public String importFormationsCarif() {
        return shellFormationService.createOrUpdateFormationsCarif(fileService.importCarifFormations(CARIF_FORMATIONS));
    }

    @Override
    @ShellMethod("Import des formations Onisep")
    public String importFormationsOnisepEsr() {
        return shellFormationService.createOrUpdateFormationsOnisep(fileService.importCSV(AF_ONISEP_ESR));
    }

    @Override
    @ShellMethod("Import des certifications professionnelles")
    public String importRncp() {
        return shellFormationService.createOrUpdateCertificationsRncp(fileService.importXmlFromZip(CERTIFICATIONS_RNCP));
    }


    @Override
    @ShellMethod("Import du répertoire spécifique")
    public String importRs() {
        return "";
    }
}
