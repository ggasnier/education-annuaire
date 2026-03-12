-- UTF-8 partout
SET client_encoding = 'UTF8';

--------------------
-- RNCP
-- RS
--------------------
CREATE TABLE certifications
(
    code       VARCHAR(10)                 NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_certifications PRIMARY KEY (code)
);

CREATE TABLE metiers
(
    code               CHAR(5)                     NOT NULL,
    code_ogr           INTEGER                     NOT NULL,
    nom                VARCHAR(255),
    transition_eco     BPCHAR(1),
    transition_num     BPCHAR(1),
    transition_demo    BPCHAR(1),
    emploi_reglemente  BPCHAR(1),
    emploi_cadre       BPCHAR(1),
    metier_parent_code BPCHAR(5),
    metadatas          JSONB,
    created_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_metiers PRIMARY KEY (code)
);

ALTER TABLE metiers
    ADD CONSTRAINT fk_metiers_parent FOREIGN KEY (metier_parent_code) REFERENCES metiers (code);
