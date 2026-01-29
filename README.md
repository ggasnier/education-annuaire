# Annuaire de l'Education et de la Formation en France (AEFF)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)

## Roadmap

### v1.0.0

- [X] Partie Territoires
    - [X] Pays
    - [X] Régions
    - [X] Académies
    - [X] Départements
    - [X] Communes
- [ ] Partie Établissements
    - [X] Structure
    - [X] Nature
    - [X] Public/Privé (secteur)
    - [X] Contrat
    - [ ] Options
        - [ ] Structure
        - [ ] Comodités
        - [ ] Labels
        - [ ] Sections langues
        - [ ] Sections cultures
        - [ ] Sport
        - [ ] Enseignement
        - [ ] Autre
        - [ ] PMR (à approfondir)
    - [ ] Contacts
        - [ ] Site web
        - [ ] Réseaux sociaux
        - [ ] Tél et fax
    - [ ] Langues
        - [X] LV1, LV2, LCA
        - [X] Sections internationales
        - [ ] Sections langues orientales
        - [ ] Sections binationales
        - [ ] Autre
    - [ ] Sport
        - [ ] Sections sportives
        - [ ] Sections sport-études
    - [ ] Journées portes ouvertes
        - [ ] Onisep
        - [ ] Masa
    - [ ] Indicateurs
        - [X] Effectifs
        - [ ] IPS
        - [ ] IVA
        - [ ] Personnels
- [ ] Partie Formations
    - [ ] Structure
    - [ ] Formations
        - [ ] Onisep ESR
        - [ ] LHEO
        - [ ] CPF
        - [ ] Parcoursup
        - [ ] Carif
        - [ ] Masa
        - [ ] Autre
    - [ ] RNCP/RS
    - [ ] Organisme de formation
- [ ] Recherche
    - [ ] Etablissements
    - [ ] Formations

## Chargement

```
import-contrats
import-natures

import-territoires
    ├──import-pays
    ├──import-regions
    ├──import-academies
    ├──import-departements
    └──import-communes
    
import-etablissements
    ├──import-etablissements-en-ouverts
    ├──import-etablissements-esr
    ├──import-etablissements-carif
    ├──import-etablissements-onisep
    ├──import-etablissements-masa
    └──import-etablissements-en-fermes
    
import-etablissements-details
    ├──import-dispositifs
    ├──import-langues
    ├──import-sports
    ├──import-specialites
    ├──import-jpo
    └──import-euroscol
    
import-etablissements-metadatas
    ├──import-effectifs
    ├──import-ips-colleges
    ├──import-ips-lycees
    └──import-iva-colleges
    
```