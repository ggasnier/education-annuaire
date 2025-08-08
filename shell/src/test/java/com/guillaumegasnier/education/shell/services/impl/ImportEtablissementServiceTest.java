package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.datasets.etablissements.EsrEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportEtablissementServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCloneWithUai() {
        EsrEtablissementDataset original = new EsrEtablissementDataset();
        original.setUai("1234567A");
        original.setNom("Lycée Test");

        EtablissementDataset copy = original.cloneWithUai("1234568B");

        assertEquals("1234568B", copy.getUai());
        assertEquals("Lycée Test", copy.getNom());
    }
}