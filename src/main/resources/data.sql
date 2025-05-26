--Pays
INSERT INTO public.pays (code, nom)
VALUES ('FR', 'France');

--Régions
INSERT INTO public.regions(code, nom)
VALUES ('11', 'Ile-de-France');

--Académies
INSERT INTO public.academies(code, nom)
VALUES ('01', 'Paris');

-- Départements
INSERT INTO public.departements(code, nom, ACADEMIE_CODE, REGION_CODE)
VALUES ('75', 'Ile de France', '01', '11');

--Communes
INSERT INTO public.COMMUNES(DEPARTEMENT_CODE, PAYS_CODE, CODE, NOM)
VALUES ('75', 'FR', '75107', 'Paris 7e');

--Nature
INSERT INTO public.natures (code, nom, nom_court)
VALUES ('302', 'Lycée', null);