package com.guillaumegasnier.education.shell.datasets.formations;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * date_chargement
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
 * nom_departement
 * nom_region
 * nbaction_nbheures
 * coderegion_export
 */
@Getter
@Setter
@ToString
public class CPFFormationDataset implements Dataset {

    @CsvBindByName(column = "nom_of")
    private String nomOf;

    /**
     * RS
     * RNCP
     */
    @CsvBindByName(column = "type_referentiel")
    private String typeReferentiel;

    @CsvBindByName(column = "code_rs")
    private String codeRs;

    @CsvBindByName(column = "code_rncp")
    private String codeRncp;

    @CsvBindByName(column = "intitule_certification")
    private String intituleCertification;

    @CsvBindByName(column = "libelle_niveau_sortie_formation")
    private String libelleNiveauSortieFormation;

    @CsvBindByName(column = "code_certifinfo")
    private String codeCertifinfo;

    @CsvBindByName(column = "siret_of")
    private String siretOf;

    @CsvBindByName(column = "numero_formation")
    private String numeroFormation;

    @CsvBindByName(column = "intitule_formation")
    private String intituleFormation;

    @CsvBindByName(column = "nb_action")
    private String nbAction;
}
