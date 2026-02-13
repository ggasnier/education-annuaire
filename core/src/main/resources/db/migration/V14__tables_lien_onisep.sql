CREATE TABLE formations_onisep
(
    onisep_id INTEGER      NOT NULL,
    clef      VARCHAR(255) NOT NULL,
    valeur    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_formations_onisep PRIMARY KEY (onisep_id, clef, valeur)
);