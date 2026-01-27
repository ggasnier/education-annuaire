package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.shell.datasets.etablissements.EnEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.JPODataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.metadatas.EffectifsLyceeDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.metadatas.IPSLycee2023Dataset;
import com.guillaumegasnier.education.shell.dto.etablissements.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class EtablissementTransformerTest {

    @Mock
    CoreEtablissementService coreEtablissementService;

    @Mock
    CoreTerritoireService coreTerritoireService;

    EtablissementMapper etablissementMapper;

    EtablissementTransformerImpl transformer;

    String uaiExiste = "0750683K";
    String uaiAbsent = "0750652B";

    EtablissementEntity entityExiste;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        etablissementMapper = Mappers.getMapper(EtablissementMapper.class);
        transformer = new EtablissementTransformerImpl(coreEtablissementService, coreTerritoireService, etablissementMapper);

        entityExiste = new EtablissementEntity();
        entityExiste.setUai(uaiExiste);

        when(coreEtablissementService.isEtablissementExiste(uaiExiste)).thenReturn(true);
        when(coreEtablissementService.isEtablissementExiste(uaiAbsent)).thenReturn(false);

        when(coreEtablissementService.findEtablissement(uaiExiste)).thenReturn(Optional.of(entityExiste));
        when(coreEtablissementService.findEtablissement(uaiAbsent)).thenReturn(Optional.empty());

        when(coreEtablissementService.findNature("123")).thenReturn(Optional.empty());
        when(coreEtablissementService.findContrat("21")).thenReturn(Optional.empty());

        when(coreTerritoireService.findCommune("75105")).thenReturn(Optional.of(new CommuneEntity()));
    }

    @Test
    void toEtablissementMetadataEntityIndicePositionSocialeTest() {

        IPSLycee2023Dataset dataset = new IPSLycee2023Dataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiAbsent);
        assertNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new IPSLycee2023Dataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.of(new EtablissementMetadataEntity()));
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new IPSLycee2023Dataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.empty());
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));

    }

    @Test
    void toEtablissementMetadataEntityEffectifsTest() {
        EffectifsLyceeDataset dataset = new EffectifsLyceeDataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiAbsent);
        assertNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new EffectifsLyceeDataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        dataset.setEffectifs(12345);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.of(new EtablissementMetadataEntity()));
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new EffectifsLyceeDataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        dataset.setEffectifs(12345);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.empty());
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new EffectifsLyceeDataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.of(new EtablissementMetadataEntity()));
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));

        dataset = new EffectifsLyceeDataset();
        dataset.setRentreeScolaire("2024-2025");
        dataset.setUai(uaiExiste);
        when(coreEtablissementService.findMetadata(uaiExiste, 2024)).thenReturn(Optional.empty());
        assertNotNull(transformer.toEtablissementMetadataEntity(dataset));
    }

    @Test
    void toEtablissementMetadataEntityIndicateurValeurAjouteeTest() {

    }

    @Test
    void toEtablissementEntityTest() {
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);
        assertNotNull(transformer.toEtablissementEntity(dataset, "en"));
    }

    @Test
    void toEtablissementEntityOldTest() {
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);
        dataset.setCodeNature("123");
        dataset.setCodeContrat("21");
        dataset.setDateOuverture("2026-01-01");
        assertNotNull(transformer.toEtablissementEntityOld(entityExiste, dataset, "en"));
    }

    @Test
    void toEtablissementEntityNewTest() {
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiAbsent);
        dataset.setCodeCommune("75105");
        dataset.setCodeNature("123");
        dataset.setCodeContrat("21");
        assertNotNull(transformer.toEtablissementEntityNew(dataset, "en"));
    }

    @Test
    void toEtablissementJPOEntityTest() {
        when(coreEtablissementService.findJPO(uaiAbsent, LocalDate.parse("2026-01-01"), LocalDate.parse("2026-01-01"))).thenReturn(Optional.of(new EtablissementJPOEntity()));
        JPODataset dataset = new JPODataset();
        dataset.setUai(uaiAbsent);
        dataset.setDateDebut(LocalDate.parse("2026-01-01"));
        dataset.setDateFin(LocalDate.parse("2026-01-01"));
        assertNotNull(transformer.toEtablissementJPOEntity(dataset, "en"));

        when(coreEtablissementService.findJPO(uaiExiste, LocalDate.parse("2026-01-01"), LocalDate.parse("2026-01-01"))).thenReturn(Optional.empty());
        dataset = new JPODataset();
        dataset.setUai(uaiExiste);
        dataset.setDateDebut(LocalDate.parse("2026-01-01"));
        dataset.setDateFin(LocalDate.parse("2026-01-01"));
        assertNotNull(transformer.toEtablissementJPOEntity(dataset, "en"));

        when(coreEtablissementService.findJPO(uaiAbsent, LocalDate.parse("2026-01-01"), LocalDate.parse("2026-01-01"))).thenReturn(Optional.empty());
        dataset = new JPODataset();
        dataset.setUai(uaiAbsent);
        dataset.setDateDebut(LocalDate.parse("2026-01-01"));
        dataset.setDateFin(LocalDate.parse("2026-01-01"));
        assertNull(transformer.toEtablissementJPOEntity(dataset, "en"));
    }

    @Test
    void toEtablissementContactEntityTest() {

    }

    @Test
    void toEtablissementLangueEntityTest() {
        when(coreEtablissementService.findLangue(uaiAbsent, Langue.CO, Langue.Categorie.LV, "")).thenReturn(Optional.of(new EtablissementLangueEntity()));
        LangueDTO dto = new LangueDTO(uaiAbsent, Langue.CO, Langue.Categorie.LV, "");
        assertNotNull(transformer.toEtablissementLangueEntity(dto, "en"));

        when(coreEtablissementService.findLangue(uaiExiste, Langue.CO, Langue.Categorie.LV, "")).thenReturn(Optional.empty());
        dto = new LangueDTO(uaiExiste, Langue.CO, Langue.Categorie.LV, "");
        assertNotNull(transformer.toEtablissementLangueEntity(dto, "en"));

        when(coreEtablissementService.findLangue(uaiAbsent, Langue.CO, Langue.Categorie.LV, "")).thenReturn(Optional.empty());
        dto = new LangueDTO(uaiAbsent, Langue.CO, Langue.Categorie.LV, "");
        assertNull(transformer.toEtablissementLangueEntity(dto, "en"));
    }

    @Test
    void toEtablissementOptionEntityTest() {
        when(coreEtablissementService.findOption(uaiAbsent, OptionEtablissement.BFI)).thenReturn(Optional.of(new EtablissementOptionEntity()));
        OptionDTO dto = new OptionDTO(uaiAbsent, OptionEtablissement.BFI);
        assertNotNull(transformer.toEtablissementOptionEntity(dto, "en"));

        when(coreEtablissementService.findOption(uaiExiste, OptionEtablissement.BFI)).thenReturn(Optional.empty());
        dto = new OptionDTO(uaiExiste, OptionEtablissement.BFI);
        assertNotNull(transformer.toEtablissementOptionEntity(dto, "en"));

        when(coreEtablissementService.findOption(uaiAbsent, OptionEtablissement.BFI)).thenReturn(Optional.empty());
        dto = new OptionDTO(uaiAbsent, OptionEtablissement.BFI);
        assertNull(transformer.toEtablissementOptionEntity(dto, "en"));
    }

    @Test
    void toEtablissementSpecialiteEntityTest() {
        when(coreEtablissementService.findSpecialite(uaiAbsent, SpecialiteBac.CINEMA)).thenReturn(Optional.of(new EtablissementSpecialiteEntity()));
        SpecialiteDTO dto = new SpecialiteDTO(uaiAbsent, SpecialiteBac.CINEMA);
        assertNotNull(transformer.toEtablissementSpecialiteEntity(dto, "en"));

        when(coreEtablissementService.findSpecialite(uaiExiste, SpecialiteBac.CINEMA)).thenReturn(Optional.empty());
        dto = new SpecialiteDTO(uaiExiste, SpecialiteBac.CINEMA);
        assertNotNull(transformer.toEtablissementSpecialiteEntity(dto, "en"));

        when(coreEtablissementService.findSpecialite(uaiAbsent, SpecialiteBac.CINEMA)).thenReturn(Optional.empty());
        dto = new SpecialiteDTO(uaiAbsent, SpecialiteBac.CINEMA);
        assertNull(transformer.toEtablissementSpecialiteEntity(dto, "en"));
    }

    @Test
    void toEtablissementSportEntityTest() {
        when(coreEtablissementService.findSport(uaiAbsent, Sport.ESCRIME, Sport.Categorie.SE)).thenReturn(Optional.of(new EtablissementSportEntity()));
        SportDTO dto = new SportDTO(uaiAbsent, Sport.ESCRIME, Sport.Categorie.SE);
        assertNotNull(transformer.toEtablissementSportEntity(dto, "en"));

        when(coreEtablissementService.findSport(uaiExiste, Sport.ESCRIME, Sport.Categorie.SE)).thenReturn(Optional.empty());
        dto = new SportDTO(uaiExiste, Sport.ESCRIME, Sport.Categorie.SE);
        assertNotNull(transformer.toEtablissementSportEntity(dto, "en"));

        when(coreEtablissementService.findSport(uaiAbsent, Sport.ESCRIME, Sport.Categorie.SE)).thenReturn(Optional.empty());
        dto = new SportDTO(uaiAbsent, Sport.ESCRIME, Sport.Categorie.SE);
        assertNull(transformer.toEtablissementSportEntity(dto, "en"));
    }

    @Test
    void toEtablissementMasaEntityTest() {
        when(coreEtablissementService.findMasa("123456")).thenReturn(Optional.of(new EtablissementMasaEntity()));
        MasaDTO dto = new MasaDTO(uaiAbsent, "123456");
        assertNotNull(transformer.toEtablissementMasaEntity(dto));

        when(coreEtablissementService.findMasa("123456")).thenReturn(Optional.empty());
        dto = new MasaDTO(uaiExiste, "123456");
        assertNotNull(transformer.toEtablissementMasaEntity(dto));

        when(coreEtablissementService.findMasa("123456")).thenReturn(Optional.empty());
        dto = new MasaDTO(uaiAbsent, "123456");
        assertNull(transformer.toEtablissementMasaEntity(dto));
    }
}