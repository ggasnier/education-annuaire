package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.OptionEtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.SectionInternationaleEntity;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.etablissements.SectionBinationaleDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.SectionInternationaleDataset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShellEntityServiceImplTest {

    @Mock
    private CoreEtablissementService coreEtablissementService;

    @InjectMocks
    private ShellEntityServiceImpl service;

    private String uaiExiste = "0750683K";
    private String uaiAbsent = "0750652B";

    private EtablissementEntity etablissementEntityExiste;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        etablissementEntityExiste = new EtablissementEntity();
        etablissementEntityExiste.setUai(uaiExiste);

        when(coreEtablissementService.findEtablissement(uaiExiste)).thenReturn(Optional.of(etablissementEntityExiste));
        when(coreEtablissementService.findEtablissement(uaiAbsent)).thenReturn(Optional.empty());
    }

    @Test
    void toSectionInternationaleEntityUaiNotFound() {
        // given
        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<SectionInternationaleEntity> result = service.toSectionInternationaleEntity(dataset);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void toSectionInternationaleEntityUaiFound() {
        // given
        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setUai(uaiExiste);
        dataset.setSection("ALLEMANDE");
        dataset.setNiveau("2nde et BFI");
        // when
        List<SectionInternationaleEntity> result = service.toSectionInternationaleEntity(dataset);
        // then
        assertFalse(result.isEmpty());
    }

    @Test
    void toOptionEtablissementEntitySectionBinationaleUaiNotFound() {
        // given
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        dataset.setUai(uaiAbsent);
        // when
        OptionEtablissementEntity result = service.toOptionEtablissementEntity(dataset);
        // then
        assertNull(result);
    }

    @Test
    void toOptionEtablissementEntitySectionBinationaleUaiFound() {
        // given
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        dataset.setUai(uaiExiste);
        dataset.setTypeBac("Abibac");
        // when
        OptionEtablissementEntity result = service.toOptionEtablissementEntity(dataset);
        // then
        assertNotNull(result);
        assertEquals(OptionEtablissement.ABIBAC, result.getPk().getOption());
    }
}
