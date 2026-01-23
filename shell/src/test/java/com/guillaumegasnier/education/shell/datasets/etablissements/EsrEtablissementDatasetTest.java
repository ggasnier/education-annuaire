package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.guillaumegasnier.education.core.enums.Secteur.PU;
import static com.guillaumegasnier.education.core.enums.Secteur.PV;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EsrEtablissementDatasetTest {

    EsrEtablissementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new EsrEtablissementDataset();
    }

    @Test
    void testCloneWithUai() {
        dataset.setUai("1234567A");
        dataset.setNom("Lycée Test");

        EsrEtablissementDataset copy = dataset.cloneWithUai("1234568B");

        assertEquals("1234568B", copy.getUai());
        assertEquals("Lycée Test", copy.getNom());
    }

    @Test
    void testCloneWithSiret() {
        dataset.setUai("1234567A");
        dataset.setNom("Lycée Test");

        EsrEtablissementDataset copy = dataset.cloneWithSiret("123456789900212");

        assertEquals("123456789900212", copy.getSiret());
        assertEquals("Lycée Test", copy.getNom());
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
    void getSecteurTest() {
        assertNull(dataset.getSecteur());
        dataset.setSecteurEtablissement("privé");
        assertEquals(PV, dataset.getSecteur());
        dataset.setSecteurEtablissement("public");
        assertEquals(PU, dataset.getSecteur());
        dataset.setSecteurEtablissement("Autre");
        assertNull(dataset.getSecteur());
    }
}