package com.guillaumegasnier.education.shell.datasets.etablissements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.guillaumegasnier.education.core.enums.OptionEtablissement.RELAIS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OnisepDispositifDatasetTest {

    OnisepDispositifDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new OnisepDispositifDataset();
    }

    @Test
    void getOptionTest() {
        assertNull(dataset.getOption());
        dataset.setNom("dispositif relais");
        assertEquals(RELAIS, dataset.getOption());
        dataset.setNom("dispositif");
        assertNull(dataset.getOption());
    }

    @Test
    void getLangueListTest() {

    }
}