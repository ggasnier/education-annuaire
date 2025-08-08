package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EnEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EsrEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2021Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2022Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2023Dataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import com.guillaumegasnier.education.shell.services.EtablissementService;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.shells.ImportEtablissementShell;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
public class ImportEtablissementShellImpl implements ImportEtablissementShell {

    private final EtablissementService etablissementService;
    private final FileService fileService;

    public ImportEtablissementShellImpl(EtablissementService etablissementService, FileService fileService) {
        this.etablissementService = etablissementService;
        this.fileService = fileService;
    }

    @Override
    @ShellMethod(value = "Import établissements (EN)")
    public String importEnEtablissements() {
        return etablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_OUVERTS, EnEtablissementDataset.class), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public String importEsrEtablissements() {
        return etablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS, EsrEtablissementDataset.class), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public String importCarifEtablissements() {
        return etablissementService.createOrUpdateEtablissements(fileService.importJsonCarif(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public String importNatures() {
        return etablissementService.createOrUpdateNatures(fileService.importCSV(NATURES, NatureDataset.class));
    }

    @Override
    @ShellMethod(value = "Import contrats d'établissements")
    public String importContrats() {
        return etablissementService.createOrUpdateContrats(fileService.importCSV(CONTRATS, ContratDataset.class));
    }

    @ShellMethod(value = "Import IPS Collèges")
    public String importIpsColleges() {
        List<IPSDataset> ipsColleges = new ArrayList<>();
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_1, IPSCollege2023Dataset.class));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_2, IPSCollege2022Dataset.class));
        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_3, IPSCollege2021Dataset.class));
        return etablissementService.createOrUpdateIPSColleges(ipsColleges);
    }
}
