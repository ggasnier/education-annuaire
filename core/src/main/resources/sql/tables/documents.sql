-- UTF-8 partout
SET client_encoding = 'UTF8';
--
-- table
--
DROP TABLE IF EXISTS documents;
--
CREATE TABLE documents
(
    id         UUID         NOT NULL,
    nom        VARCHAR(255) NOT NULL,
    categorie  CHAR(20)     NOT NULL,
    infos      JSONB,
    tsv        TSVECTOR,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    constraint documents_pkey primary key (id)
);
--
-- index des catégories
--
CREATE INDEX idx_documents_categorie ON documents (categorie);
--
-- index full text
--
CREATE INDEX idx_documents_tsv ON documents USING GIN (tsv);
--
-- index pour jsonb
--
CREATE INDEX idx_documents_infos ON documents USING GIN (infos);
--
-- colonnes calculées
--
ALTER TABLE documents
    ADD COLUMN code_niveau TEXT GENERATED ALWAYS AS (infos ->> 'code_niveau') STORED;
CREATE INDEX idx_documents_code_niveau ON documents (code_niveau);
--
-- XXX
--
ALTER TABLE documents
    ADD COLUMN code_nature TEXT GENERATED ALWAYS AS (infos ->> 'code_nature') STORED;
CREATE INDEX idx_documents_code_nature ON documents (code_nature);
--
-- trigger
--
CREATE OR REPLACE FUNCTION trigger_set_updated_at()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_updated_at
    BEFORE UPDATE
    ON documents
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated_at();