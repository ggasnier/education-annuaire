package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepPK;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        FormationDTO dto = new FormationDTO();
        dto.setFormationId(1L);
        dto.setParcoursupId(null);

        dto.setOnisepId(123);
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        dto.setOnisepId(0);
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        dto.setOnisepId(null);
        dto.setParcoursupId(456);
        when(coreFormationService.findLienOnisep("PS", "456")).thenReturn(Optional.empty());
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        LienOnisepEntity entity = new LienOnisepEntity();
        entity.setPk(new LienOnisepPK(123, "PS", "789"));

        dto.setParcoursupId(789);
        when(coreFormationService.findLienOnisep("PS", "789")).thenReturn(Optional.of(entity));
        assertEquals(7284277920559019884L, transformer.recalculId(dto).getFormationId());

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