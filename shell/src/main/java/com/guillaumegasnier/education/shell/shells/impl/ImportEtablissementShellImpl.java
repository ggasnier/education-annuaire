package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.shells.ImportEtablissementShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

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
    public void importEtablissementsEnOuverts() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_OUVERTS), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (fermés)")
    public void importEtablissementsEnFermes() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_FERMES), "en");
    }

    @Override
    @ShellMethod(value = "Import de tous les établissements")
    public void importEtablissementsAll() {
        importNatures();
        importContrats();
        importEtablissementsEnOuverts();
        importEtablissementsEsr();
        importEtablissementsCarif();
        importEtablissementsOnisepSup();
        importEtablissementsEnFermes();
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public void importEtablissementsEsr() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public void importEtablissementsCarif() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCarifEtablissements(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import établissements (ONISEP SUP)")
    public void importEtablissementsOnisepSup() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ONISEP_ETABS_SUP), "onisep");
    }

    @Override
    @ShellMethod(value = "Import Organismes de formations")
    public void importOrganismes() {
        shellEtablissementService.createOrUpdateOrganismes(fileService.importCSV(TRAVAIL_ETABS));
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public void importNatures() {
        shellEtablissementService.createOrUpdateNatures(fileService.importCSV(NATURES));
    }

    @Override
    @ShellMethod(value = "Import contrats d'établissements")
    public void importContrats() {
        shellEtablissementService.createOrUpdateContrats(fileService.importCSV(CONTRATS));
    }

    @Override
    @ShellMethod(value = "Import sections sport études et sections sportives")
    public void importSports() {
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORTIVES), "ss");
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORT_ETUDES), "se");
    }

    @Override
    @ShellMethod(value = "Import sections internationales")
    public void importSectionsInternationales() {
        shellEtablissementService.createOrUpdateSectionsInternationales(fileService.importCSV(SECTIONS_INTERNATIONALES));
    }

    @Override
    @ShellMethod(value = "Import langues dans les collèges et lycées")
    public void importLangues() {
        shellEtablissementService.createOrUpdateLangues(fileService.importCSV(LANGUES));
    }

    @Override
    @ShellMethod(value = "Import spécialités de première générale")
    public void importSpecialites() {
        shellEtablissementService.createOrUpdateSpecialites(fileService.importCSV(SPECIALITES));
    }

    @Override
    @ShellMethod(value = "Import sections binationales")
    public void importSectionsBinationales() {
        shellEtablissementService.createOrUpdateSectionsBinationales(fileService.importCSV(BINATIONALES));
    }

    @Override
    @ShellMethod(value = "Import des dispositifs des établissements")
    public void importDispositifs() {
        shellEtablissementService.createOrUpdateDispositifs(fileService.importCSV(DISPOSITIFS));
    }

    @Override
    @ShellMethod(value = "Import des établissemenst labelisés Euroscol")
    public void importEuroscol() {
        shellEtablissementService.createOrUpdateEuroscol(fileService.importCSV(EUROSCOL));
    }

    @Override
    @ShellMethod(value = "Import des effectifs des établissements")
    public void importEffectifs() {
        shellEtablissementService.createOrUpdateEffectifs(fileService.importCSV(EFFECTIFS_COLLEGE));
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
    @Override
    @ShellMethod(value = "Import IPS Collèges")
    public void importIpsColleges() {
        shellEtablissementService.createOrUpdateIPS(fileService.importCSV(IPS_COLLEGES_1));
        shellEtablissementService.createOrUpdateIPS(fileService.importCSV(IPS_COLLEGES_2));
        shellEtablissementService.createOrUpdateIPS(fileService.importCSV(IPS_COLLEGES_3));
    }

    @Override
    @ShellMethod(value = "Import IVA Collèges")
    public void importIvaColleges() {
        shellEtablissementService.createOrUpdateIVA(fileService.importCSV(IVA_COLLEGES));
    }

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
    @ShellMethod(value = "Import tous les détails des établissements")
    public void importDetailsAll() {
        importDispositifs();
        importLangues();
        importSectionsBinationales();
        importSectionsInternationales();
        importSports();
        importSpecialites();
    }
}
