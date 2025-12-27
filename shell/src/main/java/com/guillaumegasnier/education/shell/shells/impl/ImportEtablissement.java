package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.shells.ImportEtablissementShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
@AllArgsConstructor
public class ImportEtablissement implements ImportEtablissementShell {

    private final ShellEtablissementService shellEtablissementService;
    private final FileService fileService;

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

    /**
     * <p>Import des établissements depuis les différentes sources :</p>
     * <ul>
     *     <li>Ouverts (EN)</li>
     *     <li>Fermés (EN)</li>
     *     <li>ESR</li>
     *     <li>ONISEP</li>
     *     <li>Réseau des CARIF-OREF</li>
     * </ul>
     */
    @Override
    @ShellMethod(value = "Import de tous les établissements")
    public void importEtablissements() {
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
    @ShellMethod(value = "Import sections sport études et sections sportives")
    public void importSports() {
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORTIVES), Sport.Categorie.SS);
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORT_ETUDES), Sport.Categorie.SE);
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

    @Override
    @ShellMethod(value = "Import tous les détails des établissements")
    public void importEtablissementsDetails() {
        importDispositifs();
        importLangues();
        importSectionsBinationales();
        importSectionsInternationales();
        importSports();
        importSpecialites();
    }
}
