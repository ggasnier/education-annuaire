-- UTF-8 partout
SET client_encoding = 'UTF8';

-- contrats
CREATE TABLE contrats
(
    code       BPCHAR(2)                   NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_contrats PRIMARY KEY (code)
);

--natures
CREATE TABLE natures
(
    code       BPCHAR(3)                   NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    nom_court  VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_natures PRIMARY KEY (code)
);

--etablissements
CREATE TABLE etablissements
(
    uai            BPCHAR(8)                   NOT NULL,
    siret          BPCHAR(14),
    nda            BPCHAR(11),
    nom            VARCHAR(255)                NOT NULL,
    actif          BOOLEAN,
    date_ouverture date,
    date_fermeture date,
    code_nature    BPCHAR(3),
    code_contrat   BPCHAR(2),
    adresse        VARCHAR(50),
    complement     VARCHAR(50),
    code_postal    BPCHAR(5),
    code_commune   BPCHAR(5),
    sources        VARCHAR(100),
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements PRIMARY KEY (uai)
);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_communes FOREIGN KEY (code_commune) REFERENCES communes (code);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_contrats FOREIGN KEY (code_contrat) REFERENCES contrats (code);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_natures FOREIGN KEY (code_nature) REFERENCES natures (code);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_organismes FOREIGN KEY (nda) REFERENCES organismes (nda);
