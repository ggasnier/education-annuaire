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
    @ShellMethod(value = "Import organismes de formation")
    public String importOrganismesFormations() {
        return "TODO";
    }

    @Override
    @ShellMethod(value = "Import formations (CPF)")
    public String importFormationsCpf() {
        return shellFormationService.createOrUpdateFormationsCpf(fileService.importCSV(FORMATIONS_CPF));
    }

    @Override
    @ShellMethod("Import des formations Parcoursup")
    public String importFormationsParcoursup() {
        return shellFormationService.createOrUpdateFormationsParcoursup(fileService.importCSV(FORMATIONS_PARCOURSUP));
    }

    @Override
    @ShellMethod("Import des formations Onisep")
    public String importFormationsOnisepEsr() {
        return shellFormationService.createOrUpdateFormationsOnisepEsr(fileService.importCSV(FORMATIONS_ONISEP_ESR));
    }

    @Override
    @ShellMethod(value = "Import formations Onisep au format LHEO")
    public String importFormationsOnisepLheo() {
        return shellFormationService.createOrUpdateFormationsOnisepLheo(null);
    }

    @Override
    @ShellMethod("Import des formations Carif-Oref")
    public String importFormationsCarif() {
        return shellFormationService.createOrUpdateFormationsCarif(fileService.importCarifFormations(FORMATIONS_CARIF));
    }

    @Override
    @ShellMethod("Import des certifications professionnelles")
    public String importRncp() {
        return shellFormationService.createOrUpdateCertificationsRncp(fileService.importXmlFromZip(CERTIFICATIONS_RNCP));
    }

    // TODO
    @Override
    @ShellMethod("Import du répertoire spécifique")
    public String importRs() {
        return "";
    }
}
