package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSGlobalDataset;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
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
    void toSpecialiteEntityUaiNotFound() {
        // given
        SpecialitePremiereDataset dataset = new SpecialitePremiereDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<SpecialiteEntity> result = service.toSpecialiteEntity(dataset);
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
        List<SpecialiteEntity> result = service.toSpecialiteEntity(dataset);
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

    @Test
    void toOptionEtablissementEntityEtablissementUaiNotFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<OptionEtablissementEntity> result = service.toOptionEtablissementEntity(dataset);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void toOptionEtablissementEntityEtablissementUaiFound() {
        // given
        EnEtablissementDataset dataset = new EnEtablissementDataset();
        dataset.setUai(uaiExiste);
        dataset.setHebergement("1");
        // when
        List<OptionEtablissementEntity> result = service.toOptionEtablissementEntity(dataset);
        // then
        assertFalse(result.isEmpty());
    }

    @Test
    void toSectionSportiveEntityUaiNotFound() {
        // given
        SectionSportiveDataset dataset = new SectionSportiveDataset();
        dataset.setUai(uaiAbsent);
        // when
        List<SectionSportiveEntity> result = service.toSectionSportiveEntity(dataset);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void toSectionSportiveEntityUaiFound() {
        // given
        SectionSportiveDataset dataset = new SectionSportiveDataset();
        dataset.setUai(uaiExiste);
        dataset.setSections("VTT");
        // when
        List<SectionSportiveEntity> result = service.toSectionSportiveEntity(dataset);
        // then
        assertFalse(result.isEmpty());
    }

    @Test
    void toLangueEntityUaiNotFound() {
        // given
        LangueDataset dataset = new LangueDataset();
        dataset.setUai(uaiAbsent);
        // when
        LangueEntity result = service.toLangueEntity(dataset);
        // then
        assertNull(result);
    }

    @Test
    void toLangueEntityUaiFound() {
        // given
        LangueDataset dataset = new LangueDataset();
        dataset.setUai(uaiExiste);
        dataset.setLangue("anglais");
        dataset.setEnseignement("lv1");
        // when
        LangueEntity result = service.toLangueEntity(dataset);
        // then
        assertNotNull(result);
        assertEquals(Langue.EN, result.getPk().getLangue());
        assertEquals("lv1", result.getPk().getEnseignement());
    }

    @Test
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
    }

    @Test
    void toIndicePositionSocialeEntityUaiNotFound() {
        // given
        IPSGlobalDataset dataset = new IPSGlobalDataset();
        dataset.setUai(uaiAbsent);
        // when
        IndicePositionSocialeEntity result = service.toIndicePositionSocialeEntity(dataset, "C");
        // then
        assertNull(result);
    }

    @Test
    void toIndicePositionSocialeEntityUaiFound() {
        // given
        IPSGlobalDataset dataset = new IPSGlobalDataset();
        dataset.setUai(uaiExiste);
        dataset.setIndice1("140.00");
        dataset.setEcartType1("40.00");
        dataset.setRentreeScolaire("2024");
        // when
        IndicePositionSocialeEntity result = service.toIndicePositionSocialeEntity(dataset, "C");
        // then
        assertNotNull(result);
    }
}
