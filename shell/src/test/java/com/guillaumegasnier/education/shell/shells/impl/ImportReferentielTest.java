package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ImportReferentielTest {

    private ShellReferencielService shellReferencielService;
    private FileService fileService;
    private ImportReferentiel importReferentiel;

    @BeforeEach
    void setUp() {
        shellReferencielService = mock(ShellReferencielService.class);
        fileService = mock(FileService.class);
        importReferentiel = new ImportReferentiel(shellReferencielService, fileService);
    }

    @Test
    void importContratsTest() {
        importReferentiel.importContrats();
        verify(fileService).importCSV(any());
        verify(shellReferencielService).createOrUpdateContrats(any());
    }

    @Test
    void importNaturesTest() {
        importReferentiel.importNatures();
        verify(fileService).importCSV(any());
        verify(shellReferencielService).createOrUpdateNatures(any());

    }

    @Test
    void importRncpTest() {
        importReferentiel.importRncp();
        verify(fileService).importXmlFromZip(any());
        verify(shellReferencielService).createOrUpdateCertificationsRncp(any());

    }
}