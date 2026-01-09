-- UTF-8 partout
SET client_encoding = 'UTF8';

ALTER TABLE etablissements_contacts
    ADD sources varchar(50) NULL;

ALTER TABLE etablissements_langues
    ADD sources varchar(50) NULL;

ALTER TABLE etablissements_options
    ADD sources varchar(50) NULL;

ALTER TABLE etablissements_sports
    ADD sources varchar(50) NULL;
