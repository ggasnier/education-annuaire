package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellReferencielService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ShellReferencielServiceImplTest {

    private CoreEtablissementService coreEtablissementService;

    private CoreReferentielService coreReferentielService;

    private ShellReferencielService service;

    @BeforeEach
    void setUp() {
        coreEtablissementService = mock(CoreEtablissementService.class);
        coreReferentielService = mock(CoreReferentielService.class);
        EtablissementMapper etablissementMapper = Mappers.getMapper(EtablissementMapper.class);
        ReferentielMapper referentielMapper = Mappers.getMapper(ReferentielMapper.class);
        service = new ShellReferencielServiceImpl(coreEtablissementService, etablissementMapper, referentielMapper, coreReferentielService);
    }

    @Test
    void createOrUpdateContrats() {
        List<ContratDataset> datasets = new ArrayList<>();
        datasets.add(new ContratDataset());
        service.createOrUpdateContrats(datasets);
        verify(coreEtablissementService).saveContrats(any());
    }

    @Test
    void createOrUpdateNatures() {
        List<NatureDataset> datasets = new ArrayList<>();
        datasets.add(new NatureDataset());
        service.createOrUpdateNatures(datasets);
        verify(coreEtablissementService).saveNatures(any());
    }

}