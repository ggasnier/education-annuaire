package com.guillaumegasnier.education.annuaire.shells;

import com.guillaumegasnier.education.annuaire.services.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@ShellComponent
public class ImportShell {

    private final ImportService importService;

    @Autowired
    public ImportShell(ImportService importService) {
        this.importService = importService;
    }

    @ShellMethod(value = "Import établissements (EN)")
    public String importEnEtablissements() {
        return "Import des établissements de l'EN";
    }

    @ShellMethod(value = "Import établissements (ESR)")
    public String importEsrEtablissements() {
        return "Import des établissements de l'ESR";
    }

    @ShellMethod(value = "Import établissements (Carif-Oref)")
    public String importCarifEtablissements() {
        return "Import des établissements du réseau Carif-Oref";
    }
}
