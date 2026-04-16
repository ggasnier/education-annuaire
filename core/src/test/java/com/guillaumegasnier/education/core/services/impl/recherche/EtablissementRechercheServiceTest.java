package com.guillaumegasnier.education.core.services.impl.recherche;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.recherche.RechercheEtablissementDTO;
import com.guillaumegasnier.education.core.repositories.RechercheEtablissementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtablissementRechercheServiceTest {

    @Mock
    private RechercheEtablissementRepository repository;

    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @Mock
    private IndexOperations indexOperations;

    private EtablissementRechercheService service;

    @BeforeEach
    void setUp() {
        service = new EtablissementRechercheService(repository, elasticsearchOperations);
    }

    // -------------------------------------------------------------------------
    // saveEtablissements
    // -------------------------------------------------------------------------

    @Test
    void saveEtablissements_callsSaveAll() {
        RechercheEtablissementEntity e = mock(RechercheEtablissementEntity.class);
        List<RechercheEtablissementEntity> list = List.of(e);
        service.saveEtablissements(list);
        verify(repository).saveAll(list);
    }

    @Test
    void saveEtablissements_emptyList_doesNothing() {
        service.saveEtablissements(List.of());
        verifyNoInteractions(repository);
    }

    @Test
    void saveEtablissements_moreThanBatchSize_callsSaveAllMultipleTimes() {
        // BATCH_SIZE = 1000 → 1001 éléments = 2 appels à saveAll
        List<RechercheEtablissementEntity> list = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            list.add(mock(RechercheEtablissementEntity.class));
        }
        service.saveEtablissements(list);
        verify(repository, times(2)).saveAll(any());
    }

    @Test
    void saveEtablissements_exactlyBatchSize_callsSaveAllOnce() {
        List<RechercheEtablissementEntity> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(mock(RechercheEtablissementEntity.class));
        }
        service.saveEtablissements(list);
        verify(repository, times(1)).saveAll(any());
    }

    // -------------------------------------------------------------------------
    // deleteEtablissements
    // -------------------------------------------------------------------------

    @Test
    void deleteEtablissements_callsDeleteAll() {
        RechercheEtablissementEntity e = mock(RechercheEtablissementEntity.class);
        List<RechercheEtablissementEntity> list = List.of(e);
        service.deleteEtablissements(list);
        verify(repository).deleteAll(list);
    }

    @Test
    void deleteEtablissements_emptyList_callsDeleteAllWithEmptyList() {
        service.deleteEtablissements(List.of());
        verify(repository).deleteAll(List.of());
    }

    // -------------------------------------------------------------------------
    // recreateIndex
    // -------------------------------------------------------------------------

    @Test
    void recreateIndex_whenIndexExists_deletesAndCreates() {
        when(elasticsearchOperations.indexOps(RechercheEtablissementEntity.class)).thenReturn(indexOperations);
        when(indexOperations.exists()).thenReturn(true);

        service.recreateIndex();

        verify(indexOperations).delete();
        verify(indexOperations).createWithMapping();
    }

    @Test
    void recreateIndex_whenIndexDoesNotExist_onlyCreates() {
        when(elasticsearchOperations.indexOps(RechercheEtablissementEntity.class)).thenReturn(indexOperations);
        when(indexOperations.exists()).thenReturn(false);

        service.recreateIndex();

        verify(indexOperations, never()).delete();
        verify(indexOperations).createWithMapping();
    }

    // -------------------------------------------------------------------------
    // rechercheEtablissements
    // -------------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    void rechercheEtablissements_emptyCriteria_returnsEmptyDTO() {
        SearchHits<RechercheEtablissementEntity> searchHits = mock(SearchHits.class);
        when(searchHits.getTotalHits()).thenReturn(0L);
        when(searchHits.getSearchHits()).thenReturn(List.of());
        when(searchHits.getAggregations()).thenReturn(null);
        when(elasticsearchOperations.search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class)))
                .thenReturn(searchHits);

        RechercheCriteria criteria = new RechercheCriteria();
        RechercheEtablissementDTO result = service.rechercheEtablissements(criteria);

        assertNotNull(result);
        assertEquals(0L, result.getTotal());
        assertTrue(result.getResultats().isEmpty());
        assertTrue(result.getFacettes().isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    void rechercheEtablissements_withQuery_setsQOnDTO() {
        SearchHits<RechercheEtablissementEntity> searchHits = mock(SearchHits.class);
        when(searchHits.getTotalHits()).thenReturn(5L);
        when(searchHits.getSearchHits()).thenReturn(List.of());
        when(searchHits.getAggregations()).thenReturn(null);
        when(elasticsearchOperations.search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class)))
                .thenReturn(searchHits);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("q", "lycée");
        RechercheCriteria criteria = new RechercheCriteria(params);

        RechercheEtablissementDTO result = service.rechercheEtablissements(criteria);

        assertNotNull(result);
        assertEquals("lycée", result.getQ());
        assertEquals(5L, result.getTotal());
    }

    @Test
    @SuppressWarnings("unchecked")
    void rechercheEtablissements_negativePage_normalizesToZero() {
        SearchHits<RechercheEtablissementEntity> searchHits = mock(SearchHits.class);
        when(searchHits.getTotalHits()).thenReturn(0L);
        when(searchHits.getSearchHits()).thenReturn(List.of());
        when(searchHits.getAggregations()).thenReturn(null);
        when(elasticsearchOperations.search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class)))
                .thenReturn(searchHits);

        RechercheCriteria criteria = new RechercheCriteria();
        criteria.setPage(-5);

        RechercheEtablissementDTO result = service.rechercheEtablissements(criteria);

        assertEquals(0, result.getPage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void rechercheEtablissements_withFiltre_delegatesToElasticsearch() {
        SearchHits<RechercheEtablissementEntity> searchHits = mock(SearchHits.class);
        when(searchHits.getTotalHits()).thenReturn(3L);
        when(searchHits.getSearchHits()).thenReturn(List.of());
        when(searchHits.getAggregations()).thenReturn(null);
        when(elasticsearchOperations.search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class)))
                .thenReturn(searchHits);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("codeSecteur", "PU");
        RechercheCriteria criteria = new RechercheCriteria(params);

        RechercheEtablissementDTO result = service.rechercheEtablissements(criteria);

        verify(elasticsearchOperations).search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class));
        assertEquals(3L, result.getTotal());
    }

    @Test
    @SuppressWarnings("unchecked")
    void rechercheEtablissements_withNestedFiltre_codeOption_delegatesToElasticsearch() {
        SearchHits<RechercheEtablissementEntity> searchHits = mock(SearchHits.class);
        when(searchHits.getTotalHits()).thenReturn(2L);
        when(searchHits.getSearchHits()).thenReturn(List.of());
        when(searchHits.getAggregations()).thenReturn(null);
        when(elasticsearchOperations.search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class)))
                .thenReturn(searchHits);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("codeOption", "RESTAURATION");
        RechercheCriteria criteria = new RechercheCriteria(params);

        RechercheEtablissementDTO result = service.rechercheEtablissements(criteria);

        verify(elasticsearchOperations).search(any(NativeQuery.class), eq(RechercheEtablissementEntity.class));
        assertEquals(2L, result.getTotal());
    }
}

