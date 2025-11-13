CREATE TABLE etablissements_metadatas
(
    etablissement_id UUID                        NOT NULL,
    metadatas        JSONB,
    annee            INTEGER                     NOT NULL,
    uai              CHAR(8)                     NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_metadatas PRIMARY KEY (annee, uai)
);

ALTER TABLE etablissements_metadatas
    ADD CONSTRAINT FK_METADATAS_ETABLISSEMENTS FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);