package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

/**
 * numero_uai
 * appellation_officielle
 * denomination_principale
 * patronyme_uai
 * secteur_public_prive_libe
 * date_ouverture
 * date_fermeture
 * adresse_uai
 * lieu_dit_uai
 * boite_postale_uai
 * code_postal_uai
 * localite_acheminement_uai
 * coordonnee_x
 * coordonnee_y
 * EPSG
 * latitude
 * longitude
 * position
 * appariement
 * localisation
 * nature_uai
 * nature_uai_libe
 * code_departement
 * code_region
 * code_academie
 * code_commune
 * libelle_departement
 * libelle_region
 * libelle_academie
 * libelle_commune
 * Restauration
 * Herbergement
 * Ecole_maternelle
 * Ecole_elementaire
 * ULIS
 * GRETA
 */
@Getter
@Setter
@ToString
public class EnEtablissementFermeDataset implements EtablissementDataset {

    @CsvBindByName(column = "numero_uai")
    private String uai;

    @CsvBindByName(column = "appellation_officielle")
    private String nom;

    private String siret;

    @CsvBindByName(column = "adresse_uai")
    private String adresse;

    private String complement;

    @CsvBindByName(column = "code_postal_uai")
    private String codePostal;

    @CsvBindByName(column = "code_commune")
    private String codeCommune;

    @CsvBindByName(column = "libelle_commune")
    private String nomCommune;

    private String codePays;

    @CsvBindByName(column = "nature_uai")
    private String codeNature;

    private String codeContrat;

    @CsvBindByName(column = "date_ouverture")
    private String dateOuverture;

    @CsvBindByName(column = "date_fermeture")
    private String dateFermeture;

    @Override
    public LocalDate getDateFermeture() {
        return LocalDate.parse(dateFermeture);
    }

    @Override
    public LocalDate getDateOuverture() {
        return LocalDate.parse(dateOuverture);
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            EnEtablissementFermeDataset copy = (EnEtablissementFermeDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String getNomCommune() {
        return nomCommune;
    }

    @Override
    public Boolean isActif() {
        return false;
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            EnEtablissementFermeDataset copy = (EnEtablissementFermeDataset) this.clone();
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
