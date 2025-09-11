-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_sections_internationales
(
    uai        VARCHAR(8)                  NOT NULL,
    section    VARCHAR(255)                NOT NULL,
    niveau     VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sections_internationales PRIMARY KEY (uai, section, niveau)
);

ALTER TABLE etablissements_sections_internationales
    ADD CONSTRAINT FK_IPS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);