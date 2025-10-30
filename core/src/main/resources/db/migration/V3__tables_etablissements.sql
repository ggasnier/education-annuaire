-- UTF-8 partout
SET client_encoding = 'UTF8';

-- les tables pour les établissements

-- contrats
CREATE TABLE contrats
(
    code       BPCHAR(2)                    NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_contrats PRIMARY KEY (code)
);

--natures
CREATE TABLE natures
(
    code       BPCHAR(3)                    NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    nom_court  VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_natures PRIMARY KEY (code)
);

--etablissements
CREATE TABLE etablissements
(
    id                          UUID                        NOT NULL,
    uai                         BPCHAR(8),
    siret                       BPCHAR(14),
    numero_declaration_activite BPCHAR(11),
    nom                         VARCHAR(255)                NOT NULL,
    actif                       BOOLEAN,
    date_ouverture              date,
    date_fermeture              date,
    code_nature                 BPCHAR(3),
    code_contrat                BPCHAR(2),
    adresse                     VARCHAR(50),
    complement                  VARCHAR(50),
    code_postal                 BPCHAR(5),
    code_commune                BPCHAR(5),
    sources                     VARCHAR(100),
    created_at                  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements PRIMARY KEY (id)
);

CREATE UNIQUE INDEX unique_uai_not_null
    ON etablissements (uai) WHERE uai IS NOT NULL;

ALTER TABLE etablissements
    ADD CONSTRAINT ct_etablissements_UAI_NOT_NULL UNIQUE NULLS NOT DISTINCT (uai);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_communes FOREIGN KEY (code_commune) REFERENCES communes (code);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_contrats FOREIGN KEY (code_contrat) REFERENCES contrats (code);

ALTER TABLE etablissements
    ADD CONSTRAINT fk_etablissements_natures FOREIGN KEY (code_nature) REFERENCES natures (code);

--ips
CREATE TABLE etablissements_ips
(
    uai        BPCHAR(8)                   NOT NULL,
    annee      INTEGER                     NOT NULL,
    etablissement_id UUID                  NOT NULL,
    categorie  BPCHAR(1),
    indice     DOUBLE PRECISION,
    ecart_type DOUBLE PRECISION,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_ips PRIMARY KEY (uai, annee)
);

ALTER TABLE etablissements_ips
    ADD CONSTRAINT fk_ips_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

--etp
CREATE TABLE etablissements_etp
(
    uai            BPCHAR(8)               NOT NULL,
    anne           INTEGER                 NOT NULL,
    etablissement_id UUID                  NOT NULL,
    etp            DOUBLE PRECISION,
    etp_enseignant DOUBLE PRECISION,
    etp_personnel  DOUBLE PRECISION,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etp PRIMARY KEY (uai, anne)
);

ALTER TABLE etablissements_etp
    ADD CONSTRAINT fk_etp_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

--specialites
CREATE TABLE etablissements_specialites
(
    uai        BPCHAR(8)                   NOT NULL,
    specialite VARCHAR(15)                 NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_specialites PRIMARY KEY (uai, specialite)
);

ALTER TABLE etablissements_specialites
    ADD CONSTRAINT fk_specialites_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

CREATE TABLE etablissements_sections_internationales
(
    uai        BPCHAR(8)                   NOT NULL,
    section    VARCHAR(255)                NOT NULL,
    niveau     VARCHAR(255)                NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sections_internationales PRIMARY KEY (uai, section, niveau)
);

ALTER TABLE etablissements_sections_internationales
    ADD CONSTRAINT fk_IPS_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

CREATE TABLE etablissements_sports_etudes
(
    uai        BPCHAR(8)                   NOT NULL,
    sport      VARCHAR(255)                NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sports_etudes PRIMARY KEY (uai, sport)
);

ALTER TABLE etablissements_sports_etudes
    ADD CONSTRAINT fk_sports_etudes_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

CREATE TABLE etablissements_sections_sportives
(
    uai        BPCHAR(8)                   NOT NULL,
    sport      VARCHAR(255)                NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_sections_sportives PRIMARY KEY (uai, sport)
);

ALTER TABLE etablissements_sections_sportives
    ADD CONSTRAINT fk_sections_sportives_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

CREATE TABLE etablissements_options
(
    uai        BPCHAR(8)                   NOT NULL,
    option     VARCHAR(255)                NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_options PRIMARY KEY (uai, option)
);

ALTER TABLE etablissements_options
    ADD CONSTRAINT fk_options_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

CREATE TABLE etablissements_langues
(
    uai          BPCHAR(8)                 NOT NULL,
    langue       VARCHAR(2)                NOT NULL,
    enseignement VARCHAR(3)                NOT NULL,
    etablissement_id UUID                  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_etablissements_langues PRIMARY KEY (uai, langue, enseignement)
);

ALTER TABLE etablissements_langues
    ADD CONSTRAINT fk_langues_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);