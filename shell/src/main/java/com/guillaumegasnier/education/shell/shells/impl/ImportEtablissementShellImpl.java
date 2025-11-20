package com.guillaumegasnier.education.shell.shells.impl;

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
    @ShellMethod(value = "Import de tous les établissements")
    public String importEtablissementsAll() {
        List<String> rapports = new ArrayList<>();
        rapports.add(importNatures());
        rapports.add(importContrats());
        rapports.add(importEtablissementsEnOuverts());
        rapports.add(importEtablissementsEsr());
        rapports.add(importEtablissementsCarif());
        rapports.add(importEtablissementsOnisepSup());
        rapports.add(importEtablissementsEnFermes());
        return rapports.toString();
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public String importEtablissementsEsr() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public String importEtablissementsCarif() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCarifEtablissements(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import établissements (ONISEP SUP)")
    public String importEtablissementsOnisepSup() {
        return shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ONISEP_ETABS_SUP), "onisep");
    }

    @Override
    @ShellMethod(value = "Import Organismes de formations")
    public String importOrganismes() {
        return shellEtablissementService.createOrUpdateOrganismes(fileService.importCSV(TRAVAIL_ETABS));
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
    @ShellMethod(value = "Import sections sport études")
    public String importSectionsSportEtudes() {
        return shellEtablissementService.createOrUpdateSectionsSportEtudes(fileService.importCSV(SECTIONS_SPORT_ETUDES));
    }

    @Override
    @ShellMethod(value = "Import sections internationales")
    public String importSectionsInternationales() {
        return shellEtablissementService.createOrUpdateSectionsInternationales(fileService.importCSV(SECTIONS_INTERNATIONALES));
    }

    @Override
    @ShellMethod(value = "Import langues dans les collèges et lycées")
    public String importLangues() {
        return shellEtablissementService.createOrUpdateLangues(fileService.importCSV(LANGUES));
    }

    @Override
    @ShellMethod(value = "Import spécialités de première générale")
    public String importSpecialites() {
        return shellEtablissementService.createOrUpdateSpecialites(fileService.importCSV(SPECIALITES));
    }

    @Override
    @ShellMethod(value = "Import sections binationales")
    public String importSectionsBinationales() {
        return shellEtablissementService.createOrUpdateSectionsBinationales(fileService.importCSV(BINATIONALES));
    }

//    @Override
//    @ShellMethod(value = "Import IPS Ecoles")
//    public String importIpsEcoles() {
//        List<IPSDataset> ipsEcoles = new ArrayList<>();
//        ipsEcoles.addAll(fileService.importCSV(IPS_ECOLES_1));
//        ipsEcoles.addAll(fileService.importCSV(IPS_ECOLES_2));
//        return shellEtablissementService.createOrUpdateIPS(ipsEcoles, "E");
//    }
//
//    @Override
//    @ShellMethod(value = "Import IPS Collèges")
//    public String importIpsColleges() {
//        List<IPSDataset> ipsColleges = new ArrayList<>();
//        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_1));
//        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_2));
//        ipsColleges.addAll(fileService.importCSV(IPS_COLLEGES_3));
//        return shellEtablissementService.createOrUpdateIPS(ipsColleges, "C");
//    }

//    @Override
//    @ShellMethod(value = "Import IPS Lycées")
//    public String importIpsLycees() {
//        List<IPSDataset> ipsLycees = new ArrayList<>();
//        ipsLycees.addAll(fileService.importCSV(IPS_LYCEES_1));
//        ipsLycees.addAll(fileService.importCSV(IPS_LYCEES_2));
//        ipsLycees.addAll(fileService.importCSV(IPS_LYCEES_3));
//        return shellEtablissementService.createOrUpdateIPS(ipsLycees, "L");
//    }

    @Override
    @ShellMethod(value = "Import établissements dans ES")
    public String importEtablissementsRecherche() {
        return shellEtablissementService.createOrUpdateEtablissementsRecherche();
    }
}
