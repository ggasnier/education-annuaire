package com.guillaumegasnier.education.shell.shells.impl;

import com.guillaumegasnier.education.shell.services.FileService;
import com.guillaumegasnier.education.shell.services.ShellEtablissementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ImportEtablissementTest {

    private ShellEtablissementService shellEtablissementService;
    private FileService fileService;
    private ImportEtablissement importEtablissement;

    @BeforeEach
    void setUp() {
        shellEtablissementService = mock(ShellEtablissementService.class);
        fileService = mock(FileService.class);
        importEtablissement = new ImportEtablissement(shellEtablissementService, fileService);
    }

    @Test
    void testImportEtablissementsEnOuverts() {
        importEtablissement.importEtablissementsEnOuverts();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateEtablissements(any(), eq("en"));
    }

    @Test
    void testImportEtablissementsEnFermes() {
        importEtablissement.importEtablissementsEnFermes();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateEtablissements(any(), eq("en"));
    }

    @Test
    void testImportEtablissementsEsr() {
        importEtablissement.importEtablissementsEsr();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateEtablissements(any(), eq("esr"));
    }

    @Test
    void testImportEtablissementsMasa() {
        importEtablissement.importEtablissementsMasa();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateEtablissements(any(), eq("masa"));
    }

    @Test
    void importEtablissementsJpoTest() {
        importEtablissement.importEtablissementsJpo();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateJpo(any(), eq("masa"));
    }

    @Test
    void testImportEtablissementsOnisep() {
        importEtablissement.importEtablissementsOnisepSup();
        verify(fileService, times(2)).importCSV(any());
        verify(shellEtablissementService, times(2)).createOrUpdateEtablissements(any(), eq("onisep"));
    }

    @Test
    void testImportSports() {
        importEtablissement.importSports();
        verify(fileService, times(2)).importCSV(any());
        verify(shellEtablissementService, times(2)).createOrUpdateSports(any(), any(), eq("en"));
    }

    @Test
    void testImportLangues() {
        importEtablissement.importLangues();
        verify(fileService, times(3)).importCSV(any());
        verify(shellEtablissementService, times(1)).createOrUpdateLangues(any(), eq("en"));
        verify(shellEtablissementService, times(1)).createOrUpdateSectionsInternationales(any(), eq("en"));
        verify(shellEtablissementService, times(1)).createOrUpdateSectionsBinationales(any(), eq("gg"));
    }

    @Test
    void testImportEtablissements() {
        importEtablissement.importEtablissements();
        verify(fileService, times(6)).importCSV(any());
    }

    @Test
    void testImportEffectifs() {
        importEtablissement.importEffectifs();
        verify(fileService, times(3)).importCSV(any());
        verify(shellEtablissementService, times(1)).createOrUpdateEffectifs(any());
    }

    @Test
    void testImportIpsColleges() {
        importEtablissement.importIpsColleges();
        verify(fileService, times(3)).importCSV(any());
        verify(shellEtablissementService, times(3)).createOrUpdateIPS(any());
    }

    @Test
    void testImportSpecialites() {
        importEtablissement.importSpecialites();
        verify(fileService, times(1)).importCSV(any());
        verify(shellEtablissementService, times(1)).createOrUpdateSpecialites(any(), eq("onisep"));
    }

    @Test
    void importDispositifsTest() {
        importEtablissement.importDispositifs();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateDispositifs(any(), any());
    }

    @Test
    void importEuroscolTest() {
        importEtablissement.importEuroscol();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateEuroscol(any(), eq("en"));
    }

    @Test
    void importIvaCollegesTest() {
        importEtablissement.importIvaColleges();
        verify(fileService).importCSV(any());
        verify(shellEtablissementService).createOrUpdateIVA(any());
    }

    @Test
    void importEtablissementsDetailsTest() {
        importEtablissement.importEtablissementsDetails();
        verify(fileService, times(7)).importCSV(any());
    }

}
