package com.guillaumegasnier.education.shell.dto.referentiels;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CertificationDTO {

    private String code;
    private String nom;
    private Boolean actif;
    private String nouveauCode;
    private String typologieDiplome; // à mettre en enum
    private String activitesVisees; // ACTIVITES_VISEES
    private String capacitesAttestees; // CAPACITES_ATTESTEES
    private String secteursActivite; // SECTEURS_ACTIVITE
    private String typeEmploiAccessibles; // TYPE_EMPLOI_ACCESSIBLES
    private String prerequisEntreeFormation; // PREREQUIS_ENTREE_FORMATION
    /**
     * Système CEC : 1 à 8
     */
    private int niveau;

    /**
     * Nomenclature des Spécialités de Formation (NSF)
     */
    private List<String> nsfList = new ArrayList<>();

    /**
     *
     */
    private List<String> romeList = new ArrayList<>();

}
