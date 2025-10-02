package com.guillaumegasnier.education.shell.datasets.ips;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IPSGlobalDatasetTest {

    IPSGlobalDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new IPSGlobalDataset();
    }

    @Test
    void getUaiTest() {
        dataset.setUai("0750683K");
        assertEquals("0750683K", dataset.getUai());
    }

    @Test
    void getAnneeTest() {
        dataset.setRentreeScolaire("2025-07-01");
        assertEquals(2025, dataset.getAnnee());
    }

    @Test
    void getIndiceTest() {
        assertNull(dataset.getIndice());
        dataset.setIndice1(" ");
        dataset.setIndice2("NA");
        dataset.setIndice3("140.5");
        assertEquals(Double.parseDouble("140.5"), dataset.getIndice());
    }

    @Test
    void getEcartTypeTest() {
        assertNull(dataset.getEcartType());
        dataset.setEcartType1(" ");
        dataset.setEcartType2("NS");
        dataset.setEcartType3("24.5");
        assertEquals(Double.parseDouble("24.5"), dataset.getEcartType());
    }
}