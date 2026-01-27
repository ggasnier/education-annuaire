package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FormationTransformerImplTest {

    @Mock
    private CoreTerritoireService coreTerritoireService;
    @Mock
    private CoreFormationService coreFormationService;
    @Mock
    private CoreEtablissementService coreEtablissementService;

    private FormationTransformerImpl transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EtablissementMapper etablissementMapper = Mappers.getMapper(EtablissementMapper.class);
        FormationMapper formationMapper = Mappers.getMapper(FormationMapper.class);
        transformer = new FormationTransformerImpl(coreTerritoireService, coreFormationService, coreEtablissementService, formationMapper, etablissementMapper);
    }

    @Test
    void recalculId() {
    }

    @Test
    void toOrganismeEntity() {
    }

    @Test
    void toFormationEntity() {
    }

    @Test
    void toActionFormationEntity() {
    }
}