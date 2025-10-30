CREATE TABLE certifications
(
    id              UUID                        NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    code_rncp       VARCHAR(10),
    code_rs         VARCHAR(10),
    code_cpf        VARCHAR(10),
    code_certifinfo VARCHAR(10),
    nom             VARCHAR(255),
    CONSTRAINT pk_certifications PRIMARY KEY (id)
);

CREATE TABLE romes
(
    code       CHAR(5)                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nom        VARCHAR(255),
    CONSTRAINT pk_romes PRIMARY KEY (code)
);

CREATE TABLE certifications_romes
(
    certification_id UUID    NOT NULL,
    rome_code        CHAR(5) NOT NULL,
    CONSTRAINT pk_certifications_romes PRIMARY KEY (certification_id, rome_code)
);

ALTER TABLE certifications_romes
    ADD CONSTRAINT fk_certifications_romes_certification FOREIGN KEY (certification_id) REFERENCES certifications (id);

ALTER TABLE certifications_romes
    ADD CONSTRAINT fk_certifications_romes_romes FOREIGN KEY (rome_code) REFERENCES romes (code);

CREATE TABLE formations
(
    id                    UUID                        NOT NULL,
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nom                   VARCHAR(255)                NOT NULL,
    objectif              TEXT,
    resultats             TEXT,
    contenu               TEXT,
    certifiante           BOOLEAN,
    parcours_de_formation VARCHAR(255),
    code_niveau_entree    INTEGER,
    code_niveau_sortie    INTEGER,
    etablissement_id      UUID,
    identifiant_module    TEXT,
    positionnement        INTEGER,
    onisep_id             INTEGER,
    CONSTRAINT pk_formations PRIMARY KEY (id)
);

ALTER TABLE formations
    ADD CONSTRAINT fk_formations_etablissements FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);


CREATE TABLE formations_certifications
(
    formation_entity_id UUID NOT NULL,
    certifications_id   UUID NOT NULL
);

ALTER TABLE formations_certifications
    ADD CONSTRAINT uc_formations_certifications_certifications UNIQUE (certifications_id);

ALTER TABLE formations_certifications
    ADD CONSTRAINT fk_forcer_on_certification_entity FOREIGN KEY (certifications_id) REFERENCES certifications (id);

ALTER TABLE formations_certifications
    ADD CONSTRAINT fk_forcer_on_formation_entity FOREIGN KEY (formation_entity_id) REFERENCES formations (id);

-- actions de formations
CREATE TABLE formations_actions
(
    id                                UUID                        NOT NULL,
    created_at                        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    formation_id                      UUID                        NOT NULL,
    rythme_formation                  TEXT,
    code_public_vise                  CHAR(5),
    info_public_vise                  VARCHAR(250),
    niveau_entree_obligatoire         BOOLEAN,
    modalites_alternance              VARCHAR(255),
    modalites_enseignement            VARCHAR(255),
    conditions_specifiques            VARCHAR(255),
    prise_en_charge_frais_possible    VARCHAR(255),
    etablissement_id                  UUID                        NOT NULL,
    modalites_entrees_sorties         VARCHAR(255),
    url_action                        VARCHAR(255),
    duree_cycle                       VARCHAR(255),
    session                           VARCHAR(255),
    adresse_information               VARCHAR(255),
    date_information                  VARCHAR(255),
    restauration                      VARCHAR(255),
    hebergement                       VARCHAR(255),
    transport                         VARCHAR(255),
    acces_handicapes                  VARCHAR(255),
    langue_formation                  VARCHAR(255),
    modalites_recrutement             VARCHAR(255),
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
    CONSTRAINT pk_formations_actions PRIMARY KEY (id)
);

ALTER TABLE formations_actions
    ADD CONSTRAINT FK_ACTIONS_FORMATIONS FOREIGN KEY (formation_id) REFERENCES formations (id);

ALTER TABLE formations_actions
    ADD CONSTRAINT FK_ACTIONS_FORMATIONS_ETABLISSEMENTS FOREIGN KEY (etablissement_id) REFERENCES etablissements (id);

