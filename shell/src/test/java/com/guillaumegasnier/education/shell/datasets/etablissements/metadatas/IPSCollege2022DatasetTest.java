package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IPSCollege2022DatasetTest {

    IPSCollege2022Dataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new IPSCollege2022Dataset();
    }

    @Test
    void testDoubleFormat() {
        dataset.setIndice("123.4");
        dataset.setEcartType("123.5");

        assertEquals(Double.parseDouble("123.4"), dataset.getIndice());
        assertEquals(Double.parseDouble("123.5"), dataset.getEcartType());
    }

    @Test
    void testEffectifs() {
        dataset.setEffectifs("124");
        assertEquals(124, dataset.getEffectifs());
        dataset.setEffectifs("NA");
        assertNull(dataset.getEffectifs());
    }

    @Test
    void testAnnee() {
        dataset.setRentreeScolaire("2024-2025");
        assertEquals(2024, dataset.getAnnee());
    }
}