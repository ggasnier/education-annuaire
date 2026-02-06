package com.guillaumegasnier.education.core.services.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheDTO;
import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import com.guillaumegasnier.education.core.dto.RechercheFacetteValeurDTO;
import com.guillaumegasnier.education.core.enums.FacetteEtablissement;
import com.guillaumegasnier.education.core.repositories.RechercheEtablissementRepository;
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
    //private static final int MAX_PAGE_SIZE = 50;
    private static final int FACET_SIZE = 50;

    private static final Map<String, String> FILTRE_KEY_TO_FIELD = Map.of(
            "contrat", "codeContrat", // à remplacer par public/privé
            "nature", "codeNature",
            "commune", "codeCommune",
            "departement", "codeDepartement",
            "academie", "codeAcademie"
            //     "region", "codeRegion",
            //       "pays", "codePays"
    );

    private final RechercheEtablissementRepository rechercheEtablissementRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public CoreRechercheServiceImpl(
            RechercheEtablissementRepository rechercheEtablissementRepository,
            ElasticsearchOperations elasticsearchOperations) {
        this.rechercheEtablissementRepository = rechercheEtablissementRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * <p>
     * Transformation des critères de recherche en query ES
     * </p>
     *
     * @param criteria Critères de recherche, de la forme code*
     * @return Query ES
     */
    private static Query buildBaseQuery(@NonNull RechercheCriteria criteria, Boolean useFiltres) {
        BoolQuery.Builder bool = QueryBuilders.bool();
        String q = criteria.getQ();
        MultiValueMap<String, String> filtres = criteria.getFiltres();

        // Avec ou sans recherche textuelle
        if (q == null || q.isBlank()) {
            bool.must(QueryBuilders.matchAll().build()._toQuery());
        } else {
            // Champs textuels à interroger
            List<String> textFields = List.of(
                    "nom^5",
                    "adresse",
                    "codePostal",
                    "nomSecteur",
                    "nomNature",
                    "nomCommune",
                    "nomDepartement",
                    "nomAcademie",
                    "nomRegion",
                    "nomPays"
            );

            // multi_match phrase (prioritaire)
            Query phrase = QueryBuilders.multiMatch(m -> m
                    .query(q)
                    .type(TextQueryType.Phrase)
                    .slop(2)
                    .fields(textFields));

            // multi_match cross_fields fuzzy=1 -> permet que les termes se répartissent
            // sur plusieurs champs (ex: nom contains "claude monet" and
            // nomCommune contains "paris")
            Query fuzzy = QueryBuilders.multiMatch(m -> m
                    .query(q)
                    .type(TextQueryType.CrossFields)
                    .operator(Operator.And)
                    //.fuzziness("1")
                    .fields(textFields));

            bool.must(QueryBuilders.bool(b -> b.should(phrase, fuzzy)));
        }

        // Si présence de filtres et qu'on doit les utiliser
        if (filtres != null && !filtres.isEmpty() && useFiltres) {
            filtres.forEach((key, values) -> {
                // On évite les valeurs null
                if (key == null || values == null || values.isEmpty()) {
                    return;
                }

                // On évite les variables spéciales
                if ("q".equalsIgnoreCase(key) || "page".equalsIgnoreCase(key) || "size".equalsIgnoreCase(key)) {
                    return;
                }

                // Que les filtres autorisés
                String field = FILTRE_KEY_TO_FIELD.getOrDefault(key, key);
                List<String> cleaned = values.stream().filter(v -> v != null && !v.isBlank()).toList();
                if (cleaned.isEmpty()) {
                    return;
                }

                // Match n'importe quelle valeur sélectionnée pour ce filtre.
                bool.filter(QueryBuilders.terms(t -> t
                        .field(field)
                        .terms(tv -> tv.value(cleaned.stream()
                                .map(co.elastic.clients.elasticsearch._types.FieldValue::of).toList()))));
            });
        }

        return bool.build()._toQuery();
    }

//    /**
//     * <p>
//     * Transformation des critères de recherche en query ES
//     * </p>
//     *
//     * @param criteria Critères de recherche, de la forme code*
//     * @return Query ES
//     */
//    @Deprecated
//    private static Query buildBaseQuery(@NonNull RechercheCriteria criteria, Boolean useFiltres) {
//        BoolQuery.Builder bool = QueryBuilders.bool();
//        String q = criteria.getQ();
//        MultiValueMap<String, String> filtres = criteria.getFiltres();
//
//        // Avec ou sans recherche textuelle
//        if (q == null || q.isBlank()) {
//            bool.must(QueryBuilders.matchAll().build()._toQuery());
//        } else {
//            bool.must(QueryBuilders.multiMatch(m -> m
//                    .query(q)
//                    .fields("description")
//                    .operator(Operator.And)
//                    .fuzziness("AUTO"))); // TODO
//        }
//
//        // Si présence de filtres et qu'on doit les utiliser
//        if (filtres != null && !filtres.isEmpty() && useFiltres) {
//            filtres.forEach((key, values) -> {
//                // On évite les valeurs null
//                if (key == null || values == null || values.isEmpty()) {
//                    return;
//                }
//
//                // On évite les variables spéciales
//                if ("q".equalsIgnoreCase(key) || "page".equalsIgnoreCase(key) || "size".equalsIgnoreCase(key)) {
//                    return;
//                }
//
//                // Que les filtres autorisés
//                String field = FILTRE_KEY_TO_FIELD.getOrDefault(key, key);
//                List<String> cleaned = values.stream().filter(v -> v != null && !v.isBlank()).toList();
//                if (cleaned.isEmpty()) {
//                    return;
//                }
//
//                // Match n'importe quelle valeur sélectionnée pour ce filtre.
//                bool.filter(QueryBuilders.terms(t -> t
//                        .field(field)
//                        .terms(tv -> tv.value(cleaned.stream()
//                                .map(co.elastic.clients.elasticsearch._types.FieldValue::of).toList()))));
//            });
//        }
//
//        return bool.build()._toQuery();
//    }

    private static String fieldValueToString(FieldValue fieldValue) {
        if (fieldValue == null || fieldValue.isNull()) {
            return "";
        }
        if (fieldValue.isString()) {
            return fieldValue.stringValue();
        }
        if (fieldValue.isLong()) {
            return String.valueOf(fieldValue.longValue());
        }
        if (fieldValue.isDouble()) {
            return String.valueOf(fieldValue.doubleValue());
        }
        if (fieldValue.isBoolean()) {
            return String.valueOf(fieldValue.booleanValue());
        }
        return String.valueOf(fieldValue._get());
    }

    private static String toChecked(String codeFacette, String codeValeur, RechercheCriteria criteria) {
        if (!criteria.getFiltres().isEmpty()) {
            var i = criteria.getFiltres().get(codeFacette);
            if (i != null && i.contains(codeValeur)) {
                return "checked";
            }
        }
        return null;
    }

    @Override
    public void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities) {
        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            List<RechercheEtablissementEntity> batch = entities.subList(i, end);
            rechercheEtablissementRepository.saveAll(batch);
        }
    }

//    @Deprecated
//    @Override
//    public RechercheResultatsDTO searchEtablissements(@NonNull RechercheCriteria criteria) {
//
//        log.info("criteria:{}", criteria);
//
//        RechercheResultatsDTO dto = new RechercheResultatsDTO();
//
//        dto.setPage(Math.max(0, criteria.getPage()));
//        dto.setQ(criteria.getQ());
//
//        NativeQueryBuilder builder = NativeQuery.builder()
//                .withQuery(buildBaseQuery(criteria, true))
//                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));
//
//        // Les facettes
//        /*for (FacetteEtablissement facetteEtablissement : FacetteEtablissement.values()) {
//            builder.withAggregation(
//                    facetteEtablissement.getCode(),
//                    Aggregation.of(a -> a.terms(t -> t
//                            .field(facetteEtablissement.getCode())
//                            .size(FACET_SIZE)).aggregations(facetteEtablissement.getCodeNom(),
//                            Aggregation.of(agg -> agg.topHits(th -> th
//                                    .size(1)
//                                    .source(s -> s
//                                            .filter(f -> f.includes(facetteEtablissement.getCodeNom()))))))));
//
//        }*/
//
//        SearchHits<RechercheEtablissementEntity> hits = elasticsearchOperations.search(builder.build(),
//                RechercheEtablissementEntity.class);
//
//        // Nombre de résultats
//        dto.setTotal(hits.getTotalHits());
//        // Les résultats
//        dto.setResultats(hits.getSearchHits().stream().map(SearchHit::getContent).toList());
//
//        return dto;
//    }

//    @Deprecated
//    @Override
//    public List<RechercheFacetteDTO> searchEtablissementsFacettes(RechercheCriteria criteria) {
//
//        log.info("criteria:{}", criteria);
//
//        List<RechercheFacetteDTO> facettes = new ArrayList<>();
//
//        var builder = NativeQuery.builder()
//                .withQuery(buildBaseQuery(criteria, false))
//                .withMaxResults(0);
//
//        for (FacetteEtablissement facetteEtablissement : FacetteEtablissement.values()) {
//            builder.withAggregation(
//                    facetteEtablissement.getCode(),
//                    Aggregation.of(a -> a.terms(t -> t
//                            .field(facetteEtablissement.getCode())
//                            .size(FACET_SIZE)).aggregations(facetteEtablissement.getCodeNom(),
//                            Aggregation.of(agg -> agg.topHits(th -> th
//                                    .size(1)
//                                    .source(s -> s
//                                            .filter(f -> f.includes(facetteEtablissement.getCodeNom()))))))));
//        }
//
//        SearchHits<RechercheEtablissementEntity> hits = elasticsearchOperations.search(builder.build(),
//                RechercheEtablissementEntity.class);
//        if (hits.getAggregations() == null || !(hits.getAggregations() instanceof ElasticsearchAggregations aggregations)) {
//            return facettes;
//        }
//
//        // Etape de transformation (à optimiser)
//        Map<String, Aggregate> aggs = new LinkedHashMap<>();
//        aggregations.aggregationsAsMap()
//                .forEach((name, aggregation) -> aggs.put(name, aggregation.aggregation().getAggregate()));
//
//        for (FacetteEtablissement facetteEtablissement : FacetteEtablissement.values()) {
//            Aggregate aggregate = aggs.get(facetteEtablissement.getCode());
//            if (aggregate == null)
//                continue;
//
//            // On extrait les valeurs (buckets) et on les map en RechercheFacetteValeurDTO
//            List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
//
//            if (aggregate.isSterms()) {
//                aggregate.sterms().buckets().array().forEach(b -> {
//                    RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
//                    v.setCode(fieldValueToString(b.key()));
//                    v.setNom((String) b.aggregations().get(facetteEtablissement.getCodeNom()).topHits().hits().hits().getFirst().source().to(Map.class).get(facetteEtablissement.getCodeNom()));
//                    v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
//                    v.setChecked(toChecked(facetteEtablissement.getCode(), v.getCode(), criteria));
//                    valeurs.add(v);
//                });
//            } else if (aggregate.isLterms() || aggregate.isDterms()) {
//                aggregate.lterms().buckets().array().forEach(b -> {
//                    RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
//                    v.setCode(String.valueOf(b.key()));
//                    v.setNom((String) b.aggregations().get(facetteEtablissement.getCodeNom()).topHits().hits().hits().getFirst().source().to(Map.class).get(facetteEtablissement.getCodeNom()));
//                    v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
//                    v.setChecked(toChecked(facetteEtablissement.getCode(), v.getCode(), criteria));
//                    valeurs.add(v);
//                });
//            }
//
//            RechercheFacetteDTO facette = new RechercheFacetteDTO(facetteEtablissement.getCode(), facetteEtablissement.getNom(), valeurs);
//            facettes.add(facette);
//        }
//
//        return facettes;
//    }

    @Override
    public RechercheDTO recherche(@NonNull RechercheCriteria criteria) {
        var dto = new RechercheDTO();

        dto.setPage(Math.max(0, criteria.getPage()));
        dto.setQ(criteria.getQ());

        // La recherche textuelle
        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(buildBaseQuery(criteria, true))
                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));
        // On lance la recherche
        SearchHits<RechercheEtablissementEntity> hits = elasticsearchOperations.search(builder.build(), RechercheEtablissementEntity.class);
        // Nombre de résultats
        dto.setTotal(hits.getTotalHits());
        // Les résultats
        dto.setResultats(hits.getSearchHits().stream().map(SearchHit::getContent).toList());

        // Les facettes
        builder = NativeQuery.builder()
                .withQuery(buildBaseQuery(criteria, false))
                .withMaxResults(0);

        for (FacetteEtablissement facetteEtablissement : FacetteEtablissement.values()) {
            builder.withAggregation(
                    facetteEtablissement.getCode(),
                    Aggregation.of(a -> a.terms(t -> t
                            .field(facetteEtablissement.getCode())
                            .size(FACET_SIZE)).aggregations(facetteEtablissement.getCodeNom(),
                            Aggregation.of(agg -> agg.topHits(th -> th
                                    .size(1)
                                    .source(s -> s
                                            .filter(f -> f.includes(facetteEtablissement.getCodeNom()))))))));
        }

        // On relance la recherche
        hits = elasticsearchOperations.search(builder.build(), RechercheEtablissementEntity.class);

        // Si on a des résultats d'aggrégations
        if (hits.getAggregations() != null && hits.getAggregations() instanceof ElasticsearchAggregations aggregations) {

            // Etape de transformation (à optimiser)
            Map<String, Aggregate> aggs = new LinkedHashMap<>();
            aggregations.aggregationsAsMap()
                    .forEach((name, aggregation) -> aggs.put(name, aggregation.aggregation().getAggregate()));

            for (FacetteEtablissement facetteEtablissement : FacetteEtablissement.values()) {
                Aggregate aggregate = aggs.get(facetteEtablissement.getCode());
                if (aggregate == null)
                    continue;

                // On extrait les valeurs (buckets) et on les map en RechercheFacetteValeurDTO
                List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();

                if (aggregate.isSterms()) {
                    aggregate.sterms().buckets().array().forEach(b -> {
                        RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                        v.setCode(fieldValueToString(b.key()));
                        v.setNom((String) b.aggregations().get(facetteEtablissement.getCodeNom()).topHits().hits().hits().getFirst().source().to(Map.class).get(facetteEtablissement.getCodeNom()));
                        v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                        v.setChecked(toChecked(facetteEtablissement.getCode(), v.getCode(), criteria));
                        valeurs.add(v);
                    });
                } else if (aggregate.isLterms() || aggregate.isDterms()) {
                    aggregate.lterms().buckets().array().forEach(b -> {
                        RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                        v.setCode(String.valueOf(b.key()));
                        v.setNom((String) b.aggregations().get(facetteEtablissement.getCodeNom()).topHits().hits().hits().getFirst().source().to(Map.class).get(facetteEtablissement.getCodeNom()));
                        v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                        v.setChecked(toChecked(facetteEtablissement.getCode(), v.getCode(), criteria));
                        valeurs.add(v);
                    });
                }

                RechercheFacetteDTO facette = new RechercheFacetteDTO(facetteEtablissement.getCode(), facetteEtablissement.getNom(), valeurs);
                dto.getFacettes().add(facette);
            }
        }

        return dto;
    }
}
