package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.guillaumegasnier.education.core.enums.Contact.TEL;

/**
 * code UAI
 * n° SIRET
 * type d'établissement
 * nom
 * sigle
 * statut
 * tutelle
 * université de rattachement libellé et UAI
 * université de rattachement ID et URL Onisep
 * établissements liés libellés
 * établissements liés URL et ID Onisep
 * boîte postale
 * adresse
 * CP
 * commune
 * commune (COG)
 * cedex
 * téléphone
 * arrondissement
 * département
 * académie
 * région
 * région (COG)
 * longitude (X)
 * latitude (Y)
 * journées portes ouvertes
 * label génération 2024
 * URL et ID Onisep
 */
@Getter
@Setter
@ToString
public class OnisepEtablissementSupDataset implements EtablissementDataset {

    private static final Pattern pattern = Pattern.compile(
            "le\\s+(\\d{2}/\\d{2}/\\d{4})\\s+de\\s+(\\d{2}h\\d{2})\\s+à\\s+(\\d{2}h\\d{2})\\s*(.*?)\\s*$"
    );

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH'h'mm");

    @CsvBindByName(column = "code UAI")
    private String uai;
    @CsvBindByName(column = "n° SIRET")
    private String siret;
    @CsvBindByName(column = "nom")
    private String nom;
    @CsvBindByName(column = "statut")
    private String statut;
    @CsvBindByName(column = "adresse")
    private String adresse;
    @CsvBindByName(column = "CP")
    private String codePostal;
    @CsvBindByName(column = "commune (COG)")
    private String codeCommune;
    @CsvBindByName(column = "commune")
    private String nomCommune;
    @CsvBindByName(column = "boîte postale")
    private String boitePostale;
    @CsvBindByName(column = "cedex")
    private String cedex;
    @CsvBindByName(column = "téléphone")
    private String contactTelephone;
    @CsvBindByName(column = "journées portes ouvertes")
    private String jpo;

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public String getNomCommune() {
        return nomCommune;
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            OnisepEtablissementSupDataset copy = (OnisepEtablissementSupDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String getAdresse() {
        if (adresse == null) return null;
        if (adresse.isBlank()) return null;
        if (adresse.length() > 50) return adresse.substring(0, 50);
        return adresse;
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            OnisepEtablissementSupDataset copy = (OnisepEtablissementSupDataset) this.clone();
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

    @Override
    public Secteur getSecteur() {
        if (statut != null)
            if (statut.equals("public")) return Secteur.PU;
            else if (statut.equals("privé")) return Secteur.PV;
        return null;
    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            contacts.add(new ContactEtablissementDataset(TEL, contactTelephone));

        return contacts;
    }

    @Override
    public List<JPODataset> getJPO() {
        List<JPODataset> jpoList = new ArrayList<>();

        if (jpo != null && !jpo.isBlank()) {

            String[] journees = jpo.split("\\|");

            for (String journee : journees) {
                Matcher matcher = pattern.matcher(journee.trim());

                if (matcher.find()) {
                    JPODataset jpo = new JPODataset();
                    jpo.setUai(this.getUai());
                    jpo.setDate(LocalDate.parse(matcher.group(1), dateFormatter));
                    jpo.setHeureDebut(LocalTime.parse(matcher.group(2), timeFormatter));
                    jpo.setHeureFin(LocalTime.parse(matcher.group(3), timeFormatter));
                    jpo.setCommentaire(matcher.group(4).trim());

                    jpoList.add(jpo);
                }
            }
        }

        return jpoList;
    }
}
