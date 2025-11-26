package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EffectifsCollegeDatasetTest {

    EffectifsCollegeDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new EffectifsCollegeDataset();
    }

    @Test
    void testAnnee() {
        dataset.setRentreeScolaire("2024-2025");
        assertEquals(2024, dataset.getAnnee());
    }
}