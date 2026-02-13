package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.repositories.etablissements.*;
import com.guillaumegasnier.education.core.repositories.formations.OrganismeRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoreEtablissementServiceImplTest {

    @Mock
    private EtablissementRepository etablissementRepository;
    @Mock
    private NatureRepository natureRepository;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private SpecialiteRepository specialiteRepository;
    @Mock
    private EtablissementLangueRepository langueRepository;
    @Mock
    private EtablissementOptionRepository optionEtablissementRepository;
    @Mock
    private OrganismeRepository organismeRepository;
    @Mock
    private EtablissementSportRepository etablissementSportRepository;
    @Mock
    private EtablissementMetadataRepository etablissementMetadataRepository;
    @Mock
    private EtablissementContactRepository etablissementContactRepository;
    @Mock
    private EtablissementJPORepository etablissementJPORepository;
    @Mock
    private EtablissementIdentifiantRepository etablissementIdentifiantRepository;
    @Mock
    private EtablissementMasaRepository etablissementMasaRepository;
    @Mock
    private EntityManager em;

    private CoreEtablissementServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        service = new CoreEtablissementServiceImpl(
                etablissementRepository,
                natureRepository,
                contratRepository,
                specialiteRepository,
                langueRepository,
                optionEtablissementRepository,
                organismeRepository,
                etablissementSportRepository,
                etablissementMetadataRepository,
                etablissementContactRepository,
                etablissementJPORepository,
                etablissementIdentifiantRepository,
                etablissementMasaRepository
        );

        Field emField = CoreEtablissementServiceImpl.class.getDeclaredField("em");
        emField.setAccessible(true);
        emField.set(service, em);
    }

    @Test
    void saveEtablissements_callsSaveAllAndFlushClear() {
        EtablissementEntity e = mock(EtablissementEntity.class);
        List<EtablissementEntity> list = List.of(e);
        service.saveEtablissements(list);
        verify(etablissementRepository).saveAll(list);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void saveOrganismes_callsSaveAllAndFlushClear() {
        OrganismeEntity o = mock(OrganismeEntity.class);
        List<OrganismeEntity> list = List.of(o);
        service.saveOrganismes(list);
        verify(organismeRepository).saveAll(list);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void findEtablissement_delegatesToRepository() {
        EtablissementEntity e = mock(EtablissementEntity.class);
        when(etablissementRepository.findByUai("U1")).thenReturn(Optional.of(e));
        Optional<EtablissementEntity> res = service.findEtablissement("U1");
        assertTrue(res.isPresent());
        assertSame(e, res.get());
    }

    @Test
    void isEtablissementExiste_delegatesToRepository() {
        when(etablissementRepository.existsById("U2")).thenReturn(true);
        assertTrue(service.isEtablissementExiste("U2"));
    }

    @Test
    void getEtablissementReferenceByUai_callsGetReference() {
        EtablissementEntity e = mock(EtablissementEntity.class);
        when(etablissementRepository.getReferenceById("U3")).thenReturn(e);
        assertSame(e, service.getEtablissementReferenceByUai("U3"));
    }

    @Test
    void getNbrEtablissements_delegatesToCount() {
        when(etablissementRepository.countByActif(true)).thenReturn(42);
        assertEquals(42, service.getNbrEtablissements());
    }

    @Test
    void findMetadata_and_getMetadataList() {
        EtablissementMetadataEntity m = mock(EtablissementMetadataEntity.class);
        when(etablissementMetadataRepository.findByPkUaiAndPkAnnee("U1", 2020)).thenReturn(Optional.of(m));
        Optional<EtablissementMetadataEntity> res = service.findMetadata("U1", 2020);
        assertTrue(res.isPresent());
        assertSame(m, res.get());

        List<EtablissementMetadataEntity> list = List.of(m);
        when(etablissementMetadataRepository.findAllByPkUaiOrderByPkAnneeDesc("U1")).thenReturn(list);
        assertSame(list, service.getMetadataListByUai("U1"));
    }

    @Test
    void saveMetadata_callsSaveAllAndFlushClear() {
        EtablissementMetadataEntity m = mock(EtablissementMetadataEntity.class);
        service.saveMetadata(List.of(m));
        verify(etablissementMetadataRepository).saveAll(anyList());
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void findIdentifiant_whenNotFound_savesNew() {
        EtablissementEntity entity = mock(EtablissementEntity.class);
        when(entity.getUai()).thenReturn("UAI1");
        when(etablissementIdentifiantRepository.findByPkUaiAndPkClefAndPkValeur("UAI1", "k", "v"))
                .thenReturn(Optional.empty());
        EtablissementIdentifiantEntity saved = mock(EtablissementIdentifiantEntity.class);
        when(etablissementIdentifiantRepository.save(any(EtablissementIdentifiantEntity.class))).thenReturn(saved);

        EtablissementIdentifiantEntity res = service.findIdentifiant(entity, "k", "v");
        assertSame(saved, res);
        verify(etablissementIdentifiantRepository).save(any(EtablissementIdentifiantEntity.class));
    }

    @Test
    void findIdentifiant_whenFound_returnsExisting() {
        EtablissementEntity entity = mock(EtablissementEntity.class);
        when(entity.getUai()).thenReturn("UAI2");
        EtablissementIdentifiantEntity existing = mock(EtablissementIdentifiantEntity.class);
        when(etablissementIdentifiantRepository.findByPkUaiAndPkClefAndPkValeur("UAI2", "k", "v"))
                .thenReturn(Optional.of(existing));
        EtablissementIdentifiantEntity res = service.findIdentifiant(entity, "k", "v");
        assertSame(existing, res);
        verify(etablissementIdentifiantRepository, never()).save(any());
    }

    @Test
    void findOption_and_findSport_and_findMasa_delegate() {
        EtablissementOptionEntity opt = mock(EtablissementOptionEntity.class);
        when(optionEtablissementRepository.findByPkUaiAndPkOption("U1", OptionEtablissement.BFI))
                .thenReturn(Optional.of(opt));
        assertTrue(service.findOption("U1", OptionEtablissement.BFI).isPresent());

        EtablissementSportEntity sp = mock(EtablissementSportEntity.class);
        when(etablissementSportRepository.findByPkUaiAndPkSportAndPkCategorie("U1", Sport.FOOTBALL, Sport.Categorie.SE))
                .thenReturn(Optional.of(sp));
        assertTrue(service.findSport("U1", Sport.FOOTBALL, Sport.Categorie.SE).isPresent());

        EtablissementMasaEntity masa = mock(EtablissementMasaEntity.class);
        when(etablissementMasaRepository.findById("M1")).thenReturn(Optional.of(masa));
        assertTrue(service.findMasa("M1").isPresent());
    }
}
