package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.web.dto.FacetteRechercheDto;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import com.guillaumegasnier.education.web.services.RechercheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;


@Slf4j
@Service
public class RechercheServiceImpl implements RechercheService {

    private final CoreEtablissementService coreEtablissementService;
//    private final ElasticsearchClient elasticsearchClient;
//    private final ElasticsearchOperations elasticsearchOperations;
//    private final IndexCoordinates index = IndexCoordinates.of("documents");
//    private final Map<Categorie, NomResolver> resolvers = new EnumMap<>(Categorie.class);


    @Autowired
    public RechercheServiceImpl(CoreEtablissementService coreEtablissementService) {
//        this.elasticsearchClient = elasticsearchClient;
        this.coreEtablissementService = coreEtablissementService;
//        this.elasticsearchOperations = elasticsearchOperations;
    }

//    @PostConstruct
//    public void initResolvers() {
//        resolvers.put(Categorie.CODE_CONTRAT, this::resolveNomContrat);
//        resolvers.put(Categorie.CODE_NATURE, this::resolveNomNature);
//        resolvers.put(Categorie.CATEGORIE, this::resolveNomCategorie);
//        resolvers.put(Categorie.OPTION, this::resolveNomOption);
//        resolvers.put(Categorie.SPECIALITE, this::resolveNomSpecialite);
//    }

//    @Cacheable(value = "natures", key = "code")
//    public String resolveNomNature(String code) {
//        return coreEtablissementService.findNature(code)
//                .map(NatureEntity::getNom)
//                .orElse("Inconnu");
//    }
//
//    @Cacheable("contrats")
//    public String resolveNomContrat(String code) {
//        return coreEtablissementService.findContrat(code)
//                .map(ContratEntity::getNom)
//                .orElse("Inconnu");
//    }
//
//    public String resolveNomCategorie(String code) {
//        return Optional.of(Categorie.valueOf(code.toUpperCase(Locale.ROOT)))
//                .map(Categorie::getNom)
//                .orElse("Inconnu");
//    }
//
//    public String resolveNomOption(String code) {
//        return Optional.of(OptionEtablissement.valueOf(code.toUpperCase(Locale.ROOT)))
//                .map(OptionEtablissement::getNom)
//                .orElse("Inconnu");
//    }
//
//    public String resolveNomSpecialite(String code) {
//        return Optional.of(SpecialiteBac.valueOf(code.toUpperCase(Locale.ROOT)))
//                .map(SpecialiteBac::getNom)
//                .orElse("Inconnu");
//    }

    @Override
    public ResultatRechercheDto recherche(MultiValueMap<String, String> facettes) {

//        CritereRecherchDto criteres = new CritereRecherchDto(facettes);
//
//        log.debug("criteres: {}", criteres);
//
//        BoolQuery.Builder builder = QueryBuilders.bool();
//
//        if (criteres.getQ().isBlank()) {
//            builder.must(QueryBuilders.matchAll().build()._toQuery());
//        } else {
//            builder.must(QueryBuilders.multiMatch(m -> m
//                    .query(criteres.getQ())
//                    .fields("nom^4", "description", "nomCommune^2", "nomNature", "nomContrat")
//                    .fuzziness("AUTO")));
//        }
//
////        if (criteres.getCodeNature() != null && !criteres.getCodeNature().isEmpty()) {
////            builder.filter(QueryBuilders.term(t -> t.field("codeNature").value(criteres.getCodeNature()))); // todo
////        }
//
//        NativeQuery query = NativeQuery.builder()
//                .withQuery(builder.build()._toQuery())
//                .withPageable(PageRequest.of(criteres.getPage(), criteres.getSize()))
//                .build();
//
//        SearchHits<DocumentEntity> hits = elasticsearchOperations.search(query, DocumentEntity.class, index);
//        var docs = hits.getSearchHits().stream().map(SearchHit::getContent).toList();
//        long total = hits.getTotalHits();
//        return new ResultatRechercheDto(docs, total, criteres.getPage(), criteres.getSize());
        return null;
    }

    @Override
    public List<FacetteRechercheDto> facette(MultiValueMap<String, String> facettes) throws IOException {

//        CritereRecherchDto criteres = new CritereRecherchDto(facettes);
//
//        log.debug("criteres: {}", criteres);
//
//        // construire la query de base
//        Query base;
//        if (criteres.getQ() == null || criteres.getQ().isBlank()) {
//            base = Query.of(m -> m.matchAll(ma -> ma));
//        } else {
//            base = Query.of(mm -> mm.multiMatch(m -> m
//                    .query(criteres.getQ())
//                    .fields("nom", "description", "nomCommune", "nomNature", "nomContrat")
//                    .fuzziness("AUTO")
//            ));
//        }

//        if (criteres.getCodeNature() != null && !criteres.getCodeNature().isEmpty()) {
//            Query termFilter = Query.of(t -> t.term(tr -> tr.field("codeNature").value(criteres.getCodeNature())));
//            base = Query.of(b -> b.bool(bb -> bb.must(base).filter(termFilter)));
//        }

        // exécuter search avec aggregations terms
//        SearchResponse<DocumentEntity> resp = elasticsearchClient.search(s -> s
//                        .index("documents")
//                        .query(base)
//                        .size(0)
//                        .aggregations("docType", a -> a.terms(t -> t.field("docType").size(20)))
//                        .aggregations("codeCommune", a -> a.terms(t -> t.field("codeCommune").size(20)))
//                        .aggregations("codeNature", a -> a.terms(t -> t.field("codeNature").size(20)))
//                        .aggregations("codeContrat", a -> a.terms(t -> t.field("codeContrat").size(20)))
//                , DocumentEntity.class);
//
//        List<FacetteRechercheDto> facets = new ArrayList<>();
//
//        Map<String, Aggregate> aggs = resp.aggregations();
//        if (aggs == null) return facets;
//
//        log.info("aggs {}", aggs);
//
//        aggs.forEach((s, aggregate) -> {
//            log.info("s {}", s);
////            log.info("aggregate {}", aggregate.);
////            aggregate.get
//        });
//
//        // helper pour extraire Terms aggregate
////        Function<String, List<FacetteRechercheDto>> extract = name -> {
////            List<FacetteRechercheDto> buckets = new ArrayList<>();
////            Aggregate agg = aggs.get(name);
////            if (agg == null) return buckets;
////            // essayer comme StringTermsAggregate
//////            StringTermsAggregate sta = agg.stringTerms();
////
////            log.info("agg {}", agg);
////
//////            if (sta != null && sta.buckets() != null) {
//////                sta.buckets().array().forEach(b -> {
//////                    String key = b.key().stringValue();
//////                    long count = b.docCount();
//////                    buckets.add(new FacetteRechercheDto(key, count));
//////                });
//////            }
////            return buckets;
////        };
//
////        facets.add(new FacetteRechercheDto("docType", extract.apply("by_docType")));
////        facets.add(new FacetteRechercheDto("nomCommune", extract.apply("by_commune")));
////        facets.add(new FacetteRechercheDto("nomContrat", extract.apply("by_contrat")));

        return null;
    }

//    private FacetteRechercheDto toFacetteRechercheDto(FacetteRechercheDto facetteDto) {
//        NomResolver resolver = resolvers.get(Categorie.valueOf(facetteDto.getCodeCategorie().toUpperCase(Locale.ROOT)));
//        if (resolver == null) {
//            log.info("Pas de resolver pour {}", facetteDto.getCodeCategorie().toUpperCase(Locale.ROOT));
//            return facetteDto;
//        }
//
//        facetteDto.setValeurs(
//                facetteDto.getValeurs().stream()
//                        .map(v -> {
//                            v.setNom(resolver.resolveNom(v.getCode()));
//                            return v;
//                        })
//                        .toList()
//        );
//
//        return facetteDto;
//    }
}
