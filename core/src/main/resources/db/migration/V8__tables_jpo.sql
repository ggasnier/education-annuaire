-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_jpo
(
    uai         CHAR(8)                     NOT NULL,
    date_debut  date                        NOT NULL,
    date_fin    date                        NOT NULL,
    heure_debut time WITHOUT TIME ZONE,
    heure_fin   time WITHOUT TIME ZONE,
    commentaire TEXT,
    sources     VARCHAR(50)                 NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_jpo PRIMARY KEY (uai, date_debut, date_fin)
);

ALTER TABLE etablissements_jpo
    ADD CONSTRAINT fk_jpo_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);