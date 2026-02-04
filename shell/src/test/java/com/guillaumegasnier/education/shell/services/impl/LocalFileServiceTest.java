package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.enums.Sources;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.datasets.etablissements.CarifEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.formations.CarifFormationDataset;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocalFileServiceTest {
    private LocalFileService localFileService;

    @BeforeEach
    void setUp() {
        localFileService = new LocalFileService();
    }

    @Test
    void openFile_shouldReturnEmptyOptional_whenFileDoesNotExist() {
        Optional<BufferedReader> result = localFileService.openFile("nonexistent.csv", StandardCharsets.UTF_8, "GET");
        assertTrue(result.isEmpty());
    }

    @Test
    void importCSV_shouldReturnEmptyList_whenFileDoesNotExist() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getSource()).thenReturn(Sources.EN);
        when(mockSource.getLocalPath()).thenReturn("nonexistent.csv");
        //when(mockSource.getDatasetClass()).thenReturn(EnEtablissementDataset.class);
        when(mockSource.getSeparator()).thenReturn(',');
        List<Dataset> result = localFileService.importCSV(mockSource);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void saveResultAsJson_shouldLogError() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        localFileService.saveResultAsJson(List.of("test"), mockSource);
        // No exception should be thrown, method is a no-op
    }

    @Test
    void importCarifEtablissements_shouldReturnEmptyList() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        List<CarifEtablissementDataset> result = localFileService.importCarifEtablissements(mockSource);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void importCarifFormations_shouldReturnEmptyList() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        List<CarifFormationDataset> result = localFileService.importCarifFormations(mockSource);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void importXmlFromZip_shouldReturnNull_whenFileDoesNotExist() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getSource()).thenReturn(Sources.EN);
        when(mockSource.getLocalPath()).thenReturn("nonexistent.xml");
        FICHES result = localFileService.importXmlFromZip(mockSource);
        assertNull(result);
    }

    @Test
    void importLheoSubtypeFromZip_shouldReturnNull_whenFileDoesNotExist() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getSource()).thenReturn(Sources.EN);
        when(mockSource.getLocalPath()).thenReturn("nonexistent.xml");
        LheoSubtype result = localFileService.importLheoSubtypeFromZip(mockSource);
        assertNull(result);
    }
}
