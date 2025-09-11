package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSCollege2023Dataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ShellEtablissementServiceImplTest {

    @Mock
    private EtablissementMapper etablissementMapper;
    @Mock
    private CoreEtablissementService coreEtablissementService;
    @Mock
    private ShellEntityService shellEntityService;
    @InjectMocks
    private ShellEtablissementServiceImpl service;
    private String uaiExiste = "0750683K";
    private String uaiAbsent = "0750652B";
    private EtablissementEntity etablissementEntityExiste;
    private EtablissementEntity etablissementEntityAbsent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        etablissementEntityExiste = new EtablissementEntity();
        etablissementEntityExiste.setUai(uaiExiste);

        etablissementEntityAbsent = new EtablissementEntity();
        etablissementEntityAbsent.setUai(uaiAbsent);

        when(coreEtablissementService.findEtablissement(uaiExiste)).thenReturn(Optional.of(etablissementEntityExiste));
        when(coreEtablissementService.findEtablissement(uaiAbsent)).thenReturn(Optional.empty());
    }

    @Test
    void dedoublementTest() {
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste + ";" + uaiAbsent);
        dataset.setSiret("12345,3564654654");

        assertEquals(4, service.dedoublement(dataset).count());
    }

    @Test
    void createOrUpdateSectionsInternationalesTest() {
        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setUai(uaiExiste);
        dataset.setSection("ALLEMANDE");
        dataset.setNiveau("2nde et BFI");
        List<SectionInternationaleDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 sections internationale(s).", service.createOrUpdateSectionsInternationales(datasets));
    }

    @Test
    void createOrUpdateNaturesTest() {
        NatureDataset dataset = new NatureDataset();
        dataset.setCode("123");
        dataset.setNom("Nature Test");

        NatureDataset dataset2 = new NatureDataset();
        dataset2.setCode("123");
        dataset2.setNom("Nature Test");
        dataset2.setDateFin("2025-01-01");

        List<NatureDataset> datasets = List.of(dataset, dataset2);

        assertEquals("Import terminé : 2 natures(s) enregistrée(s).", service.createOrUpdateNatures(datasets));
    }

    @Test
    void createOrUpdateContratsTest() {
        ContratDataset dataset = new ContratDataset();
        dataset.setCode("12");
        dataset.setNom("Nature Test");

        ContratDataset dataset2 = new ContratDataset();
        dataset2.setCode("13");
        dataset2.setNom("Nature Test");
        dataset2.setDateFin("2025-01-01");

        List<ContratDataset> datasets = List.of(dataset, dataset2);

        assertEquals("Import terminé : 2 contrat(s) enregistré(s).", service.createOrUpdateContrats(datasets));
    }

    @Test
    void createOrUpdateIPSCollegesTest() {
        IPSCollege2023Dataset dataset = new IPSCollege2023Dataset();
        dataset.setUai(uaiExiste);
        dataset.setIndice("140.00");
        dataset.setEcartType("40.00");
        dataset.setRentreeScolaire("2024");

        List<IPSCollege2023Dataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 ips enregistrée(s).", service.createOrUpdateIPSColleges(datasets));
    }

    @Test
    void createOrUpdateSectionsSportivesTest() {
        SectionSportiveDataset dataset = new SectionSportiveDataset();
        dataset.setUai(uaiExiste);
        dataset.setSections("VTT");

        List<SectionSportiveDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 sections sportives enregistrée(s).", service.createOrUpdateSectionsSportives(datasets));
    }

    @Test
    void createOrUpdateSectionsSportEtudesTest() {
        SectionSportEtudeDataset dataset = new SectionSportEtudeDataset();
        dataset.setUai(uaiExiste);
        dataset.setNomSport("VTT");

        List<SectionSportEtudeDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 sections sport etudes enregistrée(s).", service.createOrUpdateSectionsSportEtudes(datasets));
    }

    @Test
    void createOrUpdateLanguesTest() {
        LangueDataset dataset = new LangueDataset();
        dataset.setUai(uaiExiste);
        dataset.setLangue("anglais");

        List<LangueDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 langues enregistrée(s).", service.createOrUpdateLangues(datasets));
    }

    @Test
    void createOrUpdateSpecialitesTest() {
        SpecialitePremiereDataset dataset = new SpecialitePremiereDataset();
        dataset.setUai(uaiExiste);
        dataset.setEnseignements("Mathématiques");

        List<SpecialitePremiereDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 specialités enregistrée(s).", service.createOrUpdateSpecialites(datasets));
    }

    @Test
    void createOrUpdateSectionsBinationalesTest() {
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        dataset.setUai(uaiExiste);
        dataset.setTypeBac("Abibac");

        List<SectionBinationaleDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 sections binationale enregistrée(s).", service.createOrUpdateSectionsBinationales(datasets));

    }

}