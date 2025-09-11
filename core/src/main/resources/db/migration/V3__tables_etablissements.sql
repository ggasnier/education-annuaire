-- UTF-8 partout
SET client_encoding = 'UTF8';

-- les tables pour les établissements

-- contrats
CREATE TABLE contrats
(
    code       VARCHAR(2)                  NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_contrats PRIMARY KEY (code)
);

--natures
CREATE TABLE natures
(
    code       VARCHAR(3)                  NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    nom_court  VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_natures PRIMARY KEY (code)
);

--etablissements
CREATE TABLE etablissements
(
    uai            VARCHAR(8)                  NOT NULL,
    nom            VARCHAR(255)                NOT NULL,
    siret          VARCHAR(14),
    etat           VARCHAR(1),
    date_ouverture date,
    date_fermeture date,
    code_nature    VARCHAR(3),
    code_contrat   VARCHAR(2),
    adresse        VARCHAR(50),
    complement     VARCHAR(50),
    code_postal    VARCHAR(5),
    code_commune   VARCHAR(5),
    informations   JSONB,
    sources        VARCHAR(50),
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements PRIMARY KEY (uai)
);

ALTER TABLE etablissements
    ADD CONSTRAINT FK_ETABLISSEMENTS_COMMUNES FOREIGN KEY (code_commune) REFERENCES communes (code);

ALTER TABLE etablissements
    ADD CONSTRAINT FK_ETABLISSEMENTS_CONTRATS FOREIGN KEY (code_contrat) REFERENCES contrats (code);

ALTER TABLE etablissements
    ADD CONSTRAINT FK_ETABLISSEMENTS_NATURES FOREIGN KEY (code_nature) REFERENCES natures (code);

--ips
CREATE TABLE etablissements_ips
(
    uai        VARCHAR(255)                NOT NULL,
    annee      INTEGER                     NOT NULL,
    categorie  VARCHAR(1),
    indice     DOUBLE PRECISION,
    ecart_type DOUBLE PRECISION,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_ips PRIMARY KEY (uai, annee)
);

ALTER TABLE etablissements_ips
    ADD CONSTRAINT FK_IPS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);

--etp
CREATE TABLE etablissements_etp
(
    uai            VARCHAR(8) NOT NULL,
    anne           INTEGER    NOT NULL,
    etp            DOUBLE PRECISION,
    etp_enseignant DOUBLE PRECISION,
    etp_personnel  DOUBLE PRECISION,
    CONSTRAINT pk_etp PRIMARY KEY (uai, anne)
);

ALTER TABLE etablissements_etp
    ADD CONSTRAINT FK_ETP_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);

--specialites
CREATE TABLE etablissements_specialites
(
    uai        VARCHAR(8)                  NOT NULL,
    specialite VARCHAR(15)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_specialites PRIMARY KEY (uai, specialite)
);

ALTER TABLE etablissements_specialites
    ADD CONSTRAINT FK_SPECIALITES_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);