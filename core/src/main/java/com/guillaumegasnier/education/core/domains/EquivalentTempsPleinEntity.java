package com.guillaumegasnier.education.core.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * num_ligne
 * Code région
 * Libellé région
 * Code académie
 * Libellé académie
 * Code département
 * Libellé département
 * Secteur
 * Année de la rentrée scolaire
 * Nature de l'établissement
 * Identifiant de l'établissement
 * Nom de l'établissement
 * ETP total
 * ETP de personnels de vie scolaire
 * ETP enseignants (hommes et femmes)
 * ETP de femmes enseignantes
 * ETP d'enseignants agrégés
 * ETP d'enseignants certifiés, PEPS
 * ETP d'enseignants PLP
 * ETP d'enseignants titulaires d'un autre corps
 * ETP d'enseignants non titulaires
 * ETP d'enseignants de moins de 35 ans
 * ETP d'enseignants de 35 à moins de 50 ans
 * ETP d'enseignants de 50 ans ou plus
 * ETP d'enseignants ayant une ancienneté dans l'établissement de moins de 2 ans
 * ETP d'enseignants ayant une ancienneté dans l'établissement de 2 ans à moins de 5 ans
 * ETP d'enseignants ayant une ancienneté dans l'établissement de 5 ans à moins de 8 ans
 * ETP d'enseignants ayant une ancienneté dans l'établissement de 8 ans ou plus
 * Géolocalisation
 */
@Getter
@Setter
@Entity
@Table(name = "etp")
public class EquivalentTempsPleinEntity {

    @EmbeddedId
    private EquivalentTempsPleinPK pk;

    @MapsId("uai")
    @OneToOne
    private EtablissementEntity etablissement;

    // ETP totat
    private Double etp;

    // ETP enseignants (hommes et femmes)
    private Double etpEnseignant;

    // ETP de personnels de vie scolaire
    private Double etpPersonnel;
}
