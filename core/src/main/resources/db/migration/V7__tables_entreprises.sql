CREATE TABLE entreprises
(
    siret      CHAR(14)                    NOT NULL,
    nom        VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_entreprises PRIMARY KEY (siret)
);