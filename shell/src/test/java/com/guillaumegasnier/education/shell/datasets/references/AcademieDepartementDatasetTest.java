package com.guillaumegasnier.education.shell.datasets.references;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcademieDepartementDatasetTest {

    AcademieDepartementDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new AcademieDepartementDataset();
    }

    @Test
    void getCodeDepartement() {

        dataset.setCodeDepartement("001");
        assertEquals("01", dataset.getCodeDepartement());

        dataset.setCodeDepartement("978");
        assertEquals("978", dataset.getCodeDepartement());
    }
}