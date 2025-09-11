-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE etablissements_options
(
    uai        VARCHAR(8)                  NOT NULL,
    option     VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_options PRIMARY KEY (uai, option)
);

ALTER TABLE etablissements_options
    ADD CONSTRAINT FK_OPTIONS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);