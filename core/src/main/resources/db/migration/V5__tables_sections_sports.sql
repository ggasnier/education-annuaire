-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_sports_etudes
(
    uai        VARCHAR(8)                  NOT NULL,
    sport      VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sports_etudes PRIMARY KEY (uai, sport)
);

ALTER TABLE etablissements_sports_etudes
    ADD CONSTRAINT FK_OPTIONS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);

CREATE TABLE etablissements_sections_sportives
(
    uai        VARCHAR(8)                  NOT NULL,
    sport      VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sections_sportives PRIMARY KEY (uai, sport)
);

ALTER TABLE etablissements_sections_sportives
    ADD CONSTRAINT FK_OPTIONS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);