package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.guillaumegasnier.education.core.enums.Secteur.PU;
import static com.guillaumegasnier.education.core.enums.Secteur.PV;
import static org.junit.jupiter.api.Assertions.*;

class OnisepEtablissementSupDatasetTest {

    OnisepEtablissementSupDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new OnisepEtablissementSupDataset();
    }

    @Test
    void getSiretTest() {
        assertNull(dataset.getSiret());
        dataset.setSiret("");
        assertNull(dataset.getSiret());
        dataset.setSiret("123456789");
        assertEquals("123456789", dataset.getSiret());
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
    void getSecteurTest() {
        assertNull(dataset.getSecteur());
        dataset.setStatut("privé");
        assertEquals(PV, dataset.getSecteur());
        dataset.setStatut("public");
        assertEquals(PU, dataset.getSecteur());
        dataset.setStatut("Autre");
        assertNull(dataset.getSecteur());
    }

    @Test
    void testCloneWithUai() {
        dataset.setUai("1234567A");
        dataset.setNom("Lycée Test");

        OnisepEtablissementSupDataset copy = dataset.cloneWithUai("1234568B");

        assertEquals("1234568B", copy.getUai());
        assertEquals("Lycée Test", copy.getNom());
    }

    @Test
    void testCloneWithSiret() {
        dataset.setUai("1234567A");
        dataset.setNom("Lycée Test");

        OnisepEtablissementSupDataset copy = dataset.cloneWithSiret("123456789900212");

        assertEquals("123456789900212", copy.getSiret());
        assertEquals("Lycée Test", copy.getNom());
    }

    @Test
    void getJPOTest() {
        assertNotNull(dataset.getJPO());
        dataset.setJpo("");
        assertNotNull(dataset.getJPO());
        dataset.setJpo("le 30/05/2026 de 09h00 à 13h00");
        assertNotNull(dataset.getJPO());
        assertEquals(1, dataset.getJPO().size());
    }

    @Test
    void testGetContacts() {
        assertNotNull(dataset.getContacts());
        dataset.setContactTelephone("");
        assertEquals(0, dataset.getContacts().size());
        dataset.setContactTelephone("0102030405");
//        dataset.setContactMail("ici@perdu.com");
//        dataset.setContactWeb("https://perdu.com");
        assertEquals(1, dataset.getContacts().size());
    }
}