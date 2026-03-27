-- UTF-8 partout
SET client_encoding = 'UTF8';

------------------------------
-- offres de formations
------------------------------
CREATE TABLE formations
(
    id                    BIGINT                      NOT NULL,
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nom                   VARCHAR(255)                NOT NULL,
    objectif              TEXT,
    resultats             TEXT,
    contenu               TEXT,
    certifiante           BOOLEAN,
    code_certification    VARCHAR(10)                 NULL,
    parcours_de_formation INTEGER,
    code_niveau_entree    INTEGER,
    code_niveau_sortie    INTEGER,
    uai                   BPCHAR(8),
    nda                   BPCHAR(11),
    identifiant_module    TEXT,
    positionnement        INTEGER,
    onisep_id             INTEGER,
    parcoursup_id         INTEGER,
    sources               VARCHAR(50)                 NULL,

    CONSTRAINT pk_formations PRIMARY KEY (id)
);

ALTER TABLE formations
    ADD CONSTRAINT fk_formations_certifications FOREIGN KEY (code_certification) REFERENCES certifications (code);

ALTER TABLE formations
    ADD CONSTRAINT fk_formations_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

ALTER TABLE formations
    ADD CONSTRAINT fk_formations_organismes FOREIGN KEY (nda) REFERENCES organismes (nda);

------------------------------
-- actions de formations
------------------------------
CREATE TABLE formations_actions
(
    id                                BIGINT                      NOT NULL,
    formation_id                      BIGINT                      NOT NULL,
    rythme_formation                  TEXT,
    code_public_vise                  CHAR(5),
    info_public_vise                  VARCHAR(250),
    niveau_entree_obligatoire         BOOLEAN,
    modalites_alternance              VARCHAR(255),
    modalites_enseignement            INTEGER,
    conditions_specifiques            TEXT,
    prise_en_charge_frais_possible    BOOLEAN,
    uai                               BPCHAR(8),
    modalites_entrees_sorties         INTEGER,
    url_action                        VARCHAR(255),
    duree_cycle                       INTEGER,
    session                           VARCHAR(255),
    adresse_information               VARCHAR(255),
    date_information                  DATE,
    restauration                      VARCHAR(255),
    hebergement                       VARCHAR(255),
    transport                         VARCHAR(255),
    acces_handicapes                  VARCHAR(255),
    langue_formation                  BPCHAR(2),
    modalites_recrutement             TEXT,
    modalites_pedagogiques            VARCHAR(255),
    code_modalite_pedagogique         VARCHAR(255),
    equipement                        VARCHAR(255),
    frais_restants                    VARCHAR(255),
    code_perimetre_recrutement        VARCHAR(255),
    infos_perimetre_recrutement       VARCHAR(255),
    prix_horairettc                   VARCHAR(255),
    prix_totalttc                     VARCHAR(255),
    duree_indicative                  VARCHAR(255),
    nombre_heures_centre              VARCHAR(255),
    nombre_heures_entreprise          VARCHAR(255),
    nombre_heures_total               VARCHAR(255),
    detail_conditions_prise_en_charge VARCHAR(255),
    conventionnement                  VARCHAR(255),
    duree_conventionnee               VARCHAR(255),
    organisme_formateur               VARCHAR(255),
    organisme_financeur               VARCHAR(255),
    onisep_id                         INTEGER,
    parcoursup_id                     INTEGER,
    sources                           VARCHAR(50)                 NULL,
    created_at                        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_formations_actions PRIMARY KEY (id)
);

ALTER TABLE formations_actions
    ADD CONSTRAINT fk_actions_formations FOREIGN KEY (formation_id) REFERENCES formations (id);

ALTER TABLE formations_actions
    ADD CONSTRAINT fk_actions_formations_etablissements FOREIGN KEY (uai) REFERENCES etablissements (uai);

-- sessions de formations
