package com.guillaumegasnier.education.annuaire.datasets;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Identifiant_de_l_etablissement
 * Nom_etablissement
 * Type_etablissement
 * Statut_public_prive
 * Adresse_1
 * Adresse_2
 * Adresse_3
 * Code_postal
 * Code_commune
 * Nom_commune
 * Code_departement
 * Code_academie
 * Code_region
 * Ecole_maternelle
 * Ecole_elementaire
 * Voie_generale
 * Voie_technologique
 * Voie_professionnelle
 * Telephone
 * Fax
 * Web
 * Mail
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
 * <p>
 * DTo pour {@link com.guillaumegasnier.education.annuaire.domains.EtablissementEntity}
 */
@Getter
@Setter
@ToString
public class EnEtablissementDataset {

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

    @CsvBindByName(column = "Code_type_contrat_prive")
    private String codeContrat;

    @CsvBindByName(column = "Code_commune")
    private String codeCommune;
}
