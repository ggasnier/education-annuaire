package com.guillaumegasnier.education.shell.dto.referentiels;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class CertificationDTO {

    private String code;
    private String nom;
    private Boolean actif; // ACTIF
    private String nouveauCode;
    private String etat;
    private String typologieDiplome; // à mettre en enum
    private String activitesVisees; // ACTIVITES_VISEES
    private String capacitesAttestees; // CAPACITES_ATTESTEES
    private String secteursActivite; // SECTEURS_ACTIVITE
    private String typeEmploiAccessibles; // TYPE_EMPLOI_ACCESSIBLES
    private String prerequisEntreeFormation; // PREREQUIS_ENTREE_FORMATION
    private String reglementationsActivites; // REGLEMENTATIONS_ACTIVITES
    private Boolean accessibleNouvelleCaledonie; // ACCESSIBLE_NOUVELLE_CALEDONIE
    private Boolean accessiblePolynesieFrancaise; // ACCESSIBLE_POLYNESIE_FRANCAISE
    private String publicationDecret; // PUBLICATION_DECRET_CREATION
    private String url; // LIEN_URL_DESCRIPTION
    private String objectifsContexte; // OBJECTIFS_CONTEXTE


    /**
     * Système CEC : 1 à 8
     */
    private int niveau;

    /**
     * Nomenclature des Spécialités de Formation (NSF)
     */
    private List<String> nsfList = new ArrayList<>();

    /**
     * Métiers (ROME)
     */
    private List<String> romeList = new ArrayList<>();

    public String getUrl() {
        if (url == null || url.isBlank()) {
            return null;
        }
        Pattern pattern = Pattern.compile("https?://[^\\s,;|]+");
        Matcher matcher = pattern.matcher(url);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group());
        }
        return urls.isEmpty() ? null : String.join(",", urls);
    }

    //null ,2085
    //Titre ingénieur,600
    //MASTER,296
    //TP,265    Titre professionnel
    //Autre,234
    //Licence Professionnelle,171
    //BUT,170
    //CAP,143
    //BAC PRO,104
    //CS,101 Certificat de spécialisation
    //DIPLOVIS,95
    //BTS,92
    //DipViGrM,91
    //LICENCE,75
    //Grade_Licence,67
    //Grade_Master,64
    //DEJEPS,56
    //DE,52
    //BPJEPS,50
    //BP,47
    //DipViGrL,43
    //DEUST,42
    //DESJEPS,42
    //DSP,27
    //BMA,17
    //BTSA,15
    //DNMADE,14
    //CAPA,9
    //DNSP,7
    //BPA,6
    //BA,6
    //CA,3
    //BTSMarit,2
    //DEEA,1
    //CAPD,1
    //DNA,1
    //DOCTORAT,1
    //CPJEPS,1
    //DNSEP,1
    public String getTypologieDiplome() {
        ;
        if (typologieDiplome == null || typologieDiplome.isBlank()) {
            return null;
        }
        return typologieDiplome
                .replaceAll("é", "E")
                .replaceAll(" ", "_")
                .toUpperCase(Locale.ROOT);
    }

}
