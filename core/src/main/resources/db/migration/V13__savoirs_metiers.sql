CREATE TABLE rome_metiers_savoirs
(
    coeur_metier CHAR(1),
    metier_code  CHAR(5) NOT NULL,
    savoir_code  INTEGER NOT NULL,
    CONSTRAINT pk_rome_metiers_savoirs PRIMARY KEY (metier_code, savoir_code)
);

ALTER TABLE rome_metiers_savoirs
    ADD CONSTRAINT FK_ROME_METIERS_SAVOIRS_ON_METIER_CODE FOREIGN KEY (metier_code) REFERENCES rome_metiers (code);

ALTER TABLE rome_metiers_savoirs
    ADD CONSTRAINT FK_ROME_METIERS_SAVOIRS_ON_SAVOIR_CODE FOREIGN KEY (savoir_code) REFERENCES rome_savoirs (code);