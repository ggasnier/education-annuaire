CREATE TABLE rome_savoirs
(
    code           INTEGER                     NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    nom            VARCHAR(255),
    categorie      VARCHAR(255),
    sous_categorie VARCHAR(255),
    transition_eco CHAR(1),
    code_rubrique  INTEGER                     NOT NULL,
    CONSTRAINT pk_rome_savoirs PRIMARY KEY (code)
);