package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarifEtablissementDatasetTest {

    CarifEtablissementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new CarifEtablissementDataset();
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
    void getNomTest() {
        assertNull(dataset.getNom());
        dataset.setOnisepNom("Nom ONISEP");
        assertEquals("Nom ONISEP", dataset.getNom());
    }

    @Test
    void isActifTest() {
        assertTrue(dataset.isActif());
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
    void getUaiTest() {
        assertEquals("", dataset.getUai());
        dataset.setUai("1234567A");
        assertEquals("1234567A", dataset.getUai());
    }

    @Test
    void getOptionsTest() {
        assertEquals(1, dataset.getOptions().size());
    }

    @Test
    void getDateFermetureTest() {
        assertNull(dataset.getDateFermeture());
        dataset.setDateFermeture("2026-01-01");
        assertNotNull(dataset.getDateFermeture());
        dataset.setDateFermeture("01/01/2026");
        assertNull(dataset.getDateFermeture());
    }

    @Test
    void getAdresseTest() {
        assertNull(dataset.getAdresse());
        dataset.setNumeroVoie("Ici");
        dataset.setTypeVoie("Voie");
        dataset.setNomVoie("ou là");
        assertEquals("Ici Voie ou là", dataset.getAdresse());
    }
}