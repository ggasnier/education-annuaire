package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasaEtablissementDatasetTest {

    MasaEtablissementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new MasaEtablissementDataset();
    }

    @Test
    void getSiretTest() {
        assertNull(dataset.getSiret());
        dataset.setSiret("");
        assertNull(dataset.getSiret());
        dataset.setSiret("19 010 819 100 049");
        assertEquals("19010819100049", dataset.getSiret());
    }

    @Test
    void getSecteurTest() {
        assertNull(dataset.getSecteur());
        dataset.setSecteur("");
        assertNull(dataset.getSecteur());
        dataset.setSecteur("Privé");
        assertEquals(Secteur.PV, dataset.getSecteur());
        dataset.setSecteur("Public");
        assertEquals(Secteur.PU, dataset.getSecteur());
    }

    @Test
    void getCodeNatureTest() {
        dataset.setNomNature("MFREO");
        assertEquals("380", dataset.getCodeNature());
        dataset.setNomNature("Lycée polyvalent");
        assertEquals("306", dataset.getCodeNature());
        dataset.setNomNature("LEAP");
        assertEquals("320", dataset.getCodeNature());
        dataset.setNomNature("LPA");
        assertEquals("320", dataset.getCodeNature());
        dataset.setNomNature("Collège");
        assertEquals("340", dataset.getCodeNature());
        dataset.setNomNature("CFPPA");
        assertEquals("740", dataset.getCodeNature());
        dataset.setNomNature("LEGTA");
        assertEquals("300", dataset.getCodeNature());
        dataset.setNomNature("CFA");
        assertEquals("605", dataset.getCodeNature());
        dataset.setNomNature("CFA privé");
        assertEquals("605", dataset.getCodeNature());
        dataset.setNomNature("LEGTPA");
        assertEquals("307", dataset.getCodeNature());
        dataset.setNomNature("EREA");
        assertEquals("370", dataset.getCodeNature());
        dataset.setNomNature("Inconnu");
        assertNull(dataset.getCodeNature());
        dataset.setNomNature(null);
        assertNull(dataset.getCodeNature());
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
        dataset.setDateFinValidite("01/01/2025");
        assertFalse(dataset.isActif());
    }

    @Test
    void adresseTest() {
        // Adresse
        assertNull(dataset.getAdresse());
        dataset.setAdresse4("");
        assertNull(dataset.getAdresse());
        dataset.setAdresse4("ici et là bas");
        assertEquals("ici et là bas", dataset.getAdresse());
        // Complément
        assertNull(dataset.getComplement());
        dataset.setAdresse5("");
        assertNull(dataset.getComplement());
        dataset.setAdresse5("ailleurs");
        assertEquals("ailleurs", dataset.getComplement());
        // Code Postal
        assertNull(dataset.getCodePostal());
        dataset.setAdresse6("");
        assertNull(dataset.getCodePostal());
        dataset.setAdresse6("15005 AURILLAC CEDEX");
        assertEquals("15005", dataset.getCodePostal());
        // Code commune
        dataset.setCodeCommune("38399");
        assertEquals("38399", dataset.getCodeCommune());
    }

    @Test
    void getOptionsTest() {
        assertNotNull(dataset.getOptions());
        assertEquals(0, dataset.getOptions().size());
        dataset.setHebergement("Oui");
        dataset.setRestauration("Oui");
        assertEquals(2, dataset.getOptions().size());
        dataset.setHebergement("Non");
        dataset.setRestauration("");
        assertEquals(0, dataset.getOptions().size());
    }

}