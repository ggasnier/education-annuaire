package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2021Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2022Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2023Dataset;
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
    @ShellMethod(value = "Import établissements (EN)")
    public String importEnEtablissements() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_OUVERTS, EnEtablissementDataset.class), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (fermés)")
    public String importEtablissementsFermes() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_FERMES, EtablissementFermeDataset.class), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public String importEsrEtablissements() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS, EsrEtablissementDataset.class), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public String importCarifEtablissements() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importJsonCarif(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public String importNatures() {
        return shellEtablissementService.createOrUpdateNatures(fileService.importCSV(NATURES, NatureDataset.class));
    }

    @Override
    @ShellMethod(value = "Import contrats d'établissements")
    public String importContrats() {
        return shellEtablissementService.createOrUpdateContrats(fileService.importCSV(CONTRATS, ContratDataset.class));
    }

    @ShellMethod(value = "Import IPS Collèges")
    public String importIpsColleges() {
        List<IPSDataset> ipsColleges = new ArrayList<>();
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_1, IPSCollege2023Dataset.class));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_2, IPSCollege2022Dataset.class));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_3, IPSCollege2021Dataset.class));
        return shellEtablissementService.createOrUpdateIPSColleges(ipsColleges);
    }
}
