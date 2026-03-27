-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_identifiants
(
    uai        CHAR(8)                     NOT NULL,
    clef       VARCHAR(8)                  NOT NULL,
    valeur     VARCHAR(50)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_identifiants PRIMARY KEY (uai, clef, valeur)
);

ALTER TABLE etablissements_identifiants
    ADD CONSTRAINT fk_identifiants_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);