package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import com.guillaumegasnier.education.shell.shells.ImportReferentielShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
@AllArgsConstructor
public class ImportReferentiel implements ImportReferentielShell {

    private final ShellReferencielService shellReferencielService;
    private final FileService fileService;

    @Override
    @ShellMethod(value = "Import contrats")
    public void importContrats() {
        shellReferencielService.createOrUpdateContrats(fileService.importCSV(CONTRATS));
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public void importNatures() {
        shellReferencielService.createOrUpdateNatures(fileService.importCSV(NATURES));
    }

    @Override
    @ShellMethod("Import des certifications professionnelles (RNCP)")
    public void importRncp() {
        shellReferencielService.createOrUpdateCertificationsRncp(fileService.importXmlFromZip(CERTIFICATIONS_RNCP));
    }

}
