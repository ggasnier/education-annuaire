package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import com.guillaumegasnier.education.shell.shells.ImportReferentielShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.CERTIFICATIONS_RNCP;

@ShellComponent
@AllArgsConstructor
public class ImportReferentiel implements ImportReferentielShell {

    private final ShellReferencielService shellReferencielService;
    private final FileService fileService;

    @Override
    @ShellMethod("Import des certifications professionnelles")
    public void importRncp() {
        shellReferencielService.createOrUpdateCertificationsRncp(fileService.importXmlFromZip(CERTIFICATIONS_RNCP));
    }
    
}
