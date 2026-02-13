SET client_encoding = 'UTF8';

CREATE TABLE etablissements_masa
(
    id         CHAR(8)                     NOT NULL,
    uai        CHAR(8)                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_masa PRIMARY KEY (id)
);

ALTER TABLE etablissements_masa
    ADD CONSTRAINT uc_etablissements_masa_uai UNIQUE (uai);

ALTER TABLE etablissements_masa
    ADD CONSTRAINT FK_MASA_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);