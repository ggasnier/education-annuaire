package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.ShellRechercheService;
import com.guillaumegasnier.education.shell.shells.ImportRechercheShell;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@ShellComponent
@AllArgsConstructor
public class ImportRecherche implements ImportRechercheShell {

    private final ShellRechercheService shellRechercheService;

    @Override
    @ShellMethod(value = "Import des établissements dans Elasticsearch pour la recherche")
    public void importRechercheEtablissements() {
        shellRechercheService.importEtablissementsRecherche();
    }

    @Override
    @ShellMethod(value = "Import des métiers dans Elasticsearch pour la recherche")
    public void importRechercheMetiers() {
        shellRechercheService.importMetiersRecherche();
    }
}
