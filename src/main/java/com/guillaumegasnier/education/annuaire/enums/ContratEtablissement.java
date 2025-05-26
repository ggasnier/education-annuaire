package com.guillaumegasnier.education.annuaire.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CONTRAT_ETABLISSEMENT,LIBELLE_COURT,LIBELLE_LONG,DATE_OUVERTURE,DATE_FERMETURE,DATE_INTERVENTION,N_COMMENTAIRE,LIBELLE_EDITION
 * 10,HORS CTR,HORS CONTRAT,,,03/12/2013,,Hors contrat
 * 20,SIMPLE TOT,CONTRAT SIMPLE TOUTES CLASSES,,,03/12/2013,,Contrat simple pour toutes les classes
 * 21,SIMPLE PAR,C0NTRAT SIMPLE POUR PARTIE DES CLASSES,,,03/12/2013,,Contrat simple pour une partie des classes
 * 30,ASSOC TOT,CONTRAT D'ASSOCIATION TOUTES CLASSES,,,03/12/2013,,Contrat d'association pour toutes les classes
 * 31,ASSOC PART,CONTRAT ASSOCIATION PARTIE DES CLASSES,,,03/12/2013,,Contrat d'association pour une partie des classes
 * 40,SI+AS TOT,CONTRAT TTES CLASSES SIMPLE+ASSOCIATION,,,03/12/2013,,Contrats simple et d'association pour toutes les classes
 * 41,SI+AS PART,CONTRAT PARTIE DES CLASSES SIMPLE+ASSOC,,,03/12/2013,,Contrats simple et d'association pour une partie des classes
 * 99,SS OBJET,SANS OBJET,,,03/12/2013,,Sans objet
 * **,QQSOIT CTR,QUEL QUE SOIT LE CONTRAT,,,03/12/2013,,Quel que soit le contrat
 * !!,INCONNU,INCONNU DU GESTIONNAIRE,,,03/12/2013,,Inconnu du gestionnaire
 * 50,RECONNU,RECONNU PAR L'ETAT,,,03/12/2013,,Reconnu par l'Etat
 * 60,SS CTR EA,SOUS CONTRAT ETABLISSEMENT AGRICOLE,,,25/04/2024,ajout pour la convergence SIEA-SIEN : modalité spécifique aux établissements agricoles privés sous contrat avec organismes UNREP- CNEAP- UNMFREO,Sous contrat établissement agricole
 */
@Getter
@AllArgsConstructor
public enum ContratEtablissement {

    HORS_CTR("10", "Hors contrat");

    private final String code;
    private final String nom;
}
