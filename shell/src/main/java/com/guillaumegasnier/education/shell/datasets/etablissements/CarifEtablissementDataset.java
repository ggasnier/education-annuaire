package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.etablissements.enums.EtatEtablissement;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.stream.Stream;


/**
 * Fiche catalogue
 * Siren
 * Nda
 * Siret
 * Nom / Enseigne
 * Raison sociale de l'entreprise
 * Uai
 * Certifié Qualité ?
 * Est le siege de l'entreprise
 * Siret Siege social
 * Adresse
 * Numero de voie
 * Type de voie
 * Nom de la voie
 * Complement d'adresse
 * Code postal
 * Numero de departement
 * Localite
 * Code commune INSEE
 * Cedex
 * Region
 * Numero academie
 * Nom de l'academie
 * Tags
 */
@Getter
@Setter
@ToString
public class CarifEtablissementDataset extends EtablissementDataset {

    @CsvBindByName(column = "Uai")
    private String uai;

    @CsvBindByName(column = "Siret")
    private String siret;
    @CsvBindByName(column = "Raison sociale de l'entreprise")
    private String nom;
    @CsvBindByName(column = "Code commune INSEE")
    private String codeCommune;
    @CsvBindByName(column = "Numero de voie")
    private String numeroVoie;
    @CsvBindByName(column = "Type de voie")
    private String typeVoie;
    @CsvBindByName(column = "Nom de la voie")
    private String nomVoie;
    @CsvBindByName(column = "Complement d'adresse")
    private String complement;
    @CsvBindByName(column = "Code postal")
    private String codePostal;

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
        return complement;
    }

    @Override
    public String getAdresse() {
        return String.join(" ", Stream.of(numeroVoie, typeVoie, nomVoie).filter(Objects::nonNull).toList());
    }

    @Override
    public EtatEtablissement getEtat() {
        return EtatEtablissement.O;
    }

}
