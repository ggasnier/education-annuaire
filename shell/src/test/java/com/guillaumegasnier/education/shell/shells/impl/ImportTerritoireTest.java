package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellTerritoireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImportTerritoireTest {

    private ShellTerritoireService shellTerritoireService;
    private FileService fileService;
    private ImportTerritoire importTerritoire;

    @BeforeEach
    void setUp() {
        shellTerritoireService = mock(ShellTerritoireService.class);
        fileService = mock(FileService.class);
        importTerritoire = new ImportTerritoire(shellTerritoireService, fileService);
    }

    @Test
    void importTerritoiresTest() {
        importTerritoire.importTerritoires();
        verify(shellTerritoireService).createOrUpdatePays(any());
        verify(shellTerritoireService).createOrUpdateRegions(any());
        verify(shellTerritoireService).createOrUpdateAcademies(any());
        verify(shellTerritoireService, times(2)).createOrUpdateCommunes(any());
        verify(shellTerritoireService, times(2)).createOrUpdateDepartements(any(), any());
    }

}