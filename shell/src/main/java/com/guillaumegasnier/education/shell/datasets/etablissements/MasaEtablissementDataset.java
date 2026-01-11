package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.AbstractMap;
import java.util.UUID;

@Getter
@Setter
@ToString
public class MasaEtablissementDataset implements EtablissementDataset {

    //Année scolaire
    //uai_id_cdn
    @CsvBindByName(column = "Code DGER")
    private String masaId;
    //Type
    @CsvBindByName(column = "Libellé administratif")
    private String nom;
    //Libellé public
    //Début de validité
    @CsvBindByName(column = "Fin de validité")
    private String dateFinValidite;
    //Site internet
    //Téléphone
    //Mél
    //Télécopie
    //uai_facebook
    //uai_instagram
    //uai_twitter
    //Ministère de tutelle
    @CsvBindByName(column = "Secteur")
    private String secteur;
    @CsvBindByName(column = "Siret")
    private String siret;
    @CsvBindByName(column = "Nature")
    private String nomNature;
    //Affiliation
    //Contrat
    @CsvBindByName(column = "Code Education nationale")
    private String uai;
    //Libellé de l'Education nationale
    //Elèves
    //Etudiants
    //Adultes
    //Apprentis
    //Commune
    //Code région
    //Région
    //Code département
    //Département
    @CsvBindByName(column = "adressepostale_ligne1")
    private String adresse1;
    @CsvBindByName(column = "adressepostale_ligne2")
    private String adresse2;
    @CsvBindByName(column = "adressepostale_ligne3")
    private String adresse3;
    @CsvBindByName(column = "adressepostale_ligne4")
    private String adresse4;
    @CsvBindByName(column = "adressepostale_ligne5")
    private String adresse5;
    @CsvBindByName(column = "adressepostale_ligne6")
    private String adresse6;

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public AbstractMap.SimpleEntry<String, String> getExternalId() {
        return new AbstractMap.SimpleEntry<>("MASA", masaId);
    }

    @Override
    public Secteur getSecteur() {
        if (secteur != null) {
            if (secteur.equals("Privé")) {
                return Secteur.PV;
            } else if (secteur.equals("Public")) {
                return Secteur.PU;
            }
        }
        return null;
    }

    @Override
    public MasaEtablissementDataset cloneWithUai(String uai) {
        try {
            MasaEtablissementDataset copy = (MasaEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            MasaEtablissementDataset copy = (MasaEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Boolean isActif() {
        return dateFinValidite.isBlank();
    }

    @Override
    public UUID getId() {
        return UUID.nameUUIDFromBytes(uai.getBytes());
    }

    @Override
    public String getCodeNature() {
        return switch (this.nomNature) {
            case "MFREO" -> "380";
            case "Lycée polyvalent" -> "306"; // Lycée polyvalent
            case "LEAP", "LPA" -> "320"; // Lycée professionnel
            case "Collège" -> "340"; // Collège
            case "CFPPA" -> "740"; // Centre de formation professionnelle et de promotion agricole
            case "LEGTA" -> "300"; // Lycée d'enseignement général et technologique
            case "CFA", "CFA privé" -> "605"; // Organisme de formation - Centre de formation d'apprentis
            case "LEGTPA" -> "307"; // Lycée d'enseignement général, technologique et professionnel agricole
            case "EREA" -> "370"; // Etablissement régional d'enseignement adapté / Lycée d'enseignement adapté
            default -> null;
        };

    }

    @Override
    public String getAdresse() {
        if (adresse4 != null && !adresse4.isBlank())
            return adresse4;
        return null;
    }

    @Override
    public String getComplement() {
        if (adresse5 != null && !adresse5.isBlank())
            return adresse5;
        return null;
    }

    @Override
    public String getCodePostal() {
        if (adresse6 != null && !adresse6.isBlank())
            return adresse6.substring(0, 5);
        return null;
    }

    @Override
    public String getCodeCommune() {
        return null;
    }
    //adressegeographique_ligne1
    //adressegeographique_ligne2
    //adressegeographique_ligne3
    //adressegeographique_ligne4
    //adressegeographique_ligne5
    //adressegeographique_ligne6
    //uai_niveau
    //uainiveau1_codedger
    //uainiveau1_libelle_administratif
    //uainiveau2_codedger
    //uainiveau2_libelle_administratif
    //uai_code_commune
    //Internat
    //Demi-pension
    //Résidence élèves
    //Date des données
    //coordonnees_geo
    //geom
}
