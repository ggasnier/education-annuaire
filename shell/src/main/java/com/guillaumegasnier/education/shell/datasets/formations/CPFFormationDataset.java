package com.guillaumegasnier.education.shell.datasets.formations;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.toNormalizedId;

/**
 * date_chargement
 * nom_of
 * nom_departement
 * nom_region
 * type_referentiel
 * code_rs
 * code_rncp
 * intitule_certification
 * libelle_niveau_sortie_formation
 * code_formacode_1
 * code_formacode_2
 * code_formacode_3
 * code_formacode_4
 * code_formacode_5
 * libelle_code_formacode_principal
 * code_rome_1
 * code_rome_2
 * code_rome_3
 * code_rome_4
 * code_rome_5
 * libelle_nsf_1
 * libelle_nsf_2
 * libelle_nsf_3
 * code_nsf_1
 * code_nsf_2
 * code_nsf_3
 * code_certifinfo
 * siret_of
 * numero_formation
 * intitule_formation
 * points_forts
 * objectif_formation
 * contenu_formation
 * resultats_attendus_formation
 * nb_action
 * nb_session_active
 * nb_session_a_distance
 * nombre_heures_total_min
 * nombre_heures_total_max
 * nombre_heures_total_mean
 * frais_ttc_tot_min
 * frais_ttc_tot_max
 * frais_ttc_tot_mean
 * code_departement
 * code_region
 * nbaction_nbheures
 * coderegion_export
 */
@Getter
@Setter
@ToString
public class CPFFormationDataset implements Dataset {

    private Boolean certifiante = true;
    /**
     * Désignation de l'OF si renseignée, sinon Raison sociale
     */
    @CsvBindByName(column = "nom_of")
    private String nomOrganismeFormation;

    //date_chargement
    @CsvBindByName(column = "code_departement")
    private String codeDepartement;
    @CsvBindByName(column = "nom_departement")
    private String nomDepartement;
    @CsvBindByName(column = "code_region")
    private String codeRegion;
    @CsvBindByName(column = "nom_region")
    private String nomRegion;
    /**
     * RS ou RNCP
     */
    @CsvBindByName(column = "type_referentiel")
    private String typeReferentiel;
    @CsvBindByName(column = "code_rs")
    private String codeRS;
    @CsvBindByName(column = "code_rncp")
    private String codeRNCP;
    @CsvBindByName(column = "code_certifinfo")
    private String codeCertifInfo;

    //intitule_certification

    //libelle_niveau_sortie_formation

    //code_formacode_1
    //code_formacode_2
    //code_formacode_3
    //code_formacode_4
    //code_formacode_5

    //libelle_code_formacode_principal

    //code_rome_1
    //code_rome_2
    //code_rome_3
    //code_rome_4
    //code_rome_5

    //libelle_nsf_1
    //libelle_nsf_2
    //libelle_nsf_3

    //code_nsf_1
    //code_nsf_2
    //code_nsf_3
    @CsvBindByName(column = "siret_of")
    private String siret;
    @CsvBindByName(column = "numero_formation")
    private String numeroFormation;
    @CsvBindByName(column = "intitule_formation")
    private String nom;
    @CsvBindByName(column = "points_forts")
    private String pointsForts;
    @CsvBindByName(column = "objectif_formation")
    private String objectif;
    @CsvBindByName(column = "contenu_formation")
    private String contenu;
    @CsvBindByName(column = "resultats_attendus_formation")
    private String resultats;

    public Long getFormationId() {
        return toNormalizedId("CPF", numeroFormation);
    }

    //nb_action
    //nb_session_active
    //nb_session_a_distance

    //nombre_heures_total_min
    //nombre_heures_total_max
    //nombre_heures_total_mean

    //frais_ttc_tot_min
    //frais_ttc_tot_max
    //frais_ttc_tot_mean

    //nbaction_nbheures
    //coderegion_export
}
