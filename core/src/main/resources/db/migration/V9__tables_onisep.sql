-- UTF-8 partout
SET client_encoding = 'UTF8';

CREATE TABLE onisep_liens
(
    onisep_type VARCHAR(8)    NOT NULL,
    onisep_id   INTEGER       NOT NULL,
    lien_type   VARCHAR(8)    NOT NULL,
    lien_id     VARCHAR(255)  NOT NULL,
    CONSTRAINT pk_onisep_liens PRIMARY KEY (onisep_type, onisep_id, lien_type, lien_id)
);