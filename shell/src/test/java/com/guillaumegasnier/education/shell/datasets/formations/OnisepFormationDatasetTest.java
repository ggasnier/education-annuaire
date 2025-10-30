package com.guillaumegasnier.education.shell.datasets.formations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnisepFormationDatasetTest {

    OnisepFormationDataset dataset;

    @BeforeEach
    void setUp() {
        dataset = new OnisepFormationDataset();
    }

    @Test
    void getIdTest() {
        dataset.setFormationUrl("https://www.onisep.fr/http/redirection/formation/slug/FOR.270");
        assertEquals(270, dataset.getActionFormationOnisepId());
    }
}