package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import com.guillaumegasnier.education.shell.shells.ImportEtablissementShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@ShellComponent
@AllArgsConstructor
public class ImportEtablissement implements ImportEtablissementShell {

    private static final String ONISEP = "onisep";
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
     * <p>
     * Import des établissements depuis les différentes sources :
     * </p>
     * <ul>
     * <li>Ouverts (EN)</li>
     * <li>Fermés (EN)</li>
     * <li>ESR</li>
     * <li>ONISEP</li>
     * <li>Réseau des CARIF-OREF</li>
     * </ul>
     */
    @Override
    @ShellMethod(value = "Import de tous les établissements")
    public void importEtablissements() {
        importEtablissementsEnOuverts();
        importEtablissementsEsr();
        importEtablissementsCarif();
        importEtablissementsOnisep();
        importEtablissementsMasa();
        importEtablissementsEnFermes();
    }

    @Override
    @ShellMethod(value = "Import global des détails des établissements")
    public void importEtablissementsDetails() {
        importDispositifs();
        importLangues();
        importSports();
        importSpecialites();
        importJpo();
        importEuroscol();
    }

    @Override
    @ShellMethod(value = "Import global des metadatas")
    public void importEtablissementsMetadatas() {
        importEffectifs();
        importIpsColleges();
        importIvaColleges();
    }

    @Override
    @ShellMethod(value = "Import établissements (ouverts)")
    public void importEtablissementsEnOuverts() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_OUVERTS), "en");
    }

    @Override
    @ShellMethod(value = "Import établissements (ESR)")
    public void importEtablissementsEsr() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ESR_ETABS_OUVERTS), "esr");
    }

    @Override
    @ShellMethod(value = "Import établissements (CARIF OREF)")
    public void importEtablissementsCarif() {
        shellEtablissementService
                .createOrUpdateEtablissements(fileService.importCarifEtablissements(CARIF_ETABS_OUVERTS), "carif");
    }

    @Override
    @ShellMethod(value = "Import établissements (ONISEP SUP)")
    public void importEtablissementsOnisep() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ONISEP_ETABS_SUP), ONISEP);
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(ONISEP_ETABS_SEC), ONISEP);
    }

    @Override
    @ShellMethod(value = "Import établissements (MASA)")
    public void importEtablissementsMasa() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(MASA_ETABS), "masa");
    @ShellMethod(value = "Import sections sport études et sections sportives")
    public void importSports() {
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORTIVES), Sport.Categorie.SS);
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORT_ETUDES),
                Sport.Categorie.SE);
    }

    @Override
    @ShellMethod(value = "Import établissements (fermés)")
    public void importEtablissementsEnFermes() {
        shellEtablissementService.createOrUpdateEtablissements(fileService.importCSV(EN_ETABS_FERMES), "en");
    }

    // Partie détails

    @Override
    @ShellMethod(value = "Import des dispositifs des établissements")
    public void importDispositifs() {
        shellEtablissementService.createOrUpdateDispositifs(fileService.importCSV(DISPOSITIFS), ONISEP);
    @ShellMethod(value = "Import sections internationales")
    public void importSectionsInternationales() {
        shellEtablissementService
                .createOrUpdateSectionsInternationales(fileService.importCSV(SECTIONS_INTERNATIONALES));
    }

    @Override
    @ShellMethod(value = "Import langues dans les collèges et lycées")
    public void importLangues() {
        shellEtablissementService.createOrUpdateLangues(fileService.importCSV(LANGUES), "en");
        shellEtablissementService.createOrUpdateSectionsInternationales(fileService.importCSV(SECTIONS_INTERNATIONALES), "en");
        shellEtablissementService.createOrUpdateSectionsBinationales(fileService.importCSV(BINATIONALES), "gg");
    }

    @Override
    @ShellMethod(value = "Import sections sport études et sections sportives")
    public void importSports() {
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORTIVES), Sport.Categorie.SS, "en");
        shellEtablissementService.createOrUpdateSports(fileService.importCSV(SECTIONS_SPORT_ETUDES), Sport.Categorie.SE, "en");
    }

    @Override
    @ShellMethod(value = "Import spécialités de première générale")
    public void importSpecialites() {
        shellEtablissementService.createOrUpdateSpecialites(fileService.importCSV(SPECIALITES), ONISEP);
    }

    @Override
    @ShellMethod(value = "Import des Journées Portes Ouvertes")
    public void importJpo() {
        shellEtablissementService.createOrUpdateJpo(fileService.importCSV(MASA_JPO), "masa");
    }

    @Override
    @ShellMethod(value = "Import des établissemenst labelisés Euroscol")
    public void importEuroscol() {
        shellEtablissementService.createOrUpdateEuroscol(fileService.importCSV(EUROSCOL), "en");
    }

    // Partie metadatas

    @Override
    @ShellMethod(value = "Import des effectifs des établissements")
    public <T extends Effectifs & Metadata & Dataset> void importEffectifs() {
        List<T> effectifs = fileService.importCSV(EFFECTIFS_COLLEGE);
        effectifs.addAll(fileService.importCSV(EFFECTIFS_LYCEES_GT));
        effectifs.addAll(fileService.importCSV(EFFECTIFS_LYCEES_PRO));
        shellEtablissementService.createOrUpdateEffectifs(effectifs);
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

    @Override
    @ShellMethod(value = "Import des établissements dans Elasticsearch pour la recherche")
    public void importEtablissementsRecherche() {
        shellEtablissementService.importEtablissementsRecherche();
    }
    @Override
    @ShellMethod(value = "Import établissements pour la recherche")
    public void importEtablissementsRecherche() {
        shellEtablissementService.importEtablissementsRecherche();
    }
}
