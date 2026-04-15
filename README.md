# 🎓 Formakoi — Moteur de recherche de l'Éducation et de la Formation en France

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ggasnier_education-annuaire&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)

> Plateforme open-data agrégeant les données des établissements scolaires, des formations et des référentiels métiers en
> France, à destination des **parents**, **enseignants** et **élèves**.

🌐 **[formakoi.guillaumegasnier.com](https://formakoi.guillaumegasnier.com/)**

---

## 📌 À propos

**Formakoi** est une application web Spring Boot qui consolide et expose des données issues de multiples sources
publiques (Éducation Nationale, ESR, ONISEP, CARIF, France Compétences, France Travail…) afin de fournir un annuaire
complet et consultable sur l'éducation et la formation professionnelle en France.

---

## 🏗️ Architecture

Projet Maven multi-modules :

```
annuaire (parent)
├── core    — Domaine partagé : entités JPA/Elasticsearch, repositories, services
├── shell   — CLI Spring Shell : import des données open-data
└── web     — Application web : API REST + frontend Thymeleaf (Formakoi)
```

### Stack technique

| Couche           | Technologie                                                    |
|------------------|----------------------------------------------------------------|
| Langage          | Java 21                                                        |
| Framework        | Spring Boot 3.5, Spring Shell 3.4                              |
| Persistance      | PostgreSQL 18, Spring Data JPA, Flyway                         |
| Recherche        | Elasticsearch 9.3 (analyseurs French)                          |
| Mapping          | MapStruct 1.6                                                  |
| Templating       | Thymeleaf                                                      |
| API              | REST — documentée via SpringDoc / OpenAPI (`/swagger-ui.html`) |
| Qualité          | JUnit 5, JaCoCo, SonarCloud                                    |
| Conteneurisation | Docker / Docker Compose                                        |
| Build            | Maven 3                                                        |

---

## 🚀 Démarrage rapide

### Prérequis

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

### 1. Démarrer l'infrastructure locale

```bash
docker-compose up -d
```

Lance PostgreSQL 18 (`localhost:5432`) et Elasticsearch 9.3 (`localhost:9200`).

### 2. Compiler le projet

```bash
mvn clean install
```

> Le module `shell` génère des sources JAXB (LHEO, RNCP) lors de la phase `generate-sources`.  
> En cas de sources manquantes : `mvn generate-sources -pl shell`

### 3. Lancer les imports de données (CLI)

```bash
# Depuis le module shell, profil local
cd shell
mvn spring-boot:run -Plocal
```

Voir la section [Ordre d'import](#-ordre-dimport-des-données) pour la séquence complète.

### 4. Démarrer l'application web

```bash
cd web
mvn spring-boot:run
```

L'interface est accessible sur `http://localhost:8080`.  
La documentation API Swagger est disponible sur `http://localhost:8080/swagger-ui.html`.

---

## 🧪 Tests

```bash
# Suite complète
mvn clean verify

# Par module
mvn test -pl core
mvn test -pl shell
mvn test -pl web

# Classe de test spécifique
mvn test -pl core -Dtest=CoreEtablissementServiceImplTest
```

---

## 📦 Sources de données

| Source              | Données importées                                                               |
|---------------------|---------------------------------------------------------------------------------|
| Éducation Nationale | Établissements ouverts/fermés, effectifs, IPS, IVA, langues, sections sportives |
| ESR                 | Établissements d'enseignement supérieur, formations Parcoursup                  |
| ONISEP              | Établissements, journées portes ouvertes                                        |
| CARIF-OREF          | Organismes et offres de formation                                               |
| MASA                | Établissements agricoles                                                        |
| France Compétences  | Certifications RNCP et RS (XML/ZIP)                                             |
| France Travail      | Référentiel ROME v4.6 (métiers, compétences, arborescence)                      |
| INSEE               | Communes, départements, régions, pays                                           |

---

## 🔄 Ordre d'import des données

Les imports sont exécutés via la CLI Spring Shell en respectant les dépendances suivantes :

```
import-contrats
import-natures

import-territoires
    ├── import-pays
    ├── import-regions
    ├── import-academies
    ├── import-departements
    └── import-communes

import-etablissements-global
    ├── import-etablissements
    │   ├── import-etablissements-en-ouverts
    │   ├── import-etablissements-esr
    │   ├── import-etablissements-carif
    │   ├── import-etablissements-onisep
    │   ├── import-etablissements-masa
    │   └── import-etablissements-en-fermes
    ├── import-etablissements-details
    │   ├── import-dispositifs
    │   ├── import-langues
    │   ├── import-sports
    │   ├── import-specialites
    │   ├── import-jpo
    │   └── import-euroscol
    └── import-etablissements-metadatas
        ├── import-effectifs
        ├── import-ips
        │   ├── import-ips-ecoles
        │   ├── import-ips-colleges
        │   └── import-ips-lycees
        └── import-iva
            ├── import-iva-colleges
            └── import-iva-lycees

import-rncp
import-rome
```

---

## ⚙️ Profils Spring

| Profil  | Module | Rôle                                                                |
|---------|--------|---------------------------------------------------------------------|
| `local` | shell  | Lecture des datasets depuis le filesystem local (`shell/datasets/`) |
| `prod`  | shell  | Téléchargement des datasets depuis les URLs distantes               |
| `prod`  | web    | Configuration datasource de production                              |

---

## 🗺️ Roadmap

Consultez le fichier [ROADMAP.md](./ROADMAP.md) pour le détail des fonctionnalités terminées et à venir.

---

## 🤝 Contribution

Ce projet est développé à titre personnel. Les retours, suggestions et issues sont les bienvenus.

---

## 👤 Auteur

**Guillaume Gasnier** — Développeur Java Spring Boot Senior

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Guillaume%20Gasnier-0077B5?logo=linkedin)](https://www.linkedin.com/in/guillaumegasnier/)
[![GitHub](https://img.shields.io/badge/GitHub-ggasnier-181717?logo=github)](https://github.com/ggasnier)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Quality-orange?logo=sonarcloud)](https://sonarcloud.io/summary/new_code?id=ggasnier_education-annuaire)
