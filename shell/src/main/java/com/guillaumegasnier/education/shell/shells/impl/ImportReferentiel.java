package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.datasets.referentiels.ArborescenceCompetenceDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeAppellationDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeBlocDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import com.guillaumegasnier.education.shell.shells.ImportReferentielShell;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

import static com.guillaumegasnier.education.shell.enums.SourcesDatasets.*;

@Slf4j
@ShellComponent
@AllArgsConstructor
public class ImportReferentiel implements ImportReferentielShell {

    private final ShellReferencielService shellReferencielService;
    private final FileService fileService;

    @Override
    @ShellMethod(value = "Import contrats")
    public void importContrats() {
        shellReferencielService.createOrUpdateContrats(fileService.importCSV(CONTRATS));
    }

    @Override
    @ShellMethod(value = "Import natures d'établissements")
    public void importNatures() {
        shellReferencielService.createOrUpdateNatures(fileService.importCSV(NATURES));
    }

    @Override
    @ShellMethod("Import des certifications professionnelles (RNCP)")
    public void importRncp() {
        shellReferencielService.createOrUpdateCertificationsRncp(fileService.importXmlFromZip(CERTIFICATIONS_RNCP));
    }

    @Override
    @ShellMethod("Import du Répertoire Opérationnel des Métiers et des Emplois (ROME)")
    public void importRome() {
        fileService.importRomeFromZip(ROME);

        List<RomeDataset> romeDatasetList = fileService.importRomeData(ROME, "unix_referentiel_code_rome_v460_utf8.csv", RomeDataset.class);
        List<RomeAppellationDataset> romeAppellationDatasetList = fileService.importRomeData(ROME, "unix_referentiel_appellation_v460_utf8.csv", RomeAppellationDataset.class);
        List<RomeBlocDataset> romeBlocDatasetList = fileService.importRomeData(ROME, "unix_texte_v460_utf8.csv", RomeBlocDataset.class);
        List<ArborescenceCompetenceDataset> arborescenceCompetenceDatasetList = fileService.importRomeData(ROME, "unix_arborescence_competences_v460_utf8.csv", ArborescenceCompetenceDataset.class);

        shellReferencielService.createOrUpdateRome(arborescenceCompetenceDatasetList);
        shellReferencielService.createOrUpdateRome(romeDatasetList, romeAppellationDatasetList, romeBlocDatasetList);
    }
}
