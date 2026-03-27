package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImportFormationTest {

    private ShellFormationService shellFormationService;
    private FileService fileService;
    private ImportFormation importFormation;

    @BeforeEach
    void setUp() {
        shellFormationService = mock(ShellFormationService.class);
        fileService = mock(FileService.class);
        importFormation = new ImportFormation(shellFormationService, fileService);
    }

    @Test
    void importOrganismesFormationsTest() {
        importFormation.importOrganismesFormations();
        verify(fileService).importCSV(any());
        verify(shellFormationService).createOrUpdateOrganismes(any());
    }

    @Test
    void importFormationsCpfTest() {
        importFormation.importFormationsCpf();
        verify(fileService).importCSV(any());
        verify(shellFormationService).createOrUpdateFormationsCpf(any());
    }

    @Test
    void importFormationsParcoursupTest() {
        importFormation.importFormationsParcoursup();
        verify(fileService).importCSV(any());
        verify(shellFormationService).createOrUpdateFormationsParcoursup(any());
    }

    @Test
    void importFormationsOnisepEsrTest() {
        importFormation.importFormationsOnisepEsr();
        verify(fileService).importCSV(any());
        verify(shellFormationService).createOrUpdateFormationsOnisepEsr(any());
    }

    @Test
    void importFormationsCarifTest() {
        importFormation.importFormationsCarif();
        verify(fileService).importCarifFormations(any());
        verify(shellFormationService).createOrUpdateFormationsCarif(any());
    }

    @Test
    void importFormationsOnisepLheoTest() {
        importFormation.importFormationsOnisepLheo();
        verify(fileService, times(1)).importLheoSubtypeFromZip(any());
        verify(shellFormationService, times(1)).createOrUpdateFormationsOnisepLheo(any());
    }

}