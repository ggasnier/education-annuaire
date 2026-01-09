package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementLangueEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSpecialiteEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
class ShellEntityServiceImplTest {

    @Mock
    private CoreReferenceService coreReferenceService;
    @Mock
    private CoreEtablissementService coreEtablissementService;
    @Mock
    private EtablissementMapper etablissementMapper;

    @InjectMocks
    private ShellEntityServiceImpl service;

    private String uaiExiste = "0750683K";
    private String uaiAbsent = "0750652B";

    private EtablissementEntity etablissementEntityExiste;
    private EtablissementEntity etablissementEntityAbsent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        etablissementEntityExiste = new EtablissementEntity();
        etablissementEntityExiste.setUai(uaiExiste);

        etablissementEntityAbsent = new EtablissementEntity();
        etablissementEntityAbsent.setUai(uaiAbsent);

        when(coreEtablissementService.findEtablissement(uaiExiste)).thenReturn(Optional.of(etablissementEntityExiste));
        when(coreEtablissementService.findEtablissement(uaiAbsent)).thenReturn(Optional.empty());
        when(coreEtablissementService.isEtablissementExiste(uaiExiste)).thenReturn(true);
        when(coreEtablissementService.isEtablissementExiste(uaiAbsent)).thenReturn(false);
    }

    @Test
    void toEtablissementEntityUaiNotFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiAbsent);

        when(etablissementMapper.toEntity(dataset)).thenReturn(etablissementEntityAbsent);

        EtablissementEntity entity = service.toEtablissementEntity(dataset, "test");

        assertNotNull(entity);
    }

    @Test
    void toEtablissementEntityUaiFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);

        EtablissementEntity entity = service.toEtablissementEntity(dataset, "test");

        assertNotNull(entity);
    }

    @Test
    void toLangueEntityTest() {
        OnisepDispositifDataset dataset = new OnisepDispositifDataset();
        dataset.setUai(uaiExiste);
        dataset.setNom("section européenne de lycée général et technologique");
        dataset.setEnseignement("anglais");

        List<EtablissementLangueEntity> entity = service.toLangueEntity(dataset, Langue.Categorie.LO, "onisep");
        assertEquals(1, entity.size());
        assertNotNull(entity.getFirst().getSources());
        log.info(entity.getFirst().getSources().toString());
    }

    /*@Test
    void toSectionInternationaleEntityUaiNotFound() {
        // given
        SectionInternationaleDataset dataset = new SectionInternationaleDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<SectionInternationaleEntity> result = service.toSectionInternationaleEntity(dataset);
        // then
        assertTrue(result.isEmpty());
    }*/

    /*@Test
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
    }*/

    @Test
    void toSpecialiteEntityUaiNotFound() {
        // given
        SpecialitePremiereDataset dataset = new SpecialitePremiereDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<EtablissementSpecialiteEntity> result = service.toSpecialiteEntity(dataset, "en");
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void toSpecialiteEntityUaiFound() {
        // given
        SpecialitePremiereDataset dataset = new SpecialitePremiereDataset();
        dataset.setUai(uaiExiste);
        dataset.setEnseignements("Physique-chimie");
        // when
        List<EtablissementSpecialiteEntity> result = service.toSpecialiteEntity(dataset, "en");
        // then
        assertFalse(result.isEmpty());
    }

    @Test
    void toEtablissementOptionEntitySectionBinationaleUaiNotFound() {
        // given
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        dataset.setUai(uaiAbsent);
        // when
        EtablissementOptionEntity result = service.toEtablissementOptionEntity(dataset, "en");
        // then
        assertNull(result);
    }

    @Test
    void toEtablissementOptionEntitySectionBinationaleUaiFound() {
        // given
        SectionBinationaleDataset dataset = new SectionBinationaleDataset();
        dataset.setUai(uaiExiste);
        dataset.setTypeBac("Abibac");
        // when
        EtablissementOptionEntity result = service.toEtablissementOptionEntity(dataset, "en");
        // then
        assertNotNull(result);
        assertEquals(OptionEtablissement.ABIBAC, result.getPk().getOption());
    }

    @Test
    void toEtablissementOptionEntityEtablissementUaiNotFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<EtablissementOptionEntity> result = service.toEtablissementOptionEntity(dataset, "en");
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void toEtablissementOptionEntityEtablissementUaiFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);
        dataset.setHebergement("1");
        // when
        List<EtablissementOptionEntity> result = service.toEtablissementOptionEntity(dataset, "en");
        // then
        assertFalse(result.isEmpty());
    }

    /*@Test
    void toSectionSportiveEntityUaiNotFound() {
        // given
        SectionSportiveDataset dataset = new SectionSportiveDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<SectionSportiveEntity> result = service.toSectionSportiveEntity(dataset);
        // then
        assertTrue(result.isEmpty());
    }*/

    /*@Test
    void toSectionSportiveEntityUaiFound() {
        // given
        SectionSportiveDataset dataset = new SectionSportiveDataset();
        dataset.setUai(uaiExiste);
        dataset.setSections("VTT");
        // when
        List<SectionSportiveEntity> result = service.toSectionSportiveEntity(dataset);
        // then
        assertFalse(result.isEmpty());
    }*/

    /*@Test
    void toLangueEntityUaiNotFound() {
        // given
        LangueDataset dataset = new LangueDataset();
        dataset.setUai(uaiAbsent);
        // when
        LangueEntity result = service.toLangueEntity(dataset);
        // then
        assertNull(result);
    }*/

    @Test
    void toLangueEntityUaiFound() {
        // given
        LangueDataset dataset = new LangueDataset();
        dataset.setUai(uaiExiste);
        dataset.setLangue("anglais");
        dataset.setEnseignement("lv1");
        // when
        EtablissementLangueEntity result = service.toLangueEntity(dataset, "en");
        // then
        assertNotNull(result);
        assertEquals(Langue.EN, result.getPk().getLangue());
        assertEquals("lv1", result.getPk().getEnseignement());
    }

    /*@Test
    void toSportEtudeEntityUaiNotFound() {
        // given
        SectionSportEtudeDataset dataset = new SectionSportEtudeDataset();
        dataset.setUai(uaiAbsent);
        // when
        SportEtudeEntity result = service.toSportEtudeEntity(dataset);
        // then
        assertNull(result);
    }

    @Test
    void toSportEtudeEntityUaiFound() {
        // given
        SectionSportEtudeDataset dataset = new SectionSportEtudeDataset();
        dataset.setUai(uaiExiste);
        dataset.setNomSport("VTT");
        // when
        SportEtudeEntity result = service.toSportEtudeEntity(dataset);
        // then
        assertNotNull(result);
    }

    @Test
    void toSportEtudeEntityUaiFoundSportNotFound() {
        // given
        SectionSportEtudeDataset dataset = new SectionSportEtudeDataset();
        dataset.setUai(uaiExiste);
        dataset.setNomSport("AQUA PONEY");
        // when
        SportEtudeEntity result = service.toSportEtudeEntity(dataset);
        // then
        assertNull(result);
    }*/

}
