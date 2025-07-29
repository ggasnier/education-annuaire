package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CONTRAT_ETABLISSEMENT
 * LIBELLE_COURT
 * LIBELLE_LONG
 * DATE_OUVERTURE
 * DATE_FERMETURE
 * DATE_INTERVENTION
 * N_COMMENTAIRE
 * LIBELLE_EDITION
 */
@Getter
@Setter
@ToString
public class ContratDataset {

    @CsvBindByName(column = "CONTRAT_ETABLISSEMENT")
    private String code;

    @CsvBindByName(column = "LIBELLE_EDITION")
    private String nom;

    @CsvBindByName(column = "DATE_FERMETURE")
    private String dateFin;
}
