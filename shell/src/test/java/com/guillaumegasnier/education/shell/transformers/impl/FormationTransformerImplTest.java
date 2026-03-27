package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepPK;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.dto.formations.ActionFormationDTO;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FormationTransformerImplTest {

    String uaiExiste = "0750683K";
    String uaiAbsent = "0750652B";
    @Mock
    private CoreTerritoireService coreTerritoireService;
    @Mock
    private CoreFormationService coreFormationService;
    @Mock
    private CoreReferentielService coreReferentielService;
    @Mock
    private CoreEtablissementService coreEtablissementService;
    private FormationTransformerImpl transformer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EtablissementMapper etablissementMapper = Mappers.getMapper(EtablissementMapper.class);
        FormationMapper formationMapper = Mappers.getMapper(FormationMapper.class);
        transformer = new FormationTransformerImpl(coreTerritoireService, coreFormationService, coreEtablissementService, formationMapper, etablissementMapper, coreReferentielService);
    }

    @Test
    void recalculIdTest() {
        FormationDTO dto = new FormationDTO();
        dto.setFormationId(1L);
        dto.setParcoursupId(null);

        dto.setOnisepId(123);
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        dto.setOnisepId(0);
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        dto.setOnisepId(null);
        dto.setParcoursupId(456);
        when(coreFormationService.findLienOnisep("PSF", "456")).thenReturn(Optional.empty());
        assertEquals(1L, transformer.recalculId(dto).getFormationId());

        LienOnisepEntity entity = new LienOnisepEntity();
        entity.setPk(new LienOnisepPK(123, "PSF", "789"));

        dto.setParcoursupId(789);
        when(coreFormationService.findLienOnisep("PSF", "789")).thenReturn(Optional.of(entity));
        assertEquals(7284277920559019884L, transformer.recalculId(dto).getFormationId());
    }

    @Test
    void toOrganismeEntity() {
        TravailOrganismeFormationDataset dataset = new TravailOrganismeFormationDataset();
        dataset.setDateDebut("2026-01-01");
        dataset.setDateFin("2026-01-02");
        dataset.setDateDerniereDeclaration("2026-01-03");
        assertNotNull(transformer.toOrganismeEntity(dataset));

        dataset.setNumeroDeclarationActivite("123456");

        var entity = new OrganismeEntity();

        when(coreEtablissementService.findOrganisme("123456")).thenReturn(Optional.of(entity));
        assertNotNull(transformer.toOrganismeEntity(dataset));

    }

    @Test
    void toActionFormationEntity() {

        when(coreFormationService.findActionFormation(456L)).thenReturn(Optional.empty());
        when(coreFormationService.findActionFormation(789L)).thenReturn(Optional.of(new ActionFormationEntity()));
        when(coreEtablissementService.isEtablissementExiste(uaiExiste)).thenReturn(true);
        when(coreEtablissementService.isEtablissementExiste(uaiAbsent)).thenReturn(false);
        when(coreEtablissementService.getEtablissementReferenceByUai(uaiExiste)).thenReturn(new EtablissementEntity());

        ActionFormationDTO dto = new ActionFormationDTO();
        assertNotNull(transformer.toActionFormationEntity(dto, "test"));

        dto.setActionFormationId(456L);
        dto.setUai("");
        assertNotNull(transformer.toActionFormationEntity(dto, "test"));

        dto.setUai(uaiExiste);
        assertNotNull(transformer.toActionFormationEntity(dto, "test"));

        dto.setUai(uaiAbsent);
        assertNotNull(transformer.toActionFormationEntity(dto, "test"));

        dto.setActionFormationId(789L);
        assertNotNull(transformer.toActionFormationEntity(dto, "test"));
    }
}