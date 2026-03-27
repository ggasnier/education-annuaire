package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.enums.Sources;
import com.guillaumegasnier.education.shell.config.RestClientConfig;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.guillaumegasnier.education.shell.datasets.LheoSubtype;
import com.guillaumegasnier.education.shell.enums.SourcesDatasets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductionFileServiceTest {

    private ProductionFileService productionFileService;
    private RestClientConfig.TrustAllHttpConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        RestClient restClient = mock(RestClient.class, RETURNS_DEEP_STUBS);
        productionFileService = new ProductionFileService(restClient, connectionFactory);
    }

    @Test
    void openFile_shouldReturnEmptyOptional_whenUrlIsInvalid() {
        Optional<BufferedReader> result = productionFileService.openFile("http://invalid-url", StandardCharsets.UTF_8, "GET");
        assertTrue(result.isEmpty());
    }

    @Test
    void importCSV_shouldReturnEmptyList_whenFileIsNotFound() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getNom()).thenReturn("test");
        when(mockSource.getUrl()).thenReturn("http://invalid-url");
        when(mockSource.getCharset()).thenReturn(StandardCharsets.UTF_8);
        when(mockSource.getHttpMethod()).thenReturn("GET");
        when(mockSource.getSeparator()).thenReturn(',');
        when(mockSource.getSource()).thenReturn(Sources.CARIF);
        when(mockSource.getLocalPath()).thenReturn("test.csv");
        List<Dataset> result = productionFileService.importCSV(mockSource);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void importLheoSubtypeFromZip_shouldReturnNull_whenUrlIsInvalid() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getNom()).thenReturn("test");
        when(mockSource.getUrl()).thenReturn("http://invalid-url/test.zip");
        when(mockSource.getLocalPath()).thenReturn("test.xml");
        LheoSubtype result = productionFileService.importLheoSubtypeFromZip(mockSource);
        assertNull(result);
    }

    @Test
    void saveResultAsJson_shouldNotThrow_whenResultIsNullOrEmpty() {
        SourcesDatasets mockSource = mock(SourcesDatasets.class);
        when(mockSource.getSource()).thenReturn(Sources.CARIF);
        when(mockSource.getLocalPath()).thenReturn("test.json");
        productionFileService.saveResultAsJson(null, mockSource);
        productionFileService.saveResultAsJson(List.of(), mockSource);
    }
}
