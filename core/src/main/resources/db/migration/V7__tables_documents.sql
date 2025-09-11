-- UTF-8 partout
SET client_encoding = 'UTF8';

-- table des documents pour la recherche
CREATE TABLE documents (
    id uuid NOT NULL,
    nom varchar(255) NOT NULL,
    categorie varchar(255) NOT NULL,
    key VARCHAR(255) NOT NULL,
    informations JSONB,
    tsv tsvector NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_documents PRIMARY KEY (id)
);
CREATE INDEX idx_documents_categorie ON documents USING btree (categorie);
CREATE INDEX idx_documents_tsv ON documents USING gin (tsv);

CREATE TABLE documents_values (
    document_id uuid NOT NULL,
    key varchar(255) NOT NULL,
    value varchar(255) NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_documents_values PRIMARY KEY (document_id, key, value),
    CONSTRAINT documents_values_document_id_fkey FOREIGN KEY (document_id) REFERENCES documents(id) ON DELETE CASCADE
);
CREATE INDEX idx_documents_values_doc ON documents_values USING btree (document_id);
CREATE INDEX idx_documents_values_field ON documents_values USING btree (key, value);
CREATE INDEX idx_documents_values_field_pair ON documents_values USING btree (key, value);
CREATE INDEX idx_documents_values_fieldname_value_doc ON documents_values USING btree (key, value, document_id);
