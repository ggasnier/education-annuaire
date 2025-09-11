package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.datasets.references.AcademieDataset;
import com.guillaumegasnier.education.shell.datasets.references.PaysDataset;
import com.guillaumegasnier.education.shell.datasets.references.RegionDataset;
import com.guillaumegasnier.education.shell.mappers.ReferenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShellReferenceServiceImplTest {

    @Mock
    private CoreReferenceService coreReferenceService;

    @Mock
    private ReferenceMapper referenceMapper;

    @InjectMocks
    private ShellReferenceServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrUpdatePaysTest() {
        List<PaysDataset> datasets = List.of(new PaysDataset());
        assertEquals("Import terminé : 1 pays enregistrée(s).", service.createOrUpdatePays(datasets));
    }

    @Test
    void createOrUpdateRegionsTest() {
        List<RegionDataset> datasets = List.of(new RegionDataset());
        assertEquals("Import terminé : 1 région(s) enregistrée(s).", service.createOrUpdateRegions(datasets));
    }

    @Test
    void createOrUpdateAcademiesTest() {
        List<AcademieDataset> datasets = List.of(new AcademieDataset());
        assertEquals("Import terminé : 1 académie(s) enregistrée(s).", service.createOrUpdateAcademies(datasets));
    }

    @Test
    void createOrUpdateDepartementsTest() {

    }

    @Test
    void createOrUpdateCommunesTest() {

    }


}