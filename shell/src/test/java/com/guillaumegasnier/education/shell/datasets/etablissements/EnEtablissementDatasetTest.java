package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.guillaumegasnier.education.core.enums.Secteur.PU;
import static com.guillaumegasnier.education.core.enums.Secteur.PV;
import static org.junit.jupiter.api.Assertions.*;

class EnEtablissementDatasetTest {

    EnEtablissementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new EnEtablissementDataset();
    }

    @Test
    void testGetEducationPrioritaire() {
        assertNull(dataset.getEducationPrioritaire());
        dataset.setEducationPrioritaire("");
        assertNull(dataset.getEducationPrioritaire());
        dataset.setEducationPrioritaire("REP");
        assertEquals("REP", dataset.getEducationPrioritaire());
    }

    @Test
    void testGetContacts() {
        assertNotNull(dataset.getContacts());
        dataset.setContactTelephone("0102030405");
        dataset.setContactMail("ici@perdu.com");
        dataset.setContactWeb("https://perdu.com");
        assertEquals(3, dataset.getContacts().size());
    }

    @Test
    void testGetOptions() {
        assertNotNull(dataset.getOptions());
    }

    @Test
    void getDateOuvertureTest() {
        assertNull(dataset.getDateOuverture());
        dataset.setDateOuverture("2026-01-01");
        assertEquals(LocalDate.parse("2026-01-01"), dataset.getDateOuverture());
    }

    @Test
    void getAdresseTest() {
        assertNull(dataset.getAdresse());
        dataset.setAdresse("");
        assertNull(dataset.getAdresse());
        dataset.setAdresse("Lorem ipsum   ");
        assertEquals("Lorem ipsum", dataset.getAdresse());
        dataset.setAdresse("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nulla arcu, sagittis sed fermentum eu, egestas eu odio.");
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing", dataset.getAdresse());
    }

    @Test
    void getComplementTest() {
        assertNull(dataset.getComplement());
        dataset.setComplement("");
        assertNull(dataset.getComplement());
        dataset.setComplement("Lorem ipsum   ");
        assertEquals("Lorem ipsum", dataset.getComplement());
        dataset.setComplement("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nulla arcu, sagittis sed fermentum eu, egestas eu odio.");
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing", dataset.getComplement());
    }

    @Test
    void cloneWithUaiTest() {
        var dataset2 = dataset.cloneWithUai("1234567A");
        assertNotNull(dataset2);
    }

    @Test
    void cloneWithSiretTest() {
        var dataset2 = dataset.cloneWithSiret("siret12345");
        assertNotNull(dataset2);
    }

    @Test
    void isActifTest() {
        assertTrue(dataset.isActif());
    }

    @Test
    void getSecteurTest() {
        assertNull(dataset.getSecteur());
        dataset.setStatutPublicPrive("Privé");
        assertEquals(PV, dataset.getSecteur());
        dataset.setStatutPublicPrive("Public");
        assertEquals(PU, dataset.getSecteur());
        dataset.setStatutPublicPrive("Autre");
        assertNull(dataset.getSecteur());
    }

    @Test
    void getSiretTest() {
        assertNull(dataset.getSiret());
        dataset.setSiret("");
        assertNull(dataset.getSiret());
        dataset.setSiret("19010819100049");
        assertEquals("19010819100049", dataset.getSiret());
    }

}