package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import com.guillaumegasnier.education.core.repositories.formations.ActionFormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.FormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.LienOnisepRepository;
import com.guillaumegasnier.education.core.repositories.formations.OrganismeRepository;
import com.guillaumegasnier.education.core.repositories.referentiels.RomeRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoreFormationServiceImplTest {

    @Mock
    private FormationRepository formationRepository;
    @Mock
    private ActionFormationRepository actionFormationRepository;
    @Mock
    private RomeRepository romeRepository;
    @Mock
    private OrganismeRepository organismeRepository;
    @Mock
    private LienOnisepRepository lienOnisepRepository;
    @Mock
    private EntityManager em;

    private CoreFormationServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        service = new CoreFormationServiceImpl(formationRepository, actionFormationRepository, romeRepository, organismeRepository, lienOnisepRepository);
        Field emField = CoreFormationServiceImpl.class.getDeclaredField("em");
        emField.setAccessible(true);
        emField.set(service, em);
    }

    @Test
    void saveFormationsTest() {
        List<FormationEntity> entities = new ArrayList<>();
        service.saveFormations(entities);
        verify(formationRepository).saveAll(entities);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void saveActionFormationsTest() {
        List<ActionFormationEntity> entities = new ArrayList<>();
        service.saveActionFormation(entities);
        verify(actionFormationRepository).saveAll(entities);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void saveOrganismesTest() {
        List<OrganismeEntity> entities = new ArrayList<>();
        service.saveOrganismes(entities);
        verify(organismeRepository).saveAll(entities);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void saveFormationTest() {
        FormationEntity entity = new FormationEntity();
        service.saveFormation(entity);
        verify(formationRepository).save(entity);
    }

    @Test
    void saveRomesTest() {
        List<RomeEntity> entities = new ArrayList<>();
        service.saveRomes(entities);
        verify(romeRepository).saveAll(entities);
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    void findFormationTest() {
        FormationEntity entity = mock(FormationEntity.class);
        when(formationRepository.findById(1L)).thenReturn(Optional.of(entity));
        Optional<FormationEntity> result = service.findFormation(1L);
        assertTrue(result.isPresent());
        assertSame(entity, result.get());
    }

    @Test
    void findFormationByOnisepIdtest() {
        FormationEntity entity = mock(FormationEntity.class);
        when(formationRepository.findByOnisepId(123)).thenReturn(Optional.of(entity));
        Optional<FormationEntity> result = service.findFormationByOnisepId(123);
        assertTrue(result.isPresent());
        assertSame(entity, result.get());
    }

    @Test
    void findFormationByParcoursupIdtest() {
        FormationEntity entity = mock(FormationEntity.class);
        when(formationRepository.findByParcoursupId(123)).thenReturn(Optional.of(entity));
        Optional<FormationEntity> result = service.findFormationByParcoursupId(123);
        assertTrue(result.isPresent());
        assertSame(entity, result.get());
    }

    @Test
    void findActionFormationByParcoursupIdtest() {
        ActionFormationEntity entity = mock(ActionFormationEntity.class);
        when(actionFormationRepository.findByParcoursupId(123)).thenReturn(Optional.of(entity));
        Optional<ActionFormationEntity> result = service.findActionFormationByParcoursupId(123);
        assertTrue(result.isPresent());
        assertSame(entity, result.get());
    }

    @Test
    void getRomesTest() {
        Set<RomeEntity> entities = new HashSet<>();
        when(romeRepository.findAllByCodeIn(List.of("123", "456"))).thenReturn(entities);
        assertSame(entities, service.getRomes(List.of("123", "456")));
    }


}