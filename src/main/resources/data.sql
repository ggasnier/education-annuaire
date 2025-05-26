--Pays
INSERT INTO public.pays (code, nom)
VALUES ('FR', 'France');

--Régions (TODO)
INSERT INTO public.regions(code, nom)
VALUES ('11', 'Ile-de-France');

--Académies (TODO)
INSERT INTO public.academies(code, nom)
VALUES ('01', 'Paris');

-- Départements (TODO)
INSERT INTO public.departements(code, nom, ACADEMIE_CODE, REGION_CODE)
VALUES ('75', 'Ile de France', '01', '11');

--Communes (TODO via API)
INSERT INTO public.communes(DEPARTEMENT_CODE, PAYS_CODE, CODE, NOM)
VALUES ('75', 'FR', '75107', 'Paris 7e');

--Contrats
INSERT INTO public.contrats (code, nom)
VALUES ('10', 'Hors contrat');
INSERT INTO public.contrats (code, nom)
VALUES ('20', 'Contrat simple pour toutes les classes');
INSERT INTO public.contrats (code, nom)
VALUES ('21', 'Contrat simple pour une partie des classes');
INSERT INTO public.contrats (code, nom)
VALUES ('30', 'Contrat d''association pour toutes les classes');
INSERT INTO public.contrats (code, nom)
VALUES ('31', 'Contrat d''association pour une partie des classes');
INSERT INTO public.contrats (code, nom)
VALUES ('40', 'Contrats simple et d''association pour toutes les classes');
INSERT INTO public.contrats (code, nom)
VALUES ('41', 'Contrats simple et d''association pour une partie des classes');
INSERT INTO public.contrats (code, nom)
VALUES ('99', 'Publique');
INSERT INTO public.contrats (code, nom)
VALUES ('**', 'Quel que soit le contrat');
INSERT INTO public.contrats (code, nom)
VALUES ('!!', 'Inconnu du gestionnaire');
INSERT INTO public.contrats (code, nom)
VALUES ('50', 'Reconnu par l''Etat');
INSERT INTO public.contrats (code, nom)
VALUES ('60', 'Sous contrat établissement agricole');

--Natures (TODO)
INSERT INTO public.natures (code, nom, nom_court)
VALUES ('302', 'Lycée', null);