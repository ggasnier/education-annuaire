-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_sports
(
    uai        CHAR(8)                     NOT NULL,
    sport      VARCHAR(255)                NOT NULL,
    categorie  CHAR(2)                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sports PRIMARY KEY (uai, sport, categorie)
);

ALTER TABLE etablissements_sports
    ADD CONSTRAINT FK_SPORTS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);