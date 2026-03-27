package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IPSCollege2023DatasetTest {

    IPSCollege2023Dataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new IPSCollege2023Dataset();
    }

    @Test
    void testDoubleFormat() {
        dataset.setIndice("123.4");
        dataset.setEcartType("123.5");
        dataset.setIndiceDepartementPublic("123.6");
        dataset.setIndiceDepartementPrive("123.7");
        dataset.setIndiceAcademiePublic("123.8");
        dataset.setIndiceAcademiePrive("123.9");

        assertEquals(Double.parseDouble("123.4"), dataset.getIndice());
        assertEquals(Double.parseDouble("123.5"), dataset.getEcartType());
        assertEquals(Double.parseDouble("123.6"), dataset.getIndiceDepartementPublic());
        assertEquals(Double.parseDouble("123.7"), dataset.getIndiceDepartementPrive());
        assertEquals(Double.parseDouble("123.8"), dataset.getIndiceAcademiePublic());
        assertEquals(Double.parseDouble("123.9"), dataset.getIndiceAcademiePrive());
    }

    @Test
    void testAnnee() {
        dataset.setRentreeScolaire("2024-2025");
        assertEquals(2024, dataset.getAnnee());

    }
}