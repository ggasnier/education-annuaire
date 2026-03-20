package com.guillaumegasnier.education.core.services.impl.recherche;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import com.guillaumegasnier.education.core.dto.RechercheFacetteValeurDTO;
import com.guillaumegasnier.education.core.dto.recherche.RechercheEtablissementDTO;
import com.guillaumegasnier.education.core.enums.FacetteEtablissement;
import com.guillaumegasnier.education.core.repositories.RechercheEtablissementRepository;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EtablissementRechercheService {

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

    private static final List<String> TEXT_FIELDS = List.of(
            "nom^5", "adresse", "codePostal", "nomSecteur", "nomNature",
            "nomCommune", "nomDepartement", "nomAcademie", "nomRegion", "nomPays"
    );

    private final RechercheEtablissementRepository repository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public EtablissementRechercheService(RechercheEtablissementRepository repository,
                                         ElasticsearchOperations elasticsearchOperations) {
        this.repository = repository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    // -------------------------------------------------------------------------
    // Recherche (ancienne API — RechercheDTO avec facettes sticky)
    // -------------------------------------------------------------------------

//    /**
//     * Recherche d'établissements avec facettes sticky (1 seule requête ES).
//     * <ul>
//     *   <li>{@code query} — texte seul (scoring)</li>
//     *   <li>{@code post_filter} — tous les filtres actifs (résultats paginés)</li>
//     *   <li>Aggregations — une filter-agg par facette avec tous les filtres SAUF le sien</li>
//     * </ul>
//     */
//    public RechercheDTO recherche(@NonNull RechercheCriteria criteria) {
//        var dto = new RechercheDTO();
//        dto.setPage(Math.max(0, criteria.getPage()));
//        dto.setQ(criteria.getQ());
//
//        NativeQueryBuilder builder = NativeQuery.builder()
//                .withQuery(RechercheQueryBuilder.buildTextQuery(criteria, TEXT_FIELDS))
//                .withFilter(RechercheQueryBuilder.buildFiltresQuery(criteria, null, FILTRE_KEY_TO_FIELD))
//                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));
//
//        for (FacetteEtablissement facette : FacetteEtablissement.values()) {
//            final FacetteEtablissement f = facette;
//            Query filtreAgg = RechercheQueryBuilder.buildFiltresQuery(criteria, f.getCode(), FILTRE_KEY_TO_FIELD);
//            builder.withAggregation(
//                    f.getCode(),
//                    Aggregation.of(a -> a
//                            .filter(filtreAgg)
//                            .aggregations(f.getCode() + "_inner",
//                                    Aggregation.of(inner -> inner
//                                            .terms(t -> t.field(f.getCode()).size(FACET_SIZE))
//                                            .aggregations(f.getCodeNom(),
//                                                    Aggregation.of(nom -> nom
//                                                            .topHits(th -> th
//                                                                    .size(1)
//                                                                    .source(s -> s
//                                                                            .filter(fi -> fi.includes(f.getCodeNom()))))))))
//                    ));
//        }
//
//        SearchHits<RechercheEtablissementEntity> hits = elasticsearchOperations.search(
//                builder.build(), RechercheEtablissementEntity.class);
//
//        dto.setTotal(hits.getTotalHits());
//        dto.setResultats(hits.getSearchHits().stream().map(SearchHit::getContent).toList());
//
//        if (hits.getAggregations() instanceof ElasticsearchAggregations aggregations) {
//            Map<String, Aggregate> aggs = new LinkedHashMap<>();
//            aggregations.aggregationsAsMap()
//                    .forEach((name, agg) -> aggs.put(name, agg.aggregation().getAggregate()));
//            for (FacetteEtablissement facette : FacetteEtablissement.values()) {
//                Aggregate filterAgg = aggs.get(facette.getCode());
//                if (filterAgg == null || !filterAgg.isFilter()) continue;
//                Aggregate innerAgg = filterAgg.filter().aggregations().get(facette.getCode() + "_inner");
//                if (innerAgg == null) continue;
//                List<RechercheFacetteValeurDTO> valeurs = new ArrayList<>();
//                extractBuckets(innerAgg, facette, criteria, valeurs);
//                if (!valeurs.isEmpty()) {
//                    dto.getFacettes().add(new RechercheFacetteDTO(facette.getCode(), facette.getNom(), valeurs));
//                }
//            }
//        }
//        return dto;
//    }

    // -------------------------------------------------------------------------
    // Recherche (nouvelle API — RechercheEtablissementDTO)
    // -------------------------------------------------------------------------

    public RechercheEtablissementDTO rechercheEtablissements(@NonNull RechercheCriteria criteria) {
        var dto = new RechercheEtablissementDTO();
        dto.setPage(Math.max(0, criteria.getPage()));
        dto.setQ(criteria.getQ());

        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(RechercheQueryBuilder.buildTextQuery(criteria, TEXT_FIELDS))
                .withFilter(RechercheQueryBuilder.buildFiltresQuery(criteria, null, FILTRE_KEY_TO_FIELD))
                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));

        for (FacetteEtablissement facette : FacetteEtablissement.values()) {
            final FacetteEtablissement f = facette;
            Query filtreAgg = RechercheQueryBuilder.buildFiltresQuery(criteria, f.getCode(), FILTRE_KEY_TO_FIELD);
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

    // -------------------------------------------------------------------------
    // Persistence
    // -------------------------------------------------------------------------

    public void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities) {
        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            repository.saveAll(entities.subList(i, end));
        }
    }

    // -------------------------------------------------------------------------
    // Helpers privés
    // -------------------------------------------------------------------------

    private void extractBuckets(Aggregate aggregate, FacetteEtablissement facette,
                                RechercheCriteria criteria, List<RechercheFacetteValeurDTO> valeurs) {
        if (aggregate.isSterms()) {
            aggregate.sterms().buckets().array().forEach(b -> {
                RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                v.setCode(RechercheQueryBuilder.fieldValueToString(b.key()));
                v.setNom((String) b.aggregations().get(facette.getCodeNom())
                        .topHits().hits().hits().getFirst().source()
                        .to(Map.class).get(facette.getCodeNom()));
                v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                v.setChecked(RechercheQueryBuilder.toChecked(facette.getCode(), v.getCode(), criteria));
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
                v.setChecked(RechercheQueryBuilder.toChecked(facette.getCode(), v.getCode(), criteria));
                valeurs.add(v);
            });
        }
    }
}

