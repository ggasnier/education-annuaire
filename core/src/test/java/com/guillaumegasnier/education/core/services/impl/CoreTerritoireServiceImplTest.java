package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.territoires.*;
import com.guillaumegasnier.education.core.repositories.territoires.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoreTerritoireServiceImplTest {

    @Mock
    private PaysRepository paysRepository;
    @Mock
    private AcademieRepository academieRepository;
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private DepartementRepository departementRepository;
    @Mock
    private CommuneRepository communeRepository;

    private CoreTerritoireServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CoreTerritoireServiceImpl(
                paysRepository,
                academieRepository,
                regionRepository,
                departementRepository,
                communeRepository
        );
    }

    @Test
    void savePaysTest() {
        PaysEntity p = mock(PaysEntity.class);
        List<PaysEntity> list = List.of(p);
        service.savePays(list);
        verify(paysRepository).saveAll(list);
    }

    @Test
    void saveAcademiesTest() {
        AcademieEntity e = mock(AcademieEntity.class);
        List<AcademieEntity> list = List.of(e);
        service.saveAcademies(list);
        verify(academieRepository).saveAll(list);
    }

    @Test
    void saveRegionsTest() {
        RegionEntity e = mock(RegionEntity.class);
        List<RegionEntity> list = List.of(e);
        service.saveRegions(list);
        verify(regionRepository).saveAll(list);
    }

    @Test
    void saveDepartementsTest() {
        DepartementEntity e = mock(DepartementEntity.class);
        List<DepartementEntity> list = List.of(e);
        service.saveDepartements(list);
        verify(departementRepository).saveAll(list);
    }

    @Test
    void saveCommunesTest() {
        CommuneEntity e = mock(CommuneEntity.class);
        List<CommuneEntity> list = List.of(e);
        service.saveCommunes(list);
        verify(communeRepository).saveAll(list);
    }

    @Test
    void getRegion_returnsEntity() {
        RegionEntity r = mock(RegionEntity.class);
        when(regionRepository.findById("R1")).thenReturn(Optional.of(r));
        assertSame(r, service.getRegion("R1"));
    }

    @Test
    void getAcademie_returnsEntity() {
        AcademieEntity a = mock(AcademieEntity.class);
        when(academieRepository.findById("A1")).thenReturn(Optional.of(a));
        assertSame(a, service.getAcademie("A1"));
    }

    @Test
    void getDepartement_returnsEntity() {
        DepartementEntity d = mock(DepartementEntity.class);
        when(departementRepository.findById("D1")).thenReturn(Optional.of(d));
        assertSame(d, service.getDepartement("D1"));
    }

    @Test
    void findCommuneByNom_blank_returnsEmpty() {
        Optional<CommuneEntity> res = service.findCommuneByNom("   ");
        assertTrue(res.isEmpty());
        verifyNoInteractions(communeRepository);
    }

    @Test
    void findCommuneByNom_null_delegatesToRepository() {
        CommuneEntity c = mock(CommuneEntity.class);
        when(communeRepository.findByNomIgnoreCase(null)).thenReturn(Optional.of(c));
        Optional<CommuneEntity> res = service.findCommuneByNom(null);
        assertTrue(res.isPresent());
        assertSame(c, res.get());
    }

    @Test
    void findCommuneByNom_repositoryThrows_returnsEmpty() {
        when(communeRepository.findByNomIgnoreCase("Dup"))
                .thenThrow(new IncorrectResultSizeDataAccessException(2));
        Optional<CommuneEntity> res = service.findCommuneByNom("Dup");
        assertTrue(res.isEmpty());
    }

    @Test
    void findCommuneByNomAndCodeRegion_returnsEmpty() {
        Optional<CommuneEntity> res = service.findCommuneByNomAndCodeRegion("Paris", "R1");
        assertTrue(res.isEmpty());
    }

    @Test
    void findDepartement_delegatesToRepository() {
        DepartementEntity d = mock(DepartementEntity.class);
        when(departementRepository.findById("D2")).thenReturn(Optional.of(d));
        Optional<DepartementEntity> res = service.findDepartement("D2");
        assertTrue(res.isPresent());
        assertSame(d, res.get());
    }

    @Test
    void getPays_usesGetReferenceById() {
        PaysEntity p = mock(PaysEntity.class);
        when(paysRepository.getReferenceById("FR")).thenReturn(p);
        assertSame(p, service.getPays("FR"));
    }

    @Test
    void getDepartements_delegatesToRepository() {
        DepartementEntity d = mock(DepartementEntity.class);
        List<DepartementEntity> list = List.of(d);
        when(departementRepository.findAllByOrderByAcademieNomAscNomAsc()).thenReturn(list);
        assertSame(list, service.getDepartements());
    }

    @Test
    void findCommune_byCode_delegatesToRepository() {
        CommuneEntity c = mock(CommuneEntity.class);
        when(communeRepository.findById("12345")).thenReturn(Optional.of(c));
        Optional<CommuneEntity> res = service.findCommune("12345");
        assertTrue(res.isPresent());
        assertSame(c, res.get());
    }
}
