-- UTF-8 partout
SET client_encoding = 'UTF8';

--------------------
-- RNCP
-- RS
--------------------
CREATE TABLE certifications
(
    code       VARCHAR(10)                 NOT NULL,
    nom        VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_certifications PRIMARY KEY (code)
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
    certification_code VARCHAR(255) NOT NULL,
    rome_code          CHAR(5)      NOT NULL,
    CONSTRAINT pk_certifications_romes PRIMARY KEY (certification_code, rome_code)
);

ALTER TABLE certifications_romes
    ADD CONSTRAINT fk_certifications_romes_certification FOREIGN KEY (certification_code) REFERENCES certifications (code);

ALTER TABLE certifications_romes
    ADD CONSTRAINT fk_certifications_romes_romes FOREIGN KEY (rome_code) REFERENCES romes (code);
