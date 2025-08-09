SET client_encoding = 'UTF8';

DROP TYPE IF EXISTS resultat_recherche CASCADE;

CREATE TYPE resultat_recherche AS
(
    id        UUID,
    nom       VARCHAR,
    categorie CHAR(3),
    infos     JSONB,
    score     REAL,
    total     BIGINT
);

CREATE OR REPLACE FUNCTION recherche_resultats(
    categories TEXT[] DEFAULT NULL,
    filtres JSONB DEFAULT '{}'::jsonb,
    texte_recherche TEXT DEFAULT NULL,
    page INT DEFAULT 1
)
    RETURNS SETOF resultat_recherche
    LANGUAGE plpgsql
AS
$$
DECLARE
    requete    TEXT;
    conditions TEXT := 'WHERE true';
    offset_val INT  := (page - 1) * 20; -- Page size fixée à 20
    cle        TEXT;
    valeurs    TEXT[];
BEGIN
    -- Boucle sur les filtres dynamiques JSONB
    --v1
    --FOR cle, valeur IN
    --    SELECT key, value::TEXT FROM jsonb_each_text(filtres)
    --LOOP
    --    conditions := conditions || format(' AND infos ->> %L = %L', cle, valeur);
    --END LOOP;

    IF categories IS NOT NULL AND array_length(categories, 1) > 0 THEN
        conditions := conditions || format(' AND categorie = ANY (ARRAY[%s])',
                                           array_to_string(
                                                   ARRAY(SELECT quote_literal(val) FROM unnest(categories) AS val),
                                                   ','));
    END IF;

    --v2
    FOR cle, valeurs IN
        SELECT key, array_agg(value::TEXT)
        FROM (SELECT key, jsonb_array_elements_text(value) AS value
              FROM jsonb_each(filtres)) sub
        GROUP BY key
        LOOP
            IF cle LIKE 'list_%' THEN
                conditions := conditions || format(' AND infos -> %L ?| ARRAY[%s]',
                                                   cle, array_to_string(
                                                           ARRAY(SELECT quote_literal(v) FROM unnest(valeurs) AS v),
                                                           ','));
            ELSE
                conditions := conditions || format(' AND infos ->> %L = ANY (ARRAY[%s]::text[])',
                                                   cle, array_to_string(
                                                           ARRAY(SELECT quote_literal(v) FROM unnest(valeurs) AS v),
                                                           ','));
            END IF;
        END LOOP;

    -- Construction de la requête
    requete := 'SELECT d.id, d.nom, d.categorie, d.infos, ';

    IF texte_recherche IS NOT NULL AND texte_recherche <> '' THEN
        requete := requete ||
                   'ts_rank(d.tsv, websearch_to_tsquery(''french'', unaccent(replace(' ||
                   quote_literal(texte_recherche) || ' , ''-'', '' '')))) as score, ';

        conditions := conditions || ' AND d.tsv @@ websearch_to_tsquery(''french'', unaccent(replace(' ||
                      quote_literal(texte_recherche) || ' , ''-'', '' ''))) ';
    ELSE
        requete := requete || '0.0::REAL AS score, ';
    END IF;

    -- Ajout du total_count et du tri/pagination
    requete := requete || 'COUNT(*) OVER() AS total FROM documents d '
                   || conditions || format(' ORDER BY score DESC, id DESC LIMIT 25 OFFSET %s', offset_val);


    RAISE NOTICE 'Requête exécutée : %', requete;

    RETURN QUERY EXECUTE requete;
END;
$$;