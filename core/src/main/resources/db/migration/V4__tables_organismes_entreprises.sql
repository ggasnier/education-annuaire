-- UTF-8 partout
SET client_encoding = 'UTF8';

-- groupes
CREATE TABLE formations_specialites_groupes
(
    code       CHAR(3)                     NOT NULL,
    nom        VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_formations_specialites_groupes PRIMARY KEY (code)
);

-- references formations
CREATE TABLE formations_specialites
(
    code        CHAR(4)                     NOT NULL,
    nom         VARCHAR(255),
    groupe_code CHAR(3),
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_formations_specialites PRIMARY KEY (code)
);

ALTER TABLE formations_specialites
    ADD CONSTRAINT fk_formations_specialites_groupes FOREIGN KEY (groupe_code) REFERENCES formations_specialites_groupes (code);

-- organismes de formation
CREATE TABLE organismes
(
    nda                                    CHAR(11)                    NOT NULL,
    nom                                    VARCHAR(255),
    siret                                  CHAR(14),
    adresse                                VARCHAR(50),
    code_postal                            VARCHAR(5),
    code_commune                           VARCHAR(10),
    actions_de_formation                   BOOLEAN,
    bilans_de_competences                  BOOLEAN,
    validation_acquis_experience           BOOLEAN,
    actions_de_formation_par_apprentissage BOOLEAN,
    certification_qualiopi                 BOOLEAN,
    date_derniere_declaration              date,
    date_debut                             date,
    date_fin                               date,
    created_at                             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_organismes PRIMARY KEY (nda)
);

CREATE TABLE organismes_groupes
(
    groupe_code CHAR(3)  NOT NULL,
    nda         CHAR(11) NOT NULL
);

ALTER TABLE organismes_groupes
    ADD CONSTRAINT uc_organismes_groupes UNIQUE (nda, groupe_code);

ALTER TABLE organismes
    ADD CONSTRAINT fk_organismes_communes FOREIGN KEY (code_commune) REFERENCES communes (code);

ALTER TABLE organismes_groupes
    ADD CONSTRAINT fk_organismes_groupes_groupes FOREIGN KEY (groupe_code) REFERENCES formations_specialites_groupes (code);

ALTER TABLE organismes_groupes
    ADD CONSTRAINT fk_organismes_groupes_organismes FOREIGN KEY (nda) REFERENCES organismes (nda);

CREATE TABLE entreprises
(
    siret      CHAR(14)                    NOT NULL,
    nom        VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_entreprises PRIMARY KEY (siret)
);