package com.guillaumegasnier.education.core.services.impl.recherche;

import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheFacetteValeurDTO;
import com.guillaumegasnier.education.core.dto.recherche.RechercheEtablissementDTO;
import com.guillaumegasnier.education.core.enums.FacetteEtablissement;
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
import java.util.Map;

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

    // -------------------------------------------------------------------------
    // extractBuckets
    // -------------------------------------------------------------------------

    private static final JacksonJsonpMapper MAPPER = new JacksonJsonpMapper();

    /**
     * Construit un Aggregate top_hits portant un document source JSON.
     */
    private Aggregate buildTopHitsAggregate(Map<String, Object> sourceMap) {
        JsonData source = JsonData.of(sourceMap, MAPPER);
        Hit<JsonData> hit = Hit.of(h -> h.index("etablissements").id("1").source(source));
        return Aggregate.of(a -> a.topHits(
                TopHitsAggregate.of(th -> th.hits(
                        HitsMetadata.of(hm -> hm
                                .total(t -> t.value(1).relation(TotalHitsRelation.Eq))
                                .hits(List.of(hit)))))));
    }

    /**
     * Construit un Aggregate sterms avec un seul bucket string.
     */
    private Aggregate buildStermsAggregate(String key, long docCount, String codeNomAggName, Map<String, Object> sourceMap) {
        Aggregate topHitsAgg = buildTopHitsAggregate(sourceMap);
        StringTermsBucket bucket = StringTermsBucket.of(b -> b
                .key(key)
                .docCount(docCount)
                .aggregations(codeNomAggName, topHitsAgg));
        return Aggregate.of(a -> a.sterms(
                StringTermsAggregate.of(st -> st
                        .buckets(bkts -> bkts.array(List.of(bucket)))
                        .sumOtherDocCount(0L))));
    }

    /**
     * Construit un Aggregate lterms avec un seul bucket long.
     */
    private Aggregate buildLtermsAggregate(long key, long docCount, String codeNomAggName, Map<String, Object> sourceMap) {
        Aggregate topHitsAgg = buildTopHitsAggregate(sourceMap);
        LongTermsBucket bucket = LongTermsBucket.of(b -> b
                .key(key)
                .docCount(docCount)
                .aggregations(codeNomAggName, topHitsAgg));
        return Aggregate.of(a -> a.lterms(
                LongTermsAggregate.of(lt -> lt
                        .buckets(bkts -> bkts.array(List.of(bucket)))
                        .sumOtherDocCount(0L))));
    }

    @Test
    void extractBuckets_sterms_flatFacette_populatesValeurs() {
        Aggregate aggregate = buildStermsAggregate("PU", 42, "nomSecteur",
                Map.of("nomSecteur", "Public"));
        RechercheCriteria criteria = new RechercheCriteria(new LinkedMultiValueMap<>());

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.SECTEUR, criteria, valeurs);

        assertEquals(1, valeurs.size());
        RechercheFacetteValeurDTO v = valeurs.getFirst();
        assertEquals("PU", v.getCode());
        assertEquals("Public", v.getNom());
        assertEquals(42, v.getTotal());
        assertNull(v.getChecked());
    }

    @Test
    void extractBuckets_sterms_checkedWhenFilterMatches() {
        Aggregate aggregate = buildStermsAggregate("PU", 10, "nomSecteur",
                Map.of("nomSecteur", "Public"));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("codeSecteur", "PU");
        RechercheCriteria criteria = new RechercheCriteria(params);

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.SECTEUR, criteria, valeurs);

        assertEquals("checked", valeurs.getFirst().getChecked());
    }

    @Test
    void extractBuckets_sterms_notCheckedWhenFilterDoesNotMatch() {
        Aggregate aggregate = buildStermsAggregate("PU", 10, "nomSecteur",
                Map.of("nomSecteur", "Public"));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("codeSecteur", "PR");
        RechercheCriteria criteria = new RechercheCriteria(params);

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.SECTEUR, criteria, valeurs);

        assertNull(valeurs.getFirst().getChecked());
    }

    @Test
    void extractBuckets_sterms_multipleBuckets_populatesAll() {
        Aggregate topHits1 = buildTopHitsAggregate(Map.of("nomSecteur", "Public"));
        Aggregate topHits2 = buildTopHitsAggregate(Map.of("nomSecteur", "Privé"));

        StringTermsBucket b1 = StringTermsBucket.of(b -> b.key("PU").docCount(10)
                .aggregations("nomSecteur", topHits1));
        StringTermsBucket b2 = StringTermsBucket.of(b -> b.key("PR").docCount(5)
                .aggregations("nomSecteur", topHits2));

        Aggregate aggregate = Aggregate.of(a -> a.sterms(
                StringTermsAggregate.of(st -> st
                        .buckets(bkts -> bkts.array(List.of(b1, b2)))
                        .sumOtherDocCount(0L))));

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.SECTEUR, new RechercheCriteria(new LinkedMultiValueMap<>()), valeurs);

        assertEquals(2, valeurs.size());
        assertEquals("PU", valeurs.get(0).getCode());
        assertEquals("PR", valeurs.get(1).getCode());
    }

    @Test
    void extractBuckets_lterms_flatFacette_populatesValeurs() {
        Aggregate aggregate = buildLtermsAggregate(75L, 8L, "nomDepartement",
                Map.of("nomDepartement", "Paris"));
        RechercheCriteria criteria = new RechercheCriteria(new LinkedMultiValueMap<>());

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.DEPARTEMENT, criteria, valeurs);

        assertEquals(1, valeurs.size());
        assertEquals("75", valeurs.getFirst().getCode());
        assertEquals("Paris", valeurs.getFirst().getNom());
        assertEquals(8, valeurs.getFirst().getTotal());
    }

    @Test
    void extractBuckets_nested_facette_descendsAndPopulates() {
        // OPTION : nestedPath="options", code="options.codeOption", codeNom="options.nomOption"
        Aggregate topHitsAgg = buildTopHitsAggregate(Map.of("nomOption", "Restauration"));
        StringTermsBucket bucket = StringTermsBucket.of(b -> b
                .key("RESTAURATION")
                .docCount(3L)
                .aggregations("options.nomOption", topHitsAgg));
        Aggregate termAgg = Aggregate.of(a -> a.sterms(
                StringTermsAggregate.of(st -> st
                        .buckets(bkts -> bkts.array(List.of(bucket)))
                        .sumOtherDocCount(0L))));

        Aggregate nestedAggregate = Aggregate.of(a -> a.nested(
                NestedAggregate.of(n -> n
                        .docCount(3L)
                        .aggregations("options.codeOption_terms", termAgg))));

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(nestedAggregate, FacetteEtablissement.OPTION, new RechercheCriteria(new LinkedMultiValueMap<>()), valeurs);

        assertEquals(1, valeurs.size());
        assertEquals("RESTAURATION", valeurs.getFirst().getCode());
        assertEquals("Restauration", valeurs.getFirst().getNom());
        assertEquals(3, valeurs.getFirst().getTotal());
    }

    @Test
    void extractBuckets_nested_missingInnerTerms_returnsEmpty() {
        Aggregate nestedAggregate = Aggregate.of(a -> a.nested(
                NestedAggregate.of(n -> n.docCount(0L))));

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(nestedAggregate, FacetteEtablissement.OPTION, new RechercheCriteria(new LinkedMultiValueMap<>()), valeurs);

        assertTrue(valeurs.isEmpty());
    }

    @Test
    void extractBuckets_sterms_emptyBuckets_returnsEmpty() {
        Aggregate aggregate = Aggregate.of(a -> a.sterms(
                StringTermsAggregate.of(st -> st
                        .buckets(bkts -> bkts.array(List.of()))
                        .sumOtherDocCount(0L))));

        List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
        service.extractBuckets(aggregate, FacetteEtablissement.SECTEUR, new RechercheCriteria(new LinkedMultiValueMap<>()), valeurs);

        assertTrue(valeurs.isEmpty());
    }
}

