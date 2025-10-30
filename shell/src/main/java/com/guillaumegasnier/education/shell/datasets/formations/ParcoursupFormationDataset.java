package com.guillaumegasnier.education.shell.datasets.formations;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParcoursupFormationDataset implements Dataset {

    @CsvBindByName(column = "Session")
    private String annee;

    @CsvBindByName(column = "Identifiant de l'établissement")
    private String uai;

    @CsvBindByName(column = "Nom de l'établissement")
    private String nomEtablissement;

    @CsvBindByName(column = "Types d'établissement")
    private String typeEtablissement;

    @CsvBindByName(column = "Types de formation")
    private String typeFormation;

    @CsvBindByName(column = "Nom long de la formation")
    private String nomFormation;

    @CsvBindByName(column = "Mentions/Spécialités")
    private String mentions;

    @CsvBindByName(column = "Formations en apprentissage")
    private String apprentissage;

    @CsvBindByName(column = "Internat")
    private String internat;

    @CsvBindByName(column = "Aménagement")
    private String amenagement;

    @CsvBindByName(column = "Informations complémentaires")
    private String complements;

    @CsvBindByName(column = "Région")
    private String region;

    @CsvBindByName(column = "Département")
    private String departement;

    @CsvBindByName(column = "Commune")
    private String commune;

    @CsvBindByName(column = "Lien vers la fiche formation")
    private String lienFormation;

    @CsvBindByName(column = "Lien vers les données statistiques pour l'année antérieure")
    private String lienStatistiques;

    @CsvBindByName(column = "Site internet de l'établissement")
    private String siteInternet;

    @CsvBindByName(column = "Localisation")
    private String localisation;

    @CsvBindByName(column = "Nom court de la formation")
    private String nomCourtFormation;

    @CsvBindByName(column = "Code interne Parcoursup de la formation")
    private String codeInterneFormation;

    @CsvBindByName(column = "Code interne Parcoursup pour les portails")
    private String codeExterneParcoursup;

    @CsvBindByName(column = "etablissement_id_paysage")
    private String etablissementIdPaysage;

    @CsvBindByName(column = "composante_id_paysage")
    private String composanteIdPaysage;

    @CsvBindByName(column = "rnd")
    private String rnd;

    @CsvBindByName(column = "code_formation")
    private String codeFormation;
}
