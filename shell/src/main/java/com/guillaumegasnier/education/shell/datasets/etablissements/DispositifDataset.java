package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
public class DispositifDataset implements Dataset {

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

    public OptionEtablissement getOption() {
        return switch (nom) {
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
            case "classe à horaires aménagés arts et métiers du spectacle", "classe à horaires aménagés arts plastiques"
            , "classe à horaires aménagés cinéma", "classe à horaires aménagés danse",
                 "classe à horaires aménagés musique", "classe à horaires aménagés théâtre" ->
                    OptionEtablissement.CL_H_AM;
            case "section bilangue de collège", "section bilingue de langue régionale de collège",
                 "section bilingue de langue régionale de lycée" -> OptionEtablissement.SECTION_BILINGUE;
            case "section de langue orientale de lycée" -> OptionEtablissement.SECTION_ORIENTALE;
            case "unité localisée pour l'inclusion scolaire en collège",
                 "unité localisée pour l'inclusion scolaire en lycée" -> OptionEtablissement.ULIS;
            case "section d'enseignement général et professionnel adapté" -> OptionEtablissement.SEGPA;
            default -> null;

//            case "brevet d'initiation à la mer":
//            case "brevet d'initiation aéronautique":
//            case "classe de 3e prépa-métiers":
//            case "classe pour enfants de familles itinérantes et de voyageurs":
//            case "dispositif relais":
        };
    }

    public List<Langue> getLangueList() {
        if (getOption().equals(OptionEtablissement.SECTION_EUROPEENNE) || getOption().equals(OptionEtablissement.SECTION_ORIENTALE) || getOption().equals(OptionEtablissement.SECTION_INTERNATIONALE) || getOption().equals(OptionEtablissement.SECTION_BILINGUE)) {
            return Stream.of(enseignement.split(","))
                    .map(Langue::transformation)
                    .filter(Objects::nonNull)
                    .toList();
        }
        return Collections.emptyList();
    }

    //TYPDISP indexation Onisep
    //Structure d'enseignement (ENS) libellé
    //ENS URL et ID Onisep
    //ENS type
    //ENS statut
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
}
