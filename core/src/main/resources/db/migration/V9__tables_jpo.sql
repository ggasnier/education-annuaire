-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_jpo
(
    uai         CHAR(8)                     NOT NULL,
    date        date                        NOT NULL,
    heure_debut time WITHOUT TIME ZONE,
    heure_fin   time WITHOUT TIME ZONE,
    commentaire TEXT,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_jpo PRIMARY KEY (uai, date)
);

ALTER TABLE etablissements_jpo
    ADD CONSTRAINT FK_JPO_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);