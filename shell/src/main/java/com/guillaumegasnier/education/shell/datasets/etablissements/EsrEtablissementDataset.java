package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.guillaumegasnier.education.core.enums.Contact.*;

/**
 * identifiant interne
 * libellé
 * nom court
 * sigle
 * type d'établissement
 * typologie d'universités et assimilés
 * secteur d'établissement
 * vague contractuelle
 * localisation
 * site internet
 * géolocalisation
 * uai - identifiant
 * anciens codes uai
 * siret
 * siren
 * rna
 * Identifiant wikidata
 * Elément wikidata
 * identifiant_idref
 * Identifiant ETER
 * Identifiant ROR
 * Elément ROR
 * Identifiant PIC
 * Identifiant OrgRef
 * Identifiant ISNI
 * Elément ISNI
 * Elément Funding Data
 * date_creation
 * texte_de_ref_creation_lib
 * texte_de_ref_creation
 * Code commune
 * Commune
 * Code unité urbaine
 * Unité urbaine
 * Code département
 * Département
 * Code académie
 * Académie
 * Code région
 * Région
 * Ancien code région
 * Ancienne région
 * Mention distribution
 * Adresse
 * Lieu dit
 * Boite postale
 * Code postal
 * Localité
 * Pays
 * Numéro de téléphone
 * statut_juridique_court
 * Statut juridique
 * compte_facebook
 * compte_twitter
 * compte_instagram
 * compte_flickr
 * compte_pinterest
 * flux_rss
 * compte_linkedin
 * compte_france_culture
 * compte_scribd
 * compte_scoopit
 * compte_tumblr
 * compte_youtube
 * compte_vimeo
 * compte_dailymotion
 * compte_github
 * Page Wikipédia en français
 * Page Wikipédia en anglais
 * scanr
 * hal
 * mooc
 * article
 * uo_lib_officiel
 * English name
 * Site internet en anglais
 * Site internet en chinois
 * Site internet en espagnol
 * Site internet en allemand
 * Site internet en italien
 * universites_fusionnees
 * etablissement_experimental
 * champ_recherche
 * inscrits
 * Effectifs d'étudiants inscrits 2010-11
 * Effectifs d'étudiants inscrits 2011-12
 * Effectifs d'étudiants inscrits 2012-13
 * Effectifs d'étudiants inscrits 2013-14
 * Effectifs d'étudiants inscrits 2014-15
 * Effectifs d'étudiants inscrits 2015-16
 * Effectifs d'étudiants inscrits 2016-17
 * Effectifs d'étudiants inscrits 2017-18
 * Effectifs d'étudiants inscrits 2018-19
 * Effectifs d'étudiants inscrits 2019-20
 * Effectifs d'étudiants inscrits 2020-21
 * Effectifs d'étudiants inscrits 2021-22
 * Effectifs d'étudiants inscrits 2022-23
 */
@Getter
@Setter
@ToString
public class EsrEtablissementDataset implements EtablissementDataset {

    @CsvBindByName(column = "uai - identifiant")
    private String uai;
    @CsvBindByName(column = "siret")
    private String siret;
    @CsvBindByName(column = "libellé")
    private String nom;
    @CsvBindByName(column = "Adresse")
    private String adresse;
    @CsvBindByName(column = "Lieu dit")
    private String lieuDit;
    @CsvBindByName(column = "Boite postale")
    private String boitePostale;
    @CsvBindByName(column = "Code postal")
    private String codePostal;
    @CsvBindByName(column = "Code commune")
    private String codeCommune;
    @CsvBindByName(column = "Commune")
    private String nomCommune;
    @CsvBindByName(column = "Numéro de téléphone")
    private String contactTelephone;
    @CsvBindByName(column = "site internet")
    private String contactWeb;
    @CsvBindByName(column = "compte_facebook")
    private String contactFacebook;
    @CsvBindByName(column = "compte_twitter")
    private String contactTwitter;
    @CsvBindByName(column = "compte_linkedin")
    private String contactLinkedin;
    @CsvBindByName(column = "compte_youtube")
    private String contactYoutube;
    @CsvBindByName(column = "Page Wikipédia en français")
    private String contactWikipedia;
    @CsvBindByName(column = "date_creation")
    private String dateOuverture;
    @CsvBindByName(column = "secteur d'établissement")
    private String secteurEtablissement;

    @Override
    public String getCodeCommune() {
        if (codeCommune == null) return null;
        if (codeCommune.isBlank()) return null;
        return codeCommune;
    }

    @Override
    public String getNomCommune() {
        return nomCommune;
    }

    @Override
    public Secteur getSecteur() {
        if (secteurEtablissement != null)
            if (secteurEtablissement.equals("public")) return Secteur.PU;
            else if (secteurEtablissement.equals("privé")) return Secteur.PV;
        return null;
    }

    // compte_flickr
    // compte_pinterest
    // flux_rss
    // compte_france_culture
    // compte_scribd
    // compte_scoopit
    // compte_tumblr
    // compte_vimeo
    // compte_dailymotion
    // compte_github

    // * Page Wikipédia en français
    // * Page Wikipédia en anglais

    @Override
    public String getUai() {
        if (uai == null) return "";
        return uai;
    }

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Nullable
    @Override
    public LocalDate getDateOuverture() {
        try {
            return LocalDate.parse(dateOuverture);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String getComplement() {
        if (lieuDit == null && boitePostale == null)
            return null;

        if (lieuDit != null && !lieuDit.isEmpty())
            return lieuDit;

        if (boitePostale != null && !boitePostale.isEmpty())
            return boitePostale;

        return null;
    }


    @Override
    public String getCodePays() {
        return "";
    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            contacts.add(new ContactEtablissementDataset(TEL, contactTelephone));

        // if (contactMail != null && !contactMail.isEmpty())
        //   contacts.add(new ContactEtablissementDataset("email", contactMail));

        if (contactWeb != null && !contactWeb.isEmpty())
            contacts.add(new ContactEtablissementDataset(WEB, contactWeb));

        if (contactFacebook != null && !contactFacebook.isEmpty())
            contacts.add(new ContactEtablissementDataset(FACEBOOK, contactFacebook));

        if (contactTwitter != null && !contactTwitter.isEmpty())
            contacts.add(new ContactEtablissementDataset(TWITTER, contactTwitter));

        if (contactLinkedin != null && !contactLinkedin.isEmpty())
            contacts.add(new ContactEtablissementDataset(LINKEDIN, contactLinkedin));

        if (contactYoutube != null && !contactYoutube.isEmpty())
            contacts.add(new ContactEtablissementDataset(YOUTUBE, contactYoutube));

        if (contactWikipedia != null && !contactWikipedia.isEmpty())
            contacts.add(new ContactEtablissementDataset(WIKIPEDIA, contactWikipedia));

        return contacts;
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            EsrEtablissementDataset copy = (EsrEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            EsrEtablissementDataset copy = (EsrEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public UUID getId() {
        return UUID.nameUUIDFromBytes(uai.getBytes());
    }

}
