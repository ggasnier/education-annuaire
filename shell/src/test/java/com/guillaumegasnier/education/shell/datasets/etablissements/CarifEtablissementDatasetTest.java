package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CarifEtablissementDatasetTest {


    CarifEtablissementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new CarifEtablissementDataset();
    }

    @Test
    void testGetSiret() {
        assertNull(dataset.getSiret());
        dataset.setSiret("");
        assertNull(dataset.getSiret());
        dataset.setSiret("123456789");
        assertEquals("123456789", dataset.getSiret());
    }
}