package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Type_etablissement
 * Statut_public_prive
 * Voie_generale
 * Voie_technologique
 * Voie_professionnelle
 * <p>
 * Restauration
 * Hebergement
 * ULIS
 * Apprentissage
 * Segpa
 * Section_arts
 * Section_cinema
 * Section_theatre
 * Section_sport
 * Section_internationale
 * Section_europeenne
 * Lycee_Agricole
 * Lycee_militaire
 * Lycee_des_metiers
 * Post_BAC
 * Appartenance_Education_Prioritaire
 * GRETA
 * SIREN_SIRET
 * Nombre_d_eleves
 * Fiche_onisep
 * position
 * Type_contrat_prive
 * Libelle_departement
 * Libelle_academie
 * Libelle_region
 * coordX_origine
 * coordY_origine
 * epsg_origine
 * nom_circonscription
 * latitude
 * longitude
 * precision_localisation
 * date_ouverture
 * date_maj_ligne
 * etat
 * ministere_tutelle
 * multi_uai
 * rpi_concentre
 * rpi_disperse
 * code_nature
 * libelle_nature
 * Code_type_contrat_prive
 * PIAL
 * etablissement_mere
 * type_rattachement_etablissement_mere
 * code_circonscription
 * code_zone_animation_pedagogique
 * libelle_zone_animation_pedagogique
 * code_bassin_formation
 * libelle_bassin_formation
 */
@Getter
@Setter
@ToString
public class EnEtablissementDataset implements EtablissementDataset {

    @CsvBindByName(column = "Identifiant_de_l_etablissement")
    private String uai;

    @CsvBindByName(column = "SIREN_SIRET")
    private String siret;
    @CsvBindByName(column = "Nom_etablissement")
    private String nom;
    @CsvBindByName(column = "Adresse_1")
    private String adresse;
    @CsvBindByName(column = "Adresse_2")
    private String complement;
    @CsvBindByName(column = "Code_postal")
    private String codePostal;
    @CsvBindByName(column = "code_nature")
    private String codeNature;
    @CsvBindByName(column = "Code_type_contrat_prive")
    private String codeContrat;
    @CsvBindByName(column = "Code_commune")
    private String codeCommune;
    @CsvBindByName(column = "Nom_commune")
    private String nomCommune;
    @CsvBindByName(column = "etat")
    private String etat;
    @CsvBindByName(column = "Telephone")
    private String contactTelephone;
    @CsvBindByName(column = "Mail")
    private String contactMail;
    @CsvBindByName(column = "Web")
    private String contactWeb;
    @CsvBindByName(column = "date_ouverture")
    private String dateOuverture;
    @CsvBindByName(column = "Restauration")
    private String restauration;
    @CsvBindByName(column = "Hebergement")
    private String hebergement;
    @CsvBindByName(column = "ULIS")
    private String ulis;
    @CsvBindByName(column = "Apprentissage")
    private String apprentissage;
    @CsvBindByName(column = "Segpa")
    private String segpa;
    @CsvBindByName(column = "Section_arts")
    private String sectionArts;
    @CsvBindByName(column = "Section_cinema")
    private String sectionCinema;
    @CsvBindByName(column = "Section_theatre")
    private String sectionTheatre;
    @CsvBindByName(column = "Section_sport")
    private String sectionSport;
    @CsvBindByName(column = "Section_internationale")
    private String sectionInternationale;
    @CsvBindByName(column = "Section_europeenne")
    private String sectionEuropeenne;
    @CsvBindByName(column = "Lycee_Agricole")
    private String lyceeAgricole;
    @CsvBindByName(column = "Lycee_militaire")
    private String lyceeMilitaire;
    @CsvBindByName(column = "Lycee_des_metiers")
    private String lyceeDesMetiers;
    @CsvBindByName(column = "Post_BAC")
    private String postBac;

    @Override
    public String getNomCommune() {
        return nomCommune;
    }

    @Override
    public LocalDate getDateOuverture() {
        return LocalDate.parse(dateOuverture);
    }

    @Override
    public String getAdresse() {
        if (adresse == null) return null;
        if (adresse.isBlank()) return null;
        if (adresse.length() > 50) return adresse.substring(0, 50);
        return adresse;
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            EnEtablissementDataset copy = (EnEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            EnEtablissementDataset copy = (EnEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public String getComplement() {
        if (complement == null) return null;
        if (complement.isBlank()) return null;
        if (complement.length() > 50) return complement.substring(0, 50);
        return complement;
    }

    // TODO
    @Override
    public String getCodePays() {
        return "FR";
    }

    @Override
    public EtatEtablissement getEtat() {
        if (etat == null) {
            return EtatEtablissement.O;
        } else {
            switch (etat) {
                case "A FERMER" -> {
                    return EtatEtablissement.C;
                }
                case "FERME" -> {
                    return EtatEtablissement.F;
                }
                default -> {
                    return EtatEtablissement.O;
                }
            }
        }
    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            contacts.add(new ContactEtablissementDataset("telephone", contactTelephone));

        if (contactMail != null && !contactMail.isEmpty())
            contacts.add(new ContactEtablissementDataset("email", contactMail));

        if (contactWeb != null && !contactWeb.isEmpty())
            contacts.add(new ContactEtablissementDataset("web", contactWeb));

        return contacts;
    }

    @Override
    public Set<OptionEtablissement> getOptions() {

        Set<OptionEtablissement> options = new HashSet<>();

        if (restauration != null && restauration.equals("1")) options.add(OptionEtablissement.RESTAURATION);
        if (hebergement != null && hebergement.equals("1")) options.add(OptionEtablissement.HEBERGEMENT);
        if (ulis != null && ulis.equals("1")) options.add(OptionEtablissement.ULIS);
        if (apprentissage != null && apprentissage.equals("1")) options.add(OptionEtablissement.APPRENTISSAGE);
        if (segpa != null && segpa.equals("1")) options.add(OptionEtablissement.SEGPA);
        if (sectionArts != null && sectionArts.equals("1")) options.add(OptionEtablissement.SECTION_ARTS);
        if (sectionCinema != null && sectionCinema.equals("1")) options.add(OptionEtablissement.SECTION_CINEMA);
        if (sectionTheatre != null && sectionTheatre.equals("1")) options.add(OptionEtablissement.SECTION_THEATRE);
        if (sectionSport != null && sectionSport.equals("1")) options.add(OptionEtablissement.SECTION_SPORT);
        if (sectionInternationale != null && sectionInternationale.equals("1"))
            options.add(OptionEtablissement.SECTION_INTERNATIONALE);
        if (sectionEuropeenne != null && sectionEuropeenne.equals("1"))
            options.add(OptionEtablissement.SECTION_EUROPEENNE);
        if (lyceeAgricole != null && lyceeAgricole.equals("1")) options.add(OptionEtablissement.LYCEE_AGRICOLE);
        if (lyceeMilitaire != null && lyceeMilitaire.equals("1")) options.add(OptionEtablissement.LYCEE_MILITAIRE);
        if (lyceeDesMetiers != null && lyceeDesMetiers.equals("1")) options.add(OptionEtablissement.LYCEE_DES_METIERS);
        if (postBac != null && postBac.equals("1")) options.add(OptionEtablissement.POST_BAC);

        return options;
    }
}
