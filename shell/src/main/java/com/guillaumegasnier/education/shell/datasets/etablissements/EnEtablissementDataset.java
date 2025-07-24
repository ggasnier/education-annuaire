package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.etablissements.enums.EtatEtablissement;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Type_etablissement
 * Statut_public_prive
 * Voie_generale
 * Voie_technologique
 * Voie_professionnelle
 * <p>
 * Restauration
 * Hebergement
 * ULIS
 * Apprentissage
 * Segpa
 * Section_arts
 * Section_cinema
 * Section_theatre
 * Section_sport
 * Section_internationale
 * Section_europeenne
 * Lycee_Agricole
 * Lycee_militaire
 * Lycee_des_metiers
 * Post_BAC
 * Appartenance_Education_Prioritaire
 * GRETA
 * SIREN_SIRET
 * Nombre_d_eleves
 * Fiche_onisep
 * position
 * Type_contrat_prive
 * Libelle_departement
 * Libelle_academie
 * Libelle_region
 * coordX_origine
 * coordY_origine
 * epsg_origine
 * nom_circonscription
 * latitude
 * longitude
 * precision_localisation
 * date_ouverture
 * date_maj_ligne
 * etat
 * ministere_tutelle
 * multi_uai
 * rpi_concentre
 * rpi_disperse
 * code_nature
 * libelle_nature
 * Code_type_contrat_prive
 * PIAL
 * etablissement_mere
 * type_rattachement_etablissement_mere
 * code_circonscription
 * code_zone_animation_pedagogique
 * libelle_zone_animation_pedagogique
 * code_bassin_formation
 * libelle_bassin_formation
 */
@Getter
@Setter
@ToString
public class EnEtablissementDataset extends EtablissementDataset {

    @CsvBindByName(column = "Identifiant_de_l_etablissement")
    private String uai;

    @CsvBindByName(column = "SIREN_SIRET")
    private String siret;
    @CsvBindByName(column = "Nom_etablissement")
    private String nom;
    @CsvBindByName(column = "Adresse_1")
    private String adresse;
    @CsvBindByName(column = "Adresse_2")
    private String complement;
    @CsvBindByName(column = "Code_postal")
    private String codePostal;
    @CsvBindByName(column = "code_nature")
    private String codeNature;
    @CsvBindByName(column = "Code_type_contrat_prive")
    private String codeContrat;
    @CsvBindByName(column = "Code_commune")
    private String codeCommune;
    @CsvBindByName(column = "etat")
    private String etat;

    @CsvBindByName(column = "Telephone")
    private String contactTelephone;
    @CsvBindByName(column = "Mail")
    private String contactMail;
    @CsvBindByName(column = "Web")
    private String contactWeb;

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
    public EtatEtablissement getEtat() {
        if (etat == null) {
            return EtatEtablissement.O;
        } else {
            switch (etat) {
                case "A FERMER" -> {
                    return EtatEtablissement.C;
                }
                case "FERME" -> {
                    return EtatEtablissement.F;
                }
                default -> {
                    return EtatEtablissement.O;
                }
            }
        }
    }

    //    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            contacts.add(new ContactEtablissementDataset("telephone", contactTelephone));

        if (contactMail != null && !contactMail.isEmpty())
            contacts.add(new ContactEtablissementDataset("email", contactMail));

        if (contactWeb != null && !contactWeb.isEmpty())
            contacts.add(new ContactEtablissementDataset("web", contactWeb));

        return contacts;
    }
}
