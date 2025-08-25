package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

    @CsvBindByName(column = "adresse")
    private String adresse;

    @CsvBindByName(column = "CP")
    private String codePostal;

    @CsvBindByName(column = "commune (COG)")
    private String codeCommune;

    @CsvBindByName(column = "boîte postale")
    private String boitePostale;

    @CsvBindByName(column = "cedex")
    private String cedex;

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
    public String getComplement() {
        return null;
    }

    @Override
    public String getCodePays() {
        return null;
    }

    @Override
    public String getCodeNature() {
        return null;
    }

    @Override
    public String getCodeContrat() {
        return null;
    }

    @Override
    public EtatEtablissement getEtat() {
        return null;
    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        return List.of();
    }

}
