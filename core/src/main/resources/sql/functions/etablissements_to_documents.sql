CREATE OR REPLACE FUNCTION etablissements_to_documents()
    RETURNS trigger AS
$$
DECLARE
    v_infos       JSONB;
    v_tsv         tsvector;
    v_nom_commune TEXT;
    v_nom_nature  TEXT;
    v_nom_contrat TEXT;
BEGIN
    --SELECT nom INTO v_nom_commune FROM communes WHERE code = NEW.code_commune;
    SELECT nom INTO v_nom_nature FROM natures WHERE code = NEW.code_nature;
    SELECT nom INTO v_nom_contrat FROM contrats WHERE code = NEW.code_contrat;

    -- Construction pondérée du tsvector
    v_tsv :=
            setweight(to_tsvector('french', unaccent(coalesce(NEW.nom, ''))), 'A') ||
            setweight(to_tsvector('french', coalesce(NEW.code_postal, '')), 'B') ||
                --setweight(to_tsvector('french', unaccent(coalesce(v_nom_commune, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(v_nom_nature, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(v_nom_contrat, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(NEW.adresse, ''))), 'B') ||
            setweight(to_tsvector('french', unaccent(coalesce(NEW.complement, ''))), 'B') ||
                --setweight(to_tsvector('french', unaccent(coalesce(NEW.education_prioritaire, ''))), 'c') ||
            setweight(to_tsvector('french', coalesce(NEW.uai, '')), 'C') ||
            setweight(to_tsvector('french', coalesce(NEW.siret, '')), 'C');

    -- Création du JSONB d'infos
    v_infos := jsonb_build_object(
            'code_nature', NEW.code_nature,
            'code_commune', NEW.code_commune,
            'code_contrat', NEW.code_contrat
               );

    -- UPSERT dans documents
    INSERT INTO documents (id, nom, categorie, infos, tsv)
    VALUES (code_to_uuid(NEW.uai::TEXT),
            NEW.nom,
            'etablissements',
            v_infos,
            v_tsv)
    ON CONFLICT (id) DO UPDATE
        SET nom   = EXCLUDED.nom,
            infos = EXCLUDED.infos,
            tsv   = EXCLUDED.tsv;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

--trigger
DROP TRIGGER IF EXISTS trg_maj_etablissements ON etablissements;

CREATE TRIGGER trg_maj_etablissements
    AFTER INSERT OR UPDATE
    ON etablissements
    FOR EACH ROW
EXECUTE FUNCTION etablissements_to_documents();