package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.guillaumegasnier.education.core.enums.OptionEtablissement.*;

/**
 * Action de dispositif (AD) identifiant Onisep
 * Type de dispositif (TYPDISP) libellé
 * TYPDISP ID et URL Onisep
 * TYPDISP indexation Onisep
 * Structure d'enseignement (ENS) libellé
 * ENS URL et ID Onisep
 * ENS type
 * ENS code UAI
 * ENS statut
 * ENS adresse
 * ENS code postal
 * ENS commune
 * ENS département
 * ENS académie
 * ENS région
 * ENS latitude
 * ENS longitude
 * ENS téléphone
 * ENS site web
 * ENS hébergement
 * ENS accessibilité
 * AD durée
 * AD éléments d'enseignement
 * AD modalités accueil
 * AD date de modification
 *
 * <a href="https://www.data.gouv.fr/datasets/ideo-actions-de-dispositif/">Référence</a>
 */
@Slf4j
@Getter
@Setter
@ToString
public class OnisepDispositifDataset implements Dataset {

    @CsvBindByName(column = "Action de dispositif (AD) identifiant Onisep")
    private String code;

    @CsvBindByName(column = "Type de dispositif (TYPDISP) libellé")
    private String nom;

    @CsvBindByName(column = "TYPDISP ID et URL Onisep")
    private String codeTypeDispositif;

    @CsvBindByName(column = "ENS code UAI")
    private String uai;

    @CsvBindByName(column = "ENS accessibilité")
    private String accessibilite;

    @CsvBindByName(column = "AD éléments d'enseignement")
    private String enseignement;

    @CsvBindByName(column = "ENS téléphone")
    private String contactTelephone;

    @CsvBindByName(column = "ENS site web")
    private String contactWeb;

    @CsvBindByName(column = "Structure d'enseignement (ENS) libellé")
    private String nomEtablissement;
    //ENS URL et ID Onisep
    //ENS type
    @CsvBindByName(column = "ENS statut")
    private String statutEtablissement;

    public OptionEtablissement getOption() {
        if (nom == null) return null;
        var i = switch (nom) {
            case "classe sport-études en collège", "classe sport-études en lycée" -> OptionEtablissement.SPORT_ETUDES;
            case "section sportive de collège", "section sportive de lycée" -> OptionEtablissement.SECTION_SPORT;
            case "section Abibac (double délivrance des baccalauréats français et allemand)" ->
                    OptionEtablissement.ABIBAC;
            case "section Bachibac (double délivrance des baccalauréats français et espagnol)" ->
                    OptionEtablissement.BACHIBAC;
            case "section Esabac (double délivrance des baccalauréats français et italien)" ->
                    OptionEtablissement.ESABAC;
            case "section européenne de lycée général et technologique", "section européenne de lycée professionnel" ->
                    OptionEtablissement.SECTION_EUROPEENNE;
            case "section internationale de classe de seconde", "section internationale de collège" ->
                    OptionEtablissement.SECTION_INTERNATIONALE;
            case "unité pédagogique pour élèves allophones arrivants en collège (Classe pour non francophones)",
                 "unité pédagogique pour élèves allophones arrivants en lycée (Classe pour non francophones)" ->
                    OptionEtablissement.UPE2A;
            case "classe à horaires aménagés arts et métiers du spectacle" -> CHA_ARTS;
            case "classe à horaires aménagés arts plastiques" -> CHA_ARTS2;
            case "classe à horaires aménagés cinéma" -> CHA_CINE;
            case "classe à horaires aménagés danse" -> CHA_DANSE;
            case "classe à horaires aménagés musique" -> CHA_MUSIQUE;
            case "classe à horaires aménagés théâtre" -> CHA_THEATRE;
            case "classes à horaires aménagés mathématiques et sciences" -> CHAMS;
            case "section bilangue de collège",
                 "section bilingue de langue régionale de collège",
                 "section bilingue de langue régionale de lycée" -> OptionEtablissement.SECTION_BILINGUE;
            case "section de langue orientale de lycée" -> OptionEtablissement.SECTION_ORIENTALE;
            case "unité localisée pour l'inclusion scolaire en collège",
                 "unité localisée pour l'inclusion scolaire en lycée" -> OptionEtablissement.ULIS;
            case "section d'enseignement général et professionnel adapté" -> OptionEtablissement.SEGPA;
            case "brevet d'initiation à la mer" -> BREVET_MER;
            case "brevet d'initiation aéronautique" -> BREVET_AERO;
            case "classe de 3e prépa-métiers" -> PREPA_METIERS;
            case "classe pour enfants de familles itinérantes et de voyageurs" -> FORAINS;
            case "dispositif relais" -> RELAIS;
            default -> null;
        };

        if (i == null) {
            log.warn("Option non mappée {} pour {}", nom, uai);
        }
        return i;
    }

    //TYPDISP indexation Onisep

    public List<Langue> getLangueList() {
        if (getOption().equals(OptionEtablissement.SECTION_EUROPEENNE) || getOption().equals(OptionEtablissement.SECTION_ORIENTALE) || getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE) || getOption().equals(OptionEtablissement.SECTION_BILINGUE)) {
            return Stream.of(enseignement.split(","))
                    .map(Langue::transformation)
                    .filter(Objects::nonNull)
                    .toList();
        }
        return Collections.emptyList();
    }

    public List<Sport> getSportList() {
        if (getOption().equals(SPORT_ETUDES) || getOption().equals(SECTION_SPORT)) {
            return Stream.of(enseignement.split(","))
                    .map(String::toUpperCase)
                    .map(Sport::transformation)
                    .filter(Objects::nonNull)
                    .toList();
        }
        return Collections.emptyList();
    }

    //ENS adresse
    //ENS code postal
    //ENS commune
    //ENS département
    //ENS académie
    //ENS région
    //ENS latitude
    //ENS longitude
    //ENS téléphone
    //ENS site web
    //ENS hébergement
    //AD durée
    //AD modalités accueil
    //AD date de modification

    public Secteur getSecteur() {
        if (statutEtablissement == null) return null;
        if (statutEtablissement.equals("public")) return Secteur.PU;
        return Secteur.PV;
    }
}
