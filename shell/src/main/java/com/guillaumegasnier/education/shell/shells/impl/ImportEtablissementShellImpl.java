package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.shells.ImportEtablissementShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportEtablissementShellImpl implements ImportEtablissementShell {

    private final ShellEtablissementService shellEtablissementService;
    private final FileService fileService;

    @Autowired
    public ImportEtablissementShellImpl(ShellEtablissementService shellEtablissementService, FileService fileService) {
        this.shellEtablissementService = shellEtablissementService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import établissements (ouverts)")
    public String importEtablissementsEnOuverts() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_OUVERTS), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (fermés)")
    public String importEtablissementsEnFermes() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_FERMES), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public String importEtablissementsEsr() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public String importEtablissementsCarif() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importJsonCarif(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public String importNatures() {
        return shellEtablissementService.createOrUpdateNatures(fileService.importCSV(NATURES));
    }

    @Override
    @ShellMethod(value = "Import contrats d'établissements")
    public String importContrats() {
        return shellEtablissementService.createOrUpdateContrats(fileService.importCSV(CONTRATS));
    }

    @Override
    @ShellMethod(value = "Import sections sportives")
    public String importSectionsSportives() {
        return shellEtablissementService.createOrUpdateSectionsSportives(fileService.importCSV(SECTIONS_SPORTIVES));
    }

    @Override
    @ShellMethod(value = "Import langues dans les collèges et lycées")
    public String importLangues() {
        return shellEtablissementService.createOrUpdateLangues(fileService.importCSV(LANGUES));
    }

    @ShellMethod(value = "Import IPS Collèges")
    public String importIpsColleges() {
        List<IPSDataset> ipsColleges = new ArrayList<>();
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_1));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_2));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_3));
        return shellEtablissementService.createOrUpdateIPSColleges(ipsColleges);
    }
}
