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
    secteur        BPCHAR(2)                   NULL,
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

-- specialites
CREATE TABLE etablissements_specialites
(
    uai        BPCHAR(8)                   NOT NULL,
    specialite VARCHAR(15)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_specialites PRIMARY KEY (uai, specialite)
);

ALTER TABLE etablissements_specialites
    ADD CONSTRAINT fk_specialites_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- sports
CREATE TABLE etablissements_sports
(
    uai        CHAR(8)                     NOT NULL,
    sport      VARCHAR(50)                 NOT NULL,
    categorie  CHAR(2)                     NOT NULL,
    sources    varchar(50)                 NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sports PRIMARY KEY (uai, sport, categorie)
);

ALTER TABLE etablissements_sports
    ADD CONSTRAINT fk_sports_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- contacts
CREATE TABLE etablissements_contacts
(
    uai        CHAR(8)                     NOT NULL,
    contact    VARCHAR(10)                 NOT NULL,
    valeur     VARCHAR(255)                NOT NULL,
    sources    varchar(50)                 NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_contacts PRIMARY KEY (uai, contact, valeur)
);

ALTER TABLE etablissements_contacts
    ADD CONSTRAINT fk_contacts_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- options (fourre tout)
CREATE TABLE etablissements_options
(
    uai        BPCHAR(8)                   NOT NULL,
    option     VARCHAR(255)                NOT NULL,
    sources    varchar(50)                 NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_options PRIMARY KEY (uai, option)
);

ALTER TABLE etablissements_options
    ADD CONSTRAINT fk_options_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- langues
CREATE TABLE etablissements_langues
(
    uai          CHAR(8)                     NOT NULL,
    langue       CHAR(2)                     NOT NULL,
    categorie    CHAR(2)                     NOT NULL,
    enseignement CHAR(3)                     NOT NULL,
    sources      varchar(50)                 NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_langues PRIMARY KEY (uai, langue, categorie, enseignement)
);

ALTER TABLE etablissements_langues
    ADD CONSTRAINT fk_langues_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- metadata par annee
CREATE TABLE etablissements_metadatas
(
    uai        CHAR(8)                     NOT NULL,
    annee      INTEGER                     NOT NULL,
    metadatas  JSONB,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_metadatas PRIMARY KEY (uai, annee)
);

ALTER TABLE etablissements_metadatas
    ADD CONSTRAINT FK_METADATAS_ETABLISSEMENTS FOREIGN KEY (uai) REFERENCES etablissements (uai);