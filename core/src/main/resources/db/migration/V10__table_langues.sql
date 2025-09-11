-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_langues
(
    uai          VARCHAR(8)                  NOT NULL,
    langue       VARCHAR(2)                  NOT NULL,
    enseignement VARCHAR(3)                  NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_langues PRIMARY KEY (uai, langue, enseignement)
);

ALTER TABLE etablissements_langues
    ADD CONSTRAINT FK_LANGUES_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);