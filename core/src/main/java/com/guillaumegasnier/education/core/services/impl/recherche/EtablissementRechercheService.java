package com.guillaumegasnier.education.core.services.impl.recherche;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
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
            "contrat",     "codeContrat",
            "nature",      "codeNature",
            "commune",     "codeCommune",
            "departement", "codeDepartement",
            "academie",    "codeAcademie",
            "option",      "options.codeOption"   // champ nested
    );

    private static final List<String> TEXT_FIELDS = List.of(
            "nom^5", "adresse", "codePostal", "nomSecteur", "nomNature",
            "nomCommune", "nomDepartement", "nomAcademie", "nomRegion", "nomPays",
            "options.nomOption"
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
    // Recherche
    // -------------------------------------------------------------------------

    public RechercheEtablissementDTO rechercheEtablissements(@NonNull RechercheCriteria criteria) {
        var dto = new RechercheEtablissementDTO();
        dto.setPage(Math.max(0, criteria.getPage()));
        dto.setQ(criteria.getQ());

        NativeQueryBuilder builder = NativeQuery.builder()
                .withQuery(RechercheQueryBuilder.buildTextQuery(criteria, TEXT_FIELDS))
                .withFilter(buildFiltresAvecNested(criteria, null))
                .withPageable(PageRequest.of(dto.getPage(), DEFAULT_PAGE_SIZE));

        for (FacetteEtablissement facette : FacetteEtablissement.values()) {
            final FacetteEtablissement f = facette;
            Query filtreAgg = buildFiltresAvecNested(criteria, f.getCode());
            builder.withAggregation(f.getCode(), buildAggregation(f, filtreAgg));
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
    // Helpers privés — filtres
    // -------------------------------------------------------------------------

    /**
     * Construit le post_filter (ou le filter d'une aggregation sticky) en gérant
     * les champs nested (ex: options.codeOption) : ils doivent être wrappés dans
     * une nested query, les champs plats restent des terms classiques.
     *
     * @param excludeKey clé de facette à exclure (sticky facets), null = tout inclure
     */
    private Query buildFiltresAvecNested(@NonNull RechercheCriteria criteria, String excludeKey) {
        var filtres = criteria.getFiltres();
        if (filtres == null || filtres.isEmpty()) {
            return QueryBuilders.matchAll().build()._toQuery();
        }

        var boolBuilder = QueryBuilders.bool();
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

            // Déterminer si le champ appartient à un objet nested
            FacetteEtablissement facette = facetteByUrlKey(key);
            if (facette != null && facette.isNested()) {
                // nested query : le filtre doit être exécuté dans le contexte du nested
                final String nestedPath = facette.getNestedPath();
                final String nestedField = field;
                final List<String> nestedValues = cleaned;
                Query nestedFilter = QueryBuilders.nested(n -> n
                        .path(nestedPath)
                        .query(RechercheQueryBuilder.buildFiltresQuery(
                                criteriaForField(nestedField, nestedValues), null,
                                Map.of(nestedField, nestedField))));
                boolBuilder.filter(nestedFilter);
            } else {
                boolBuilder.filter(RechercheQueryBuilder.buildFiltresQuery(
                        criteriaForField(field, cleaned), null, Map.of(field, field)));
            }
            hasFilter = true;
        }
        return hasFilter ? boolBuilder.build()._toQuery() : QueryBuilders.matchAll().build()._toQuery();
    }

    // -------------------------------------------------------------------------
    // Helpers privés — aggregations
    // -------------------------------------------------------------------------

    /**
     * Construit l'aggregation pour une facette :
     * - champ plat : filter → terms → top_hits (même pattern qu'avant)
     * - champ nested : filter → nested → terms → top_hits
     */
    private Aggregation buildAggregation(FacetteEtablissement f, Query filtreAgg) {
        if (f.isNested()) {
            // Pour un champ nested : filter > nested > terms > top_hits
            return Aggregation.of(a -> a
                    .filter(filtreAgg)
                    .aggregations(f.getCode() + "_inner",
                            Aggregation.of(inner -> inner
                                    .nested(n -> n.path(f.getNestedPath()))
                                    .aggregations(f.getCode() + "_terms",
                                            Aggregation.of(terms -> terms
                                                    .terms(t -> t.field(f.getCode()).size(FACET_SIZE))
                                                    .aggregations(f.getCodeNom(),
                                                            Aggregation.of(nom -> nom
                                                                    .topHits(th -> th
                                                                            .size(1)
                                                                            .source(s -> s
                                                                                    .filter(fi -> fi.includes(f.getCodeNom())))))))))));
        } else {
            // Champ plat : filter > terms > top_hits (pattern original)
            return Aggregation.of(a -> a
                    .filter(filtreAgg)
                    .aggregations(f.getCode() + "_inner",
                            Aggregation.of(inner -> inner
                                    .terms(t -> t.field(f.getCode()).size(FACET_SIZE))
                                    .aggregations(f.getCodeNom(),
                                            Aggregation.of(nom -> nom
                                                    .topHits(th -> th
                                                            .size(1)
                                                            .source(s -> s
                                                                    .filter(fi -> fi.includes(f.getCodeNom())))))))));
        }
    }

    // -------------------------------------------------------------------------
    // Helpers privés — extraction des buckets
    // -------------------------------------------------------------------------

    private void extractBuckets(Aggregate aggregate, FacetteEtablissement facette,
                                 RechercheCriteria criteria, List<RechercheFacetteValeurDTO> valeurs) {
        // Pour les champs nested, on descend d'un niveau supplémentaire (nested → terms)
        Aggregate termsAgg = aggregate;
        if (facette.isNested() && aggregate.isNested()) {
            termsAgg = aggregate.nested().aggregations().get(facette.getCode() + "_terms");
            if (termsAgg == null) return;
        }

        if (termsAgg.isSterms()) {
            termsAgg.sterms().buckets().array().forEach(b -> {
                RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                v.setCode(RechercheQueryBuilder.fieldValueToString(b.key()));
                var topHitsHit = b.aggregations().get(facette.getCodeNom())
                        .topHits().hits().hits().getFirst().source();
                // nomOption est un MultiField : on récupère via le chemin relatif au nested
                String nomField = facette.getCodeNom().contains(".")
                        ? facette.getCodeNom().substring(facette.getCodeNom().lastIndexOf('.') + 1)
                        : facette.getCodeNom();
                v.setNom((String) topHitsHit.to(Map.class).get(nomField));
                v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                v.setChecked(RechercheQueryBuilder.toChecked(facette.getCode(), v.getCode(), criteria));
                valeurs.add(v);
            });
        } else if (termsAgg.isLterms()) {
            termsAgg.lterms().buckets().array().forEach(b -> {
                RechercheFacetteValeurDTO v = new RechercheFacetteValeurDTO();
                v.setCode(String.valueOf(b.key()));
                var topHitsHit = b.aggregations().get(facette.getCodeNom())
                        .topHits().hits().hits().getFirst().source();
                String nomField = facette.getCodeNom().contains(".")
                        ? facette.getCodeNom().substring(facette.getCodeNom().lastIndexOf('.') + 1)
                        : facette.getCodeNom();
                v.setNom((String) topHitsHit.to(Map.class).get(nomField));
                v.setTotal((int) Math.min(b.docCount(), Integer.MAX_VALUE));
                v.setChecked(RechercheQueryBuilder.toChecked(facette.getCode(), v.getCode(), criteria));
                valeurs.add(v);
            });
        }
    }

    // -------------------------------------------------------------------------
    // Utilitaires
    // -------------------------------------------------------------------------

    /** Retrouve la FacetteEtablissement dont le champ URL correspond à la clé. */
    private FacetteEtablissement facetteByUrlKey(String key) {
        String field = FILTRE_KEY_TO_FIELD.getOrDefault(key, key);
        for (FacetteEtablissement f : FacetteEtablissement.values()) {
            if (f.getCode().equals(field)) return f;
        }
        return null;
    }

    /**
     * Crée un RechercheCriteria minimal portant un seul filtre (field=values)
     * pour déléguer la construction de la terms query à RechercheQueryBuilder.
     */
    private RechercheCriteria criteriaForField(String field, List<String> values) {
        var map = new org.springframework.util.LinkedMultiValueMap<String, String>();
        map.addAll(field, values);
        return new RechercheCriteria(map);
    }
}
