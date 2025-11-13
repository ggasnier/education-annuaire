package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void testGetOptions() {
        assertNotNull(dataset.getOptions());
    }
}