package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.Test;

import static com.guillaumegasnier.education.core.enums.OptionEtablissement.*;
import static org.junit.jupiter.api.Assertions.*;

public class GlobalDatasetTest {

    @Test
    void testSportDataset() {
        SportDataset dataset = new SportDataset();
        dataset.setUai("1234567a");
        assertEquals("1234567A", dataset.getUai());
        assertNotNull(dataset.getSectionList());
        dataset.setNomSport("escrime");
        assertNotNull(dataset.getSectionList());
        assertEquals(1, dataset.getSectionList().size());
        dataset.setNomSport(null);
        dataset.setSections("escrime,natation");
        assertNotNull(dataset.getSectionList());
        assertEquals(2, dataset.getSectionList().size());
    }

    @Test
    void testSpecialitePremiereDataset() {
        SpecialitePremiereDataset dataset = new SpecialitePremiereDataset();
        dataset.setEnseignements("Mathématiques");
        assertNotNull(dataset.getSpecialites());
    }

    @Test
    void testSectionInternationaleDataset() {
        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setNiveau("2nd et BFI");
        assertNotNull(dataset.getNiveaux());
        assertEquals(2, dataset.getNiveaux().size());
    }

    @Test
    void testSectionBinationaleDataset() {
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        assertNull(dataset.getOption());
        dataset.setTypeBac("Abibac");
        assertEquals(ABIBAC, dataset.getOption());
        dataset.setTypeBac("Bachibac");
        assertEquals(BACHIBAC, dataset.getOption());
        dataset.setTypeBac("Esabac");
        assertEquals(ESABAC, dataset.getOption());
        dataset.setTypeBac("Autre");
        assertNull(dataset.getOption());
    }

    @Test
    void testLangueDataset() {
        LangueDataset dataset = new LangueDataset();
        dataset.setLangue("Anglais");
        dataset.setEnseignement("LV1");
        assertEquals("anglais", dataset.getLangue());
        assertEquals("lv1", dataset.getEnseignement());
    }

    @Test
    void testTravailOrganismeFormationDataset() {
        TravailOrganismeFormationDataset dataset = new TravailOrganismeFormationDataset();
        dataset.setAdresse("lorem ipsum");
        assertEquals("lorem ipsum", dataset.getAdresse());
    }
}
