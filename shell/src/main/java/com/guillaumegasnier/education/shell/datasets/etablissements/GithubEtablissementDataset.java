package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@Setter
public class GithubEtablissementDataset implements EtablissementDataset {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @CsvBindByName(column = "uai")
    private String uai;
    @CsvBindByName(column = "nom")
    private String nom;
    private String siret;
    @CsvBindByName(column = "code_nature")
    private String codeNature;
    @CsvBindByName(column = "nom_nature")
    private String nomNature;
    @CsvBindByName(column = "nom_secteur")
    private String nomSecteur;
    @CsvBindByName(column = "adresse")
    private String adresse;
    @CsvBindByName(column = "complement")
    private String complement;
    @CsvBindByName(column = "code_postal")
    private String codePostal;
    @CsvBindByName(column = "code_commune")
    private String codeCommune;
    @CsvBindByName(column = "nom_commune")
    private String nomCommune;
    @CsvBindByName(column = "code_pays")
    private String codePays;
    @CsvBindByName(column = "nom_pays")
    private String nomPays;
    @CsvBindByName(column = "date_ouverture")
    private String dateOuverture;

    @Override
    public LocalDate getDateOuverture() {
        return LocalDate.parse(dateOuverture, dateFormatter);
    }

    @Override
    public Boolean isActif() {
        return true;
    }

    @Override
    public Secteur getSecteur() {
        if (nomSecteur == null) return null;
        if (nomSecteur.equalsIgnoreCase("public")) return Secteur.PU;
        if (nomSecteur.equalsIgnoreCase("privé")) return Secteur.PV;
        return null;
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            GithubEtablissementDataset copy = (GithubEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            GithubEtablissementDataset copy = (GithubEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
