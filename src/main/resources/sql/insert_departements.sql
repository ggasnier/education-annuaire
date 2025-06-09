SET client_encoding = 'UTF8';
-- INSERTION DES DÉPARTEMENTS --
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('130', 'Andorre', '99', '00')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('999', 'Etranger', '99', '00')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('990', 'Autre Pays', '99', '00')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('100', 'France Sans Autre Indication', '99', '99')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('00', '101ème Base Agape', '99', '99')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('971', 'Guadeloupe', '01', '32')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('978', 'Saint-Martin', '00', '32')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('977', 'Saint-Barthélémy', '00', '32')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('20', 'Corse (avant 1976)', '94', '27')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('22', 'Côtes-d''Armor', '53', '14')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('29', 'Finistère', '53', '14')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('35', 'Ille-et-Vilaine', '53', '14')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('49', 'Maine-et-Loire', '52', '17')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('72', 'Sarthe', '52', '17')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('53', 'Mayenne', '52', '17')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('85', 'Vendée', '52', '17')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('37', 'Indre-et-Loire', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('28', 'Eure-et-Loir', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('41', 'Loir-et-Cher', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('36', 'Indre', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('45', 'Loiret', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('18', 'Cher', '24', '18')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('83', 'Var', '93', '23')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('75', 'Paris', '11', '01')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('84', 'Vaucluse', '93', '02')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('13', 'Bouches-du-Rhône', '93', '02')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('05', 'Hautes-Alpes', '93', '02')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('04', 'Alpes-de-Haute-Provence', '93', '02')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('06', 'Alpes-Maritimes', '93', '23')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('94', 'Val-de-Marne', '11', '24')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('77', 'Seine-et-Marne', '11', '24')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('91', 'Essonne', '11', '25')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('92', 'Hauts-de-Seine', '11', '25')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('95', 'Val-d''Oise', '11', '25')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('78', 'Yvelines', '11', '25')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('973', 'Guyane', '03', '33')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('2A', 'Corse-du-Sud', '94', '27')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('2B', 'Haute-Corse', '94', '27')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('974', 'La Réunion', '04', '28')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('986', 'Wallis et Futuna', '00', '42')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('985', 'Mayotte (ancien code)', '00', '43')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('988', 'Nouvelle Calédonie', '00', '40')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('44', 'Loire-Atlantique', '52', '17')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('56', 'Morbihan', '53', '14')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('975', 'St-Pierre-et-Miquelon', '00', '44')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('93', 'Seine-Saint-Denis', '11', '24')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('972', 'Martinique', '02', '31')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('987', 'Polynésie Française', '00', '41')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('989', 'Ile de Clipperton', '00', '41')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('17', 'Charente-Maritime', '75', '13')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('70', 'Haute-Saône', '27', '03')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('24', 'Dordogne', '75', '04')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('87', 'Haute-Vienne', '75', '22')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('984', 'Terres australes et antarctiques françaises', '00', '00')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('23', 'Creuse', '75', '22')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('19', 'Corrèze', '75', '22')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('90', 'Territoire de Belfort', '27', '03')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('25', 'Doubs', '27', '03')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('16', 'Charente', '75', '13')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('79', 'Deux-Sèvres', '75', '13')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('68', 'Haut-Rhin', '44', '15')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('67', 'Bas-Rhin', '44', '15')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('81', 'Tarn', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('12', 'Aveyron', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('32', 'Gers', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('82', 'Tarn-et-Garonne', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('46', 'Lot', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('31', 'Haute-Garonne', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('65', 'Hautes-Pyrénées', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('09', 'Ariège', '76', '16')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('08', 'Ardennes', '44', '19')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('52', 'Haute-Marne', '44', '19')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('10', 'Aube', '44', '19')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('51', 'Marne', '44', '19')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('02', 'Aisne', '32', '20')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('60', 'Oise', '32', '20')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('80', 'Somme', '32', '20')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('39', 'Jura', '27', '03')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('40', 'Landes', '75', '04')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('47', 'Lot-et-Garonne', '75', '04')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('64', 'Pyrénées-Atlantiques', '75', '04')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('33', 'Gironde', '75', '04')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('15', 'Cantal', '84', '06')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('03', 'Allier', '84', '06')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('43', 'Haute-Loire', '84', '06')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('63', 'Puy-de-Dôme', '84', '06')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('89', 'Yonne', '27', '07')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('58', 'Nièvre', '27', '07')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('71', 'Saône-et-Loire', '27', '07')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('73', 'Savoie', '84', '08')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('74', 'Haute-Savoie', '84', '08')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('26', 'Drôme', '84', '08')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('38', 'Isère', '84', '08')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('07', 'Ardèche', '84', '08')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('59', 'Nord', '32', '09')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('62', 'Pas-de-Calais', '32', '09')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('01', 'Ain', '84', '10')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('69', 'Rhône', '84', '10')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('42', 'Loire', '84', '10')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('66', 'Pyrénées-Orientales', '76', '11')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('34', 'Hérault', '76', '11')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('11', 'Aude', '76', '11')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('54', 'Meurthe-et-Moselle', '44', '12')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('55', 'Meuse', '44', '12')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('86', 'Vienne', '75', '13')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('30', 'Gard', '76', '11')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('48', 'Lozère', '76', '11')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('57', 'Moselle', '44', '12')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('88', 'Vosges', '44', '12')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('21', 'Côte-d''Or', '27', '07')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('976', 'Mayotte', '06', '43')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('50', 'Manche', '28', '70')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('27', 'Eure', '28', '70')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('14', 'Calvados', '28', '70')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('61', 'Orne', '28', '70')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
INSERT INTO departements (code, nom, code_region, code_academie)
VALUES ('76', 'Seine-Maritime', '28', '70')
ON CONFLICT (code) DO UPDATE SET nom           = EXCLUDED.nom,
                                 code_region   = EXCLUDED.code_region,
                                 code_academie = EXCLUDED.code_academie;
