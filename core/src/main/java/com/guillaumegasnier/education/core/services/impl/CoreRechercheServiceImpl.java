package com.guillaumegasnier.education.core.services.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheDTO;
import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import com.guillaumegasnier.education.core.dto.RechercheFacetteValeurDTO;
import com.guillaumegasnier.education.core.dto.recherche.*;
import com.guillaumegasnier.education.core.enums.FacetteEtablissement;
import com.guillaumegasnier.education.core.repositories.RechercheEtablissementRepository;
import com.guillaumegasnier.education.core.repositories.RechercheMetierRepository;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CoreRechercheServiceImpl implements CoreRechercheService {
    private static final int BATCH_SIZE = 1000;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int FACET_SIZE = 50;
    private static final Map<String, String> FILTRE_KEY_TO_FIELD = Map.of(
            "contrat", "codeContrat",
            "nature", "codeNature",
            "commune", "codeCommune",
            "departement", "codeDepartement",
            "academie", "codeAcademie"
    );
    private final RechercheEtablissementRepository rechercheEtablissementRepository;
    private final RechercheMetierRepository rechercheMetierRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public CoreRechercheServiceImpl(
            RechercheEtablissementRepository rechercheEtablissementRepository,
            RechercheMetierRepository rechercheMetierRepository,
            ElasticsearchOperations elasticsearchOperations) {
        this.rechercheEtablissementRepository = rechercheEtablissementRepository;
        this.rechercheMetierRepository = rechercheMetierRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }
    // -------------------------------------------------------------------------
    // Helpers de construction de requetes
    // -------------------------------------------------------------------------

    /**
     * Construit uniquement la partie textuelle de la query (sans filtres).
     */
    private static Query buildTextQuery(@NonNull RechercheCriteria criteria, List<String> textFields) {
        String q = criteria.getQ();
        if (q == null || q.isBlank()) {
            return QueryBuilders.matchAll().build()._toQuery();
        }
        Query phrase = QueryBuilders.multiMatch(m -> m
                .query(q).type(TextQueryType.Phrase).slop(2).fields(textFields));
        Query fuzzy = QueryBuilders.multiMatch(m -> m
                .query(q).type(TextQueryType.CrossFields).operator(Operator.And).fields(textFields));
        return QueryBuilders.bool(b -> b.should(phrase, fuzzy));
    }

    /**
     * Construit un BoolQuery de filtres pour toutes les facettes actives,
     * en excluant optionnellement la cle {@code excludeKey} (null = tout inclure).
     * <p>
     * Utilise a la fois pour le {@code post_filter} (excludeKey=null)
     * et pour les filter-aggregations sticky (excludeKey=facette.code).
     */
    private static Query buildFiltresQuery(@NonNull RechercheCriteria criteria, String excludeKey) {
        MultiValueMap<String, String> filtres = criteria.getFiltres();
        if (filtres == null || filtres.isEmpty()) {
            return QueryBuilders.matchAll().build()._toQuery();
        }
        BoolQuery.Builder bool = QueryBuilders.bool();
        boolean hasFilter = false;
        for (Map.Entry<String, List<String>> entry : filtres.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            if (key == null || values == null || values.isEmpty()) continue;
            if ("q".equalsIgnoreCase(key) || "page".equalsIgnoreCase(key) || "size".equalsIgnoreCase(key)) continue;
            if (key.equals(excludeKey)) continue;
            String field = FILTRE_KEY_TO_FIELD.getOrDefault(key, key);
            List<String> cleaned = values.stream().filter(v -> v != null && !v.isBlank()).toList();
            if (cleaned.isEmpty()) continue;
            bool.filter(QueryBuilders.terms(t -> t
                    .field(field)
                    .terms(tv -> tv.value(cleaned.stream().map(FieldValue::of).toList()))));
            hasFilter = true;
        }
        return hasFilter ? bool.build()._toQuery() : QueryBuilders.matchAll().build()._toQuery();
    }
    // -------------------------------------------------------------------------
    // Methodes publiques
    // -------------------------------------------------------------------------

    private static String fieldValueToString(FieldValue fv) {
        if (fv == null || fv.isNull()) return "";
        if (fv.isString()) return fv.stringValue();
        if (fv.isLong()) return String.valueOf(fv.longValue());
        if (fv.isDouble()) return String.valueOf(fv.doubleValue());
        if (fv.isBoolean()) return String.valueOf(fv.booleanValue());
        return String.valueOf(fv._get());
    }

    private static String toChecked(String codeFacette, String codeValeur, RechercheCriteria criteria) {
        if (!criteria.getFiltres().isEmpty()) {
            List<String> vals = criteria.getFiltres().get(codeFacette);
            if (vals != null && vals.contains(codeValeur)) {
                return "checked";
            }
        }
        return null;
    }

    /**
     * Recherche d'etablissements avec facettes sticky (1 seule requete ES).
     * <ul>
     *   <li>{@code query} - texte seul (scoring)</li>
     *   <li>{@code post_filter} - tous les filtres actifs (resultats pagines)</li>
     *   <li>Aggregations - une filter-agg par facette avec tous les filtres SAUF le sien</li>
     * </ul>
     */
    @Override
    public RechercheDTO recherche(@NonNull RechercheCriteria criteria) {
        var dto = new RechercheDTO();
        dto.setPage(Math.max(0, criteria.getPage()));
        dto.setQ(criteria.getQ());
        List<String> textFields = List.of(
                "nom^5", "adresse", "codePostal", "nomSecteur", "nomNature",
                "nomCommune", "nomDepartement", "nomAcademie", "nomRegion", "nomPays"
        );
        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(buildTextQuery(criteria, textFields))
                .withFilter(buildFiltresQuery(criteria, null))
                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));
        for (FacetteEtablissement facette : FacetteEtablissement.values()) {
            final FacetteEtablissement f = facette;
            Query filtreAgg = buildFiltresQuery(criteria, f.getCode());
            builder.withAggregation(
                    f.getCode(),
                    Aggregation.of(a -> a
                            .filter(filtreAgg)
                            .aggregations(f.getCode() + "_inner",
                                    Aggregation.of(inner -> inner
                                            .terms(t -> t.field(f.getCode()).size(FACET_SIZE))
                                            .aggregations(f.getCodeNom(),
                                                    Aggregation.of(nom -> nom
                                                            .topHits(th -> th
                                                                    .size(1)
                                                                    .source(s -> s
                                                                            .filter(fi -> fi.includes(f.getCodeNom()))))))))
                    ));
        }
        SearchHits<RechercheEtablissementEntity> hits = elasticsearchOperations.search(
                builder.build(), RechercheEtablissementEntity.class);
        dto.setTotal(hits.getTotalHits());
        dto.setResultats(hits.getSearchHits().stream().map(SearchHit::getContent).toList());
        if (hits.getAggregations() instanceof ElasticsearchAggregations aggregations) {
            Map<String, Aggregate> aggs = new LinkedHashMap<>();
            aggregations.aggregationsAsMap()
                    .forEach((name, agg) -> aggs.put(name, agg.aggregation().getAggregate()));
            for (FacetteEtablissement facette : FacetteEtablissement.values()) {
                Aggregate filterAgg = aggs.get(facette.getCode());
                if (filterAgg == null || !filterAgg.isFilter()) continue;
                Aggregate innerAgg = filterAgg.filter().aggregations().get(facette.getCode() + "_inner");
                if (innerAgg == null) continue;
                List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
                extractBuckets(innerAgg, facette, criteria, valeurs);
                if (!valeurs.isEmpty()) {
                    dto.getFacettes().add(new RechercheFacetteDTO(facette.getCode(), facette.getNom(), valeurs));
                }
            }
        }
        return dto;
    }

    @Override
    public RechercheMetierDTO rechercheMetiers(@NonNull RechercheCriteria criteria) {
        var dto = new RechercheMetierDTO();
        dto.setPage(Math.max(0, criteria.getPage()));
        dto.setQ(criteria.getQ());
        List<String> textFields = List.of("nom^5", "appellations", "description");
        SearchHits<RechercheMetierEntity> hits = elasticsearchOperations.search(
                NativeQuery.builder()
                        .withQuery(buildTextQuery(criteria, textFields))
                        .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE))
                        .build(),
                RechercheMetierEntity.class);
        dto.setTotal(hits.getTotalHits());
        dto.setResultats(hits.getSearchHits().stream().map(SearchHit::getContent).toList());
        return dto;
    }

    @Override
    public RechercheEtablissementDTO rechercheEtablissements(@NonNull RechercheCriteria criteria) {
        return new RechercheEtablissementDTO(); // TODO
    }

    @Override
    public RechercheCompetenceDTO rechercheCompetences(@NonNull RechercheCriteria criteria) {
        return new RechercheCompetenceDTO(); // TODO
    }

    @Override
    public RechercheCertificationDTO rechercheCertifications(@NonNull RechercheCriteria criteria) {
        return new RechercheCertificationDTO(); // TODO
    }

    @Override
    public RechercheFormationDTO rechercheFormations(@NonNull RechercheCriteria criteria) {
        return new RechercheFormationDTO(); // TODO
    }

    @Override
    public void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities) {
        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            rechercheEtablissementRepository.saveAll(entities.subList(i, end));
        }
    }

    @Override
    public void saveMetiers(List<RechercheMetierEntity> entities) {
        rechercheMetierRepository.saveAll(entities);
    }

    // -------------------------------------------------------------------------
    // Helpers prives
    // -------------------------------------------------------------------------
    private void extractBuckets(Aggregate aggregate, FacetteEtablissement facette,
                                RechercheCriteria criteria, List<RechercheFacetteValeurDTO> valeurs) {
        if (aggregate.isSterms()) {
            aggregate.sterms().buckets().array().forEach(b -> {
                RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                v.setCode(fieldValueToString(b.key()));
                v.setNom((String) b.aggregations().get(facette.getCodeNom())
                        .topHits().hits().hits().getFirst().source()
                        .to(Map.class).get(facette.getCodeNom()));
                v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                v.setChecked(toChecked(facette.getCode(), v.getCode(), criteria));
                valeurs.add(v);
            });
        } else if (aggregate.isLterms()) {
            aggregate.lterms().buckets().array().forEach(b -> {
                RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                v.setCode(String.valueOf(b.key()));
                v.setNom((String) b.aggregations().get(facette.getCodeNom())
                        .topHits().hits().hits().getFirst().source()
                        .to(Map.class).get(facette.getCodeNom()));
                v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                v.setChecked(toChecked(facette.getCode(), v.getCode(), criteria));
                valeurs.add(v);
            });
        }
    }
}
