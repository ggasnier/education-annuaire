
    create table academies (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table communes (
        departement_code VARCHAR(3),
        pays_code CHAR(2) not null,
        code CHAR(5) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table departements (
        academie_code CHAR(2),
        code VARCHAR(3) not null,
        region_code CHAR(2),
        nom varchar(255) not null,
        primary key (code)
    );

    create table etablissements (
        nature_code CHAR(2),
        commune_code CHAR(5),
        uai CHAR(8) not null,
        siret CHAR(14),
        adresse varchar(255),
        code_postal varchar(255),
        complement varchar(255),
        nom varchar(255) not null,
        etat CHAR(1) not null,
        primary key (uai)
    );

    create table ips (
        annee integer not null,
        ecart_type float(53),
        indice float(53),
        etablissement_uai CHAR(8) not null,
        primary key (annee, etablissement_uai)
    );

    create table natures (
        code CHAR(2) not null,
        nom varchar(255) not null,
        nom_court varchar(255),
        primary key (code)
    );

    create table pays (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table regions (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    alter table if exists communes 
       add constraint FK44ewu1d8udqhxisxy9ju9hpch 
       foreign key (departement_code) 
       references departements;

    alter table if exists communes 
       add constraint FK518rqpg3s2hyqqens2umxwpui 
       foreign key (pays_code) 
       references pays;

    alter table if exists departements 
       add constraint FKc84s86wdq8ipge2uwkbyn13wt 
       foreign key (academie_code) 
       references academies;

    alter table if exists departements 
       add constraint FKhgq2xx5gqttkos934h4n28jp7 
       foreign key (region_code) 
       references regions;

    alter table if exists etablissements 
       add constraint FK263gc93kuw1ha1q56ed7s0uio 
       foreign key (commune_code) 
       references communes;

    alter table if exists etablissements 
       add constraint FKb0e2op59ch0orxewytjxbivnp 
       foreign key (nature_code) 
       references natures;

    alter table if exists ips 
       add constraint FKokr8mv2bkf7ts4biuhctlbk7i 
       foreign key (etablissement_uai) 
       references etablissements;

    create table academies (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table communes (
        departement_code VARCHAR(3),
        pays_code CHAR(2) not null,
        code CHAR(5) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table departements (
        academie_code CHAR(2),
        code VARCHAR(3) not null,
        region_code CHAR(2),
        nom varchar(255) not null,
        primary key (code)
    );

    create table etablissements (
        nature_code CHAR(2),
        commune_code CHAR(5),
        uai CHAR(8) not null,
        siret CHAR(14),
        adresse varchar(255),
        code_postal varchar(255),
        complement varchar(255),
        nom varchar(255) not null,
        etat CHAR(1) not null,
        primary key (uai)
    );

    create table ips (
        annee integer not null,
        ecart_type float(53),
        indice float(53),
        etablissement_uai CHAR(8) not null,
        primary key (annee, etablissement_uai)
    );

    create table natures (
        code CHAR(2) not null,
        nom varchar(255) not null,
        nom_court varchar(255),
        primary key (code)
    );

    create table pays (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table regions (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    alter table if exists communes 
       add constraint FK44ewu1d8udqhxisxy9ju9hpch 
       foreign key (departement_code) 
       references departements;

    alter table if exists communes 
       add constraint FK518rqpg3s2hyqqens2umxwpui 
       foreign key (pays_code) 
       references pays;

    alter table if exists departements 
       add constraint FKc84s86wdq8ipge2uwkbyn13wt 
       foreign key (academie_code) 
       references academies;

    alter table if exists departements 
       add constraint FKhgq2xx5gqttkos934h4n28jp7 
       foreign key (region_code) 
       references regions;

    alter table if exists etablissements 
       add constraint FK263gc93kuw1ha1q56ed7s0uio 
       foreign key (commune_code) 
       references communes;

    alter table if exists etablissements 
       add constraint FKb0e2op59ch0orxewytjxbivnp 
       foreign key (nature_code) 
       references natures;

    alter table if exists ips 
       add constraint FKokr8mv2bkf7ts4biuhctlbk7i 
       foreign key (etablissement_uai) 
       references etablissements;

    create table academies (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table communes (
        departement_code VARCHAR(3),
        pays_code CHAR(2) not null,
        code CHAR(5) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table departements (
        academie_code CHAR(2),
        code VARCHAR(3) not null,
        region_code CHAR(2),
        nom varchar(255) not null,
        primary key (code)
    );

    create table etablissements (
        nature_code CHAR(2),
        commune_code CHAR(5),
        uai CHAR(8) not null,
        siret CHAR(14),
        adresse varchar(255),
        code_postal varchar(255),
        complement varchar(255),
        nom varchar(255) not null,
        etat CHAR(1) not null,
        primary key (uai)
    );

    create table ips (
        annee integer not null,
        ecart_type float(53),
        indice float(53),
        etablissement_uai CHAR(8) not null,
        primary key (annee, etablissement_uai)
    );

    create table natures (
        code CHAR(2) not null,
        nom varchar(255) not null,
        nom_court varchar(255),
        primary key (code)
    );

    create table pays (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table regions (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    alter table if exists communes 
       add constraint FK44ewu1d8udqhxisxy9ju9hpch 
       foreign key (departement_code) 
       references departements;

    alter table if exists communes 
       add constraint FK518rqpg3s2hyqqens2umxwpui 
       foreign key (pays_code) 
       references pays;

    alter table if exists departements 
       add constraint FKc84s86wdq8ipge2uwkbyn13wt 
       foreign key (academie_code) 
       references academies;

    alter table if exists departements 
       add constraint FKhgq2xx5gqttkos934h4n28jp7 
       foreign key (region_code) 
       references regions;

    alter table if exists etablissements 
       add constraint FK263gc93kuw1ha1q56ed7s0uio 
       foreign key (commune_code) 
       references communes;

    alter table if exists etablissements 
       add constraint FKb0e2op59ch0orxewytjxbivnp 
       foreign key (nature_code) 
       references natures;

    alter table if exists ips 
       add constraint FKokr8mv2bkf7ts4biuhctlbk7i 
       foreign key (etablissement_uai) 
       references etablissements;

    create table academies (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table communes (
        departement_code VARCHAR(3),
        pays_code CHAR(2) not null,
        code CHAR(5) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table departements (
        academie_code CHAR(2),
        code VARCHAR(3) not null,
        region_code CHAR(2),
        nom varchar(255) not null,
        primary key (code)
    );

    create table etablissements (
        nature_code CHAR(2),
        commune_code CHAR(5),
        uai CHAR(8) not null,
        siret CHAR(14),
        adresse varchar(255),
        code_postal varchar(255),
        complement varchar(255),
        nom varchar(255) not null,
        etat CHAR(1) not null,
        primary key (uai)
    );

    create table ips (
        annee integer not null,
        ecart_type float(53),
        indice float(53),
        etablissement_uai CHAR(8) not null,
        primary key (annee, etablissement_uai)
    );

    create table natures (
        code CHAR(2) not null,
        nom varchar(255) not null,
        nom_court varchar(255),
        primary key (code)
    );

    create table pays (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table regions (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    alter table if exists communes 
       add constraint FK44ewu1d8udqhxisxy9ju9hpch 
       foreign key (departement_code) 
       references departements;

    alter table if exists communes 
       add constraint FK518rqpg3s2hyqqens2umxwpui 
       foreign key (pays_code) 
       references pays;

    alter table if exists departements 
       add constraint FKc84s86wdq8ipge2uwkbyn13wt 
       foreign key (academie_code) 
       references academies;

    alter table if exists departements 
       add constraint FKhgq2xx5gqttkos934h4n28jp7 
       foreign key (region_code) 
       references regions;

    alter table if exists etablissements 
       add constraint FK263gc93kuw1ha1q56ed7s0uio 
       foreign key (commune_code) 
       references communes;

    alter table if exists etablissements 
       add constraint FKb0e2op59ch0orxewytjxbivnp 
       foreign key (nature_code) 
       references natures;

    alter table if exists ips 
       add constraint FKokr8mv2bkf7ts4biuhctlbk7i 
       foreign key (etablissement_uai) 
       references etablissements;

    create table academies (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table communes (
        departement_code VARCHAR(3),
        pays_code CHAR(2) not null,
        code CHAR(5) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table departements (
        academie_code CHAR(2),
        code VARCHAR(3) not null,
        region_code CHAR(2),
        nom varchar(255) not null,
        primary key (code)
    );

    create table etablissements (
        nature_code CHAR(2),
        commune_code CHAR(5),
        uai CHAR(8) not null,
        siret CHAR(14),
        adresse varchar(255),
        code_postal varchar(255),
        complement varchar(255),
        nom varchar(255) not null,
        etat CHAR(1) not null,
        primary key (uai)
    );

    create table ips (
        annee integer not null,
        ecart_type float(53),
        indice float(53),
        etablissement_uai CHAR(8) not null,
        primary key (annee, etablissement_uai)
    );

    create table natures (
        code CHAR(2) not null,
        nom varchar(255) not null,
        nom_court varchar(255),
        primary key (code)
    );

    create table pays (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    create table regions (
        code CHAR(2) not null,
        nom varchar(255) not null,
        primary key (code)
    );

    alter table if exists communes 
       add constraint FK44ewu1d8udqhxisxy9ju9hpch 
       foreign key (departement_code) 
       references departements;

    alter table if exists communes 
       add constraint FK518rqpg3s2hyqqens2umxwpui 
       foreign key (pays_code) 
       references pays;

    alter table if exists departements 
       add constraint FKc84s86wdq8ipge2uwkbyn13wt 
       foreign key (academie_code) 
       references academies;

    alter table if exists departements 
       add constraint FKhgq2xx5gqttkos934h4n28jp7 
       foreign key (region_code) 
       references regions;

    alter table if exists etablissements 
       add constraint FK263gc93kuw1ha1q56ed7s0uio 
       foreign key (commune_code) 
       references communes;

    alter table if exists etablissements 
       add constraint FKb0e2op59ch0orxewytjxbivnp 
       foreign key (nature_code) 
       references natures;

    alter table if exists ips 
       add constraint FKokr8mv2bkf7ts4biuhctlbk7i 
       foreign key (etablissement_uai) 
       references etablissements;
