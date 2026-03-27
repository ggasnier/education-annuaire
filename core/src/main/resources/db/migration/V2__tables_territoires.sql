-- UTF-8 partout
SET client_encoding = 'UTF8';

-- les tables des territoires

-- pays
CREATE TABLE pays
(
    code BPCHAR(2)   NOT NULL,
    nom  VARCHAR(50) NOT NULL,
    CONSTRAINT pk_pays PRIMARY KEY (code)
);

-- regions
CREATE TABLE regions
(
    code       BPCHAR(2)                   NOT NULL,
    nom        VARCHAR(50)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_regions PRIMARY KEY (code)
);

-- academies
CREATE TABLE academies
(
    code       BPCHAR(2)                   NOT NULL,
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
    code_region   BPCHAR(2),
    code_academie BPCHAR(2),
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_departements PRIMARY KEY (code)
);

ALTER TABLE departements
    ADD CONSTRAINT fk_departements_academies FOREIGN KEY (code_academie) REFERENCES academies (code);

ALTER TABLE departements
    ADD CONSTRAINT fk_departements_regions FOREIGN KEY (code_region) REFERENCES regions (code);

-- communes
CREATE TABLE communes
(
    code             VARCHAR(10)                 NOT NULL,
    nom              VARCHAR(255)                NOT NULL,
    code_pays        BPCHAR(2),
    code_departement BPCHAR(3),
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_communes PRIMARY KEY (code)
);

ALTER TABLE communes
    ADD CONSTRAINT fk_communes_departements FOREIGN KEY (code_departement) REFERENCES departements (code);

ALTER TABLE communes
    ADD CONSTRAINT fk_communes_pays FOREIGN KEY (code_pays) REFERENCES pays (code);

