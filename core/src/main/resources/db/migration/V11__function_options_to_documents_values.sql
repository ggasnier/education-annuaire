-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE OR REPLACE FUNCTION public.options_to_documents_values()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$function$
DECLARE
    v_doc_id uuid;
BEGIN
    v_doc_id := code_to_uuid(NEW.uai::TEXT);

    INSERT INTO documents_values (document_id, key, value, created_at, updated_at)
    VALUES (v_doc_id, 'OPTION', NEW.option, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT (document_id, key, value) DO UPDATE
        SET updated_at = CURRENT_TIMESTAMP;

    RETURN NULL;
END;
$function$
;

DROP TRIGGER IF EXISTS trg_maj_etablissements_options ON etablissements_options;

CREATE TRIGGER trg_maj_etablissements_options
    AFTER INSERT OR UPDATE
    ON etablissements_options
    FOR EACH ROW
EXECUTE FUNCTION options_to_documents_values();