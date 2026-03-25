package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.guillaumegasnier.education.core.enums.Contact.TEL;
import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatAdresse;
import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatJPODataset;

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

    @CsvBindByName(column = "code UAI")
    private String uai;
    @CsvBindByName(column = "n° SIRET")
    private String siret;
    @CsvBindByName(column = "nom")
    private String nom;
    @CsvBindByName(column = "type d'établissement")
    private String typeEtablissement;
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
    public String getUai() {
        if (uai == null || uai.isBlank()) return "";
        if ("0755531K".equals(uai))
            return "0755531E"; // Institut d'Enseignement Supérieur d'Informatique et de Gestion
        if ("0134349T".equals(uai))
            return "0134359T"; // Institut de Formation d'Osteopathes Animaliers
        return uai;
    }

    public String getCodeCommune() {
        if (codeCommune == null || codeCommune.isBlank()) return null;
        return switch (codeCommune) {
            case "75100" -> // Paris
                    "75056";
            case "69380" -> // Lyon
                    "69123";
            case "13200" -> // Marseille
                    "13055";
            default -> codeCommune;
        };

    }

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public OnisepEtablissementSupDataset cloneWithUai(String uai) {
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
        return formatAdresse(adresse);
    }

    @Override
    public OnisepEtablissementSupDataset cloneWithSiret(String siret) {
        try {
            OnisepEtablissementSupDataset copy = (OnisepEtablissementSupDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Secteur getSecteur() {
        if (statut != null) {
            if (statut.equals("public")) {
                return Secteur.PU;
            } else if (statut.equals("privé")) {
                return Secteur.PV;
            }
        }
        return null;
    }

    @Override
    public Boolean isActif() {
        return true;
    }

//    @Override
//    public String getCodeNature() {
//        if (typeEtablissement == null || typeEtablissement.isBlank()) return null;
//
//        switch (typeEtablissement) {
//            case "école de santé":
//                return "430"; // Ecole de formation sanitaire et sociale"
//            default:
//                return null;
//        }
//    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            contacts.add(new ContactEtablissementDataset(TEL, contactTelephone));

        return contacts;
    }

    @Override
    public List<JPODataset> getJPO() {
        if (jpo != null && !jpo.isBlank())
            return Stream.of(jpo.split("\\|"))
                    .map(input -> formatJPODataset(this.getUai(), input.trim()))
                    .toList();
        return Collections.emptyList();
    }
}
