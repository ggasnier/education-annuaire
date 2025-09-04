package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.datasets.etablissements.SectionInternationaleDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ShellEtablissementServiceImplTest {


    @Mock
    private EtablissementMapper etablissementMapper;

    @Mock
    private CoreEtablissementService coreEtablissementService;

    @Mock
    private ShellEntityService shellEntityService;

    @InjectMocks
    private ShellEtablissementServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrUpdateSectionsInternationalesTest() {
        // given
        var entity = new EtablissementEntity();
        entity.setUai("0750683K");
        when(coreEtablissementService.findEtablissement("0750683K")).thenReturn(Optional.of(entity));

        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setUai("0750683K");
        dataset.setSection("ALLEMANDE");
        dataset.setNiveau("2nde et BFI");
        List<SectionInternationaleDataset> datasets = List.of(dataset);

        assertEquals("Import terminé : 1 sections internationale(s).", service.createOrUpdateSectionsInternationales(datasets));
    }

}