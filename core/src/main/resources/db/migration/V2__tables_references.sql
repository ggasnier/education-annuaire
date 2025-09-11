-- UTF-8 partout
SET client_encoding = 'UTF8';

-- les tables de référence

-- pays
CREATE TABLE pays
(
    code VARCHAR(2)  NOT NULL,
    nom  VARCHAR(50) NOT NULL,
    CONSTRAINT pk_pays PRIMARY KEY (code)
);

-- regions
CREATE TABLE regions
(
    code       VARCHAR(2)                  NOT NULL,
    nom        VARCHAR(50)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_regions PRIMARY KEY (code)
);

-- academies
CREATE TABLE academies
(
    code       VARCHAR(2)                  NOT NULL,
    nom        VARCHAR(100)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_academies PRIMARY KEY (code)
);

-- departements
CREATE TABLE departements
(
    code          VARCHAR(3)                  NOT NULL,
    nom           VARCHAR(50)                 NOT NULL,
    code_region   VARCHAR(2),
    code_academie VARCHAR(2),
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_departements PRIMARY KEY (code)
);

ALTER TABLE departements
    ADD CONSTRAINT fk_departements_academies FOREIGN KEY (code_academie) REFERENCES academies (code);

ALTER TABLE departements
    ADD CONSTRAINT FK_departements_regions FOREIGN KEY (code_region) REFERENCES regions (code);

-- communes
CREATE TABLE communes
(
    code             VARCHAR(5)                  NOT NULL,
    nom              VARCHAR(255)                NOT NULL,
    code_pays        VARCHAR(2),
    code_departement VARCHAR(3),
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_communes PRIMARY KEY (code)
);

ALTER TABLE communes
    ADD CONSTRAINT fk_communes_departements FOREIGN KEY (code_departement) REFERENCES departements (code);

ALTER TABLE communes
    ADD CONSTRAINT fk_communes_pays FOREIGN KEY (code_pays) REFERENCES pays (code);

