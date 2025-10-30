-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE OR REPLACE FUNCTION public.etablissements_to_documents()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$function$
DECLARE
    v_doc_id      uuid;
    v_tsv         tsvector;
    v_nom_commune TEXT;
    v_nom_nature  TEXT;
    v_nom_contrat TEXT;
BEGIN
    v_doc_id := code_to_uuid(NEW.uai::TEXT);

    SELECT nom INTO v_nom_commune FROM communes WHERE code = NEW.code_commune;
    SELECT nom INTO v_nom_nature FROM natures WHERE code = NEW.code_nature;
    SELECT nom INTO v_nom_contrat FROM contrats WHERE code = NEW.code_contrat AND code NOT IN ('!!', '**', '99');

    -- Construction pondérée du tsvector
    v_tsv :=
            setweight(to_tsvector('french', unaccent(coalesce(NEW.nom, ''))), 'A') ||
            setweight(to_tsvector('french', coalesce(NEW.code_postal, '')), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(v_nom_nature, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(v_nom_contrat, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(NEW.adresse, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(NEW.complement, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(v_nom_commune, ''))), 'B') ||
            setweight(to_tsvector('french', coalesce(NEW.uai, '')), 'C') ||
            setweight(to_tsvector('french', coalesce(NEW.siret, '')), 'C');

    -- UPSERT dans documents
    INSERT INTO documents (id, nom, categorie, key, informations, tsv, created_at, updated_at)
    VALUES (v_doc_id,
            NEW.nom,
            'etablissements',
            NEW.uai,
            jsonb_strip_nulls(
                    jsonb_build_object(
                            'Commune', v_nom_commune,
                            'Nature', v_nom_nature,
                            'Contrat', v_nom_contrat
                    )
            ),
            v_tsv,
            CURRENT_TIMESTAMP,
            CURRENT_TIMESTAMP)
    ON CONFLICT (id) DO UPDATE
        SET nom          = EXCLUDED.nom,
            informations = jsonb_strip_nulls(
                    jsonb_build_object(
                            'Commune', v_nom_commune,
                            'Nature', v_nom_nature,
                            'Contrat', v_nom_contrat
                    )
                           ),
            tsv          = EXCLUDED.tsv,
            updated_at   = CURRENT_TIMESTAMP;

    -- Nettoyage des anciennes valeurs
    DELETE
    FROM documents_values
    WHERE document_id = v_doc_id
      AND key IN ('CODE_NATURE', 'CODE_COMMUNE', 'CODE_CONTRAT');

    -- Champs scalaires (uniquement si non NULL)
    INSERT INTO documents_values (document_id, key, value, created_at, updated_at)
    SELECT v_doc_id, key, value, created_at, updated_at
    FROM (VALUES ('CODE_NATURE', NEW.code_nature),
                 ('CODE_COMMUNE', NEW.code_commune),
                 ('CODE_CONTRAT', NEW.code_contrat)) AS t(key, value),
         CURRENT_TIMESTAMP AS created_at,
         CURRENT_TIMESTAMP AS updated_at
    WHERE value IS NOT NULL;

    RETURN NULL;
END;
$function$
;

DROP TRIGGER IF EXISTS trg_maj_etablissements ON etablissements;

CREATE TRIGGER trg_maj_etablissements
    AFTER INSERT OR UPDATE
    ON etablissements
    FOR EACH ROW
EXECUTE FUNCTION etablissements_to_documents();