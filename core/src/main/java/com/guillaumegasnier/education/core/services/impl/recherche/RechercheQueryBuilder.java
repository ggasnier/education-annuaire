package com.guillaumegasnier.education.core.services.impl.recherche;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Utilitaires statiques partagés entre tous les services de recherche ES.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RechercheQueryBuilder {


    /**
     * Retire les boosts d'une liste de champs (ex: "nom^5" → "nom").
     * Nécessaire pour le type {@code phrase} qui ne supporte pas les boosts.
     */
    private static List<String> stripBoosts(List<String> fields) {
        return fields.stream()
                .map(f -> f.contains("^") ? f.substring(0, f.indexOf('^')) : f)
                .toList();
    }

    /**
     * Construit la partie textuelle de la query (sans filtres).
     * Retourne un match_all si aucun texte n'est fourni.
     * <p>
     * Stratégie multi-clause :
     * <ul>
     *   <li>{@code phrase} (slop=2) : booste les correspondances de séquence dans un même champ</li>
     *   <li>{@code cross_fields} (operator=AND) : les tokens peuvent être répartis sur plusieurs champs
     *       (ex: "Lycée" dans {@code nom} et "Dreux" dans {@code nomCommune})</li>
     *   <li>{@code most_fields} (operator=OR) : filet de sécurité, remonte les établissements
     *       qui matchent au moins un token dans au moins un champ</li>
     * </ul>
     * {@code minimum_should_match=1} garantit qu'au moins l'une des trois clauses matche.
     */
    public static Query buildTextQuery(@NonNull RechercheCriteria criteria, List<String> textFields) {
        String q = criteria.getQ();
        if (q == null || q.isBlank()) {
            return QueryBuilders.matchAll().build()._toQuery();
        }
        List<String> fieldsWithoutBoosts = stripBoosts(textFields);
        // Séquence dans un même champ (boost fort sur correspondances proches)
        Query phrase = QueryBuilders.multiMatch(m -> m
                .query(q).type(TextQueryType.Phrase).slop(2).fields(fieldsWithoutBoosts));
        // Tokens répartis entre les champs (ex: "Lycée" dans nom + "Dreux" dans nomCommune)
        Query crossFields = QueryBuilders.multiMatch(m -> m
                .query(q).type(TextQueryType.CrossFields).operator(Operator.And).fields(fieldsWithoutBoosts));
        // Filet de sécurité : au moins un token dans au moins un champ (avec boosts)
        Query mostFields = QueryBuilders.multiMatch(m -> m
                .query(q).type(TextQueryType.MostFields).fields(textFields));
        return QueryBuilders.bool(b -> b.should(phrase, crossFields, mostFields).minimumShouldMatch("1"));
    }

    /**
     * Construit un BoolQuery de filtres pour toutes les facettes actives,
     * en excluant optionnellement la clé {@code excludeKey} (null = tout inclure).
     * <p>
     * Utilisé à la fois pour le {@code post_filter} (excludeKey=null)
     * et pour les filter-aggregations sticky (excludeKey=facette.code).
     *
     * @param filtreKeyToField mapping clé-URL → champ ES (ex: "contrat" → "codeContrat")
     */
    public static Query buildFiltresQuery(@NonNull RechercheCriteria criteria,
                                          String excludeKey,
                                          Map<String, String> filtreKeyToField) {
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
            String field = filtreKeyToField.getOrDefault(key, key);
            List<String> cleaned = values.stream().filter(v -> v != null && !v.isBlank()).toList();
            if (cleaned.isEmpty()) continue;
            bool.filter(QueryBuilders.terms(t -> t
                    .field(field)
                    .terms(tv -> tv.value(cleaned.stream().map(FieldValue::of).toList()))));
            hasFilter = true;
        }
        return hasFilter ? bool.build()._toQuery() : QueryBuilders.matchAll().build()._toQuery();
    }

    /**
     * Convertit un {@link FieldValue} ES en String lisible.
     */
    public static String fieldValueToString(FieldValue fv) {
        if (fv == null || fv.isNull()) return "";
        if (fv.isString()) return fv.stringValue();
        if (fv.isLong()) return String.valueOf(fv.longValue());
        if (fv.isDouble()) return String.valueOf(fv.doubleValue());
        if (fv.isBoolean()) return String.valueOf(fv.booleanValue());
        return String.valueOf(fv._get());
    }

    /**
     * Retourne {@code "checked"} si la valeur {@code codeValeur} est active
     * dans la facette {@code codeFacette} des critères courants.
     */
    public static String toChecked(String codeFacette, String codeValeur,
                                   @NonNull RechercheCriteria criteria) {
        if (!criteria.getFiltres().isEmpty()) {
            List<String> vals = criteria.getFiltres().get(codeFacette);
            if (vals != null && vals.contains(codeValeur)) {
                return "checked";
            }
        }
        return null;
    }
}

