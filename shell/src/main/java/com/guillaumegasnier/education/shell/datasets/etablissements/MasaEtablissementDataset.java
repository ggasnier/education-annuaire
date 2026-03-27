package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatAdresse;

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
    @CsvBindByName(column = "uai_code_commune")
    private String codeCommune;
    @CsvBindByName(column = "Internat")
    private String hebergement;
    @CsvBindByName(column = "Demi-pension")
    private String restauration;

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret.replace(" ", "");
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
        if (dateFinValidite == null)
            return true;
        return dateFinValidite.isBlank();
    }
    
    @Override
    public String getAdresse() {
        return formatAdresse(adresse4);
    }

    @Override
    public String getComplement() {
        return formatAdresse(adresse5);
    }

    @Override
    public String getCodePostal() {
        if (adresse6 != null && !adresse6.isBlank())
            return adresse6.substring(0, 5);
        return null;
    }

    @Override
    public Set<OptionEtablissement> getOptions() {
        Set<OptionEtablissement> options = new HashSet<>();

        if (restauration != null && restauration.equals("Oui")) options.add(OptionEtablissement.RESTAURATION);
        if (hebergement != null && hebergement.equals("Oui")) options.add(OptionEtablissement.HEBERGEMENT);

        return options;
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
    //Résidence élèves
    //Date des données
    //coordonnees_geo
    //geom
}
