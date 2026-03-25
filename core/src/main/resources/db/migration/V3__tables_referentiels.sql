-- UTF-8 partout
SET client_encoding = 'UTF8';

--------------------
-- RNCP
-- RS
--------------------
CREATE TABLE certifications
(
    code                           BPCHAR(10)                  NOT NULL,
    nom                            TEXT,
    actif                          BOOLEAN,
    nouvelle_certification_code    BPCHAR(10),
    etat                           CHAR(1),
    typologie_diplome              VARCHAR(255),
    niveau                         INTEGER                     NOT NULL,
    activites_visees               TEXT,
    capacites_attestees            TEXT,
    secteurs_activite              TEXT,
    type_emploi_accessibles        TEXT,
    prerequis_entree_formation     TEXT,
    reglementations_activites      TEXT,
    publication_decret             TEXT,
    objectifs_contexte             TEXT,
    accessible_nouvelle_caledonie  BOOLEAN,
    accessible_polynesie_francaise BOOLEAN,
    url                            TEXT,
    created_at                     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_certifications PRIMARY KEY (code)
);


ALTER TABLE certifications
    ADD CONSTRAINT FK_CERTIFICATIONS_ON_NOUVELLECERTIFICATION_CODE FOREIGN KEY (nouvelle_certification_code) REFERENCES certifications (code);


--------------------
-- Métiers
--------------------
CREATE TABLE rome_metiers
(
    code               BPCHAR(5)                   NOT NULL,
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

ALTER TABLE rome_metiers
    ADD CONSTRAINT fk_metiers_parent FOREIGN KEY (metier_parent_code) REFERENCES rome_metiers (code);

CREATE TABLE certifications_metiers
(
    certification_code BPCHAR(10) NOT NULL,
    metier_code        BPCHAR(5)  NOT NULL
);

ALTER TABLE certifications_metiers
    ADD CONSTRAINT fk_certifications_metiers_c FOREIGN KEY (certification_code) REFERENCES certifications (code);

ALTER TABLE certifications_metiers
    ADD CONSTRAINT fk_certifications_metiers_m FOREIGN KEY (metier_code) REFERENCES rome_metiers (code);

--------------------
-- Domaine
--------------------
CREATE TABLE rome_domaines
(
    code       INTEGER                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nom        VARCHAR(255),
    CONSTRAINT pk_rome_domaines PRIMARY KEY (code)
);

--------------------
-- Enjeux
--------------------
CREATE TABLE rome_enjeux
(
    code         BPCHAR(2)                   NOT NULL,
    nom          VARCHAR(255),
    domaine_code INTEGER,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_rome_enjeux PRIMARY KEY (code)
);

ALTER TABLE rome_enjeux
    ADD CONSTRAINT FK_ENJEUX_DOMAINES FOREIGN KEY (domaine_code) REFERENCES rome_domaines (code);

--------------------
-- Objectifs
--------------------
CREATE TABLE rome_objectifs
(
    code       BPCHAR(3)                   NOT NULL,
    nom        VARCHAR(255),
    enjeu_code BPCHAR(2),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_rome_objectifs PRIMARY KEY (code)
);

ALTER TABLE rome_objectifs
    ADD CONSTRAINT FK_OBJECTIFS_ENJEUX FOREIGN KEY (enjeu_code) REFERENCES rome_enjeux (code);

--------------------
-- Macros compétences
--------------------
CREATE TABLE rome_macros_competences
(
    code          BPCHAR(5)                   NOT NULL,
    nom           VARCHAR(255),
    objectif_code BPCHAR(3),
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_rome_macros_competences PRIMARY KEY (code)
);

ALTER TABLE rome_macros_competences
    ADD CONSTRAINT FK_MACROS_COMPETENCES_ENJEUX FOREIGN KEY (objectif_code) REFERENCES rome_objectifs (code);

--------------------
-- Compétences
--------------------
CREATE TABLE rome_competences
(
    code                  INTEGER                     NOT NULL,
    nom                   VARCHAR(255),
    macro_competence_code BPCHAR(5),
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_rome_competences PRIMARY KEY (code)
);

ALTER TABLE rome_competences
    ADD CONSTRAINT FK_COMPETENCES_MACROS FOREIGN KEY (macro_competence_code) REFERENCES rome_macros_competences (code);


CREATE TABLE rome_metiers_competences
(
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    coeur_metier    BPCHAR(1),
    metier_code     BPCHAR(5)                   NOT NULL,
    competence_code INTEGER                     NOT NULL,
    CONSTRAINT pk_rome_metiers_competences PRIMARY KEY (metier_code, competence_code)
);

--------------------
-- Liaison Métiers - Compétences
--------------------
ALTER TABLE rome_metiers_competences
    ADD CONSTRAINT FK_METIERS_COMPETENCES_C FOREIGN KEY (competence_code) REFERENCES rome_competences (code);

ALTER TABLE rome_metiers_competences
    ADD CONSTRAINT FK_METIERS_COMPETENCES_M FOREIGN KEY (metier_code) REFERENCES rome_metiers (code);