package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.extractOnisepId;
import static com.guillaumegasnier.education.shell.utils.ShellUtil.toNormalizedId;

/**
 * Même si le réseau CARIF-OREF appéle ça une formation, d'un point de vue LHEO on est sur une action de formation
 */
@Slf4j
@Getter
@Setter
@ToString
public class CarifFormationDataset implements Dataset {

    /**
     * Clé unique de la formation (pour envoi aux ministères éducatifs)
     */
    @JsonProperty(value = "cle_ministere_educatif")
    private String cleMinistereEducatif;

    /**
     * Code formation diplôme (education nationale)
     */
    private String cfd;

    /**
     * Lettre spécialité du code cfd
     */
    @JsonProperty(value = "cfd_specialite")
    private String cfdSpecialite;

    /**
     * BCN : cfd périmé (fermeture avant le 31 août de l'année courante)
     */
    @JsonProperty(value = "cfd_outdated")
    private boolean cfdOutdated;

    /**
     * Date de fermeture du cfd
     */
    @JsonProperty(value = "cfd_date_fermeture")
    private String cfdDateFermeture;

    /**
     * Code formation diplôme d'entrée (année 1 de l'apprentissage)
     */
    @JsonProperty(value = "cfd_entree")
    private String cfdEntree;

    /**
     * Code formacode
     */
    @JsonProperty(value = "code_formacode")
    private List<String> codeFormacode;

    /**
     * Libelle formacode
     */
    @JsonProperty(value = "libelle_formacode")
    private List<String> libelleFormacode;

    /**
     * Nom de l'académie
     */
    @JsonProperty(value = "nom_academie")
    private String nomAcademie;

    /**
     * Numéro de l'académie
     */
    @JsonProperty(value = "num_academie")
    private String numAcademie;

    /**
     * Code postal
     */
    @JsonProperty(value = "code_postal")
    private String codePostal;

    /**
     * Code commune INSEE
     */
    @JsonProperty(value = "code_commune_insee")
    private String codeCommuneInsee;

    @JsonProperty(value = "num_departement")
    private String numDepartement;

    @JsonProperty(value = "nom_departement")
    private String nomDepartement;

    private String region;

    private String localite;

    /**
     * Nom de la formation déclaratif
     */
    private String nom;

    /**
     * Intitulé comme transmis par RCO
     */
    @JsonProperty(value = "intitule_rco")
    private String intituleRco;

    /**
     * Intitulé long de la formation normalisé BCN
     */
    @JsonProperty(value = "intitule_long")
    private String intituleLong;

    /**
     * Intitulé court de la formation normalisé BCN
     */
    @JsonProperty(value = "intitule_court")
    private String intituleCourt;

    /**
     * Diplôme ou titre visé
     */
    private String diplome;

    /**
     * Niveau de la formation
     */
    private String niveau;

    /**
     * Url de redirection vers le site de l'ONISEP
     */
    @JsonProperty(value = "onisep_url")
    private String onisepUrl;

    /**
     * Intitulé éditorial l'ONISEP
     */
    @JsonProperty(value = "onisep_intitule")
    private String onisepIntitule;

    /**
     * Libellé poursuite étude l'ONISEP (séparateur ;)
     */
    @JsonProperty(value = "onisep_libelle_poursuite")
    private String onisepLibellePoursuite;

    /**
     * Lien vers site de l'ONISEP api
     */
    @JsonProperty(value = "onisep_lien_site_onisepfr")
    private String onisepLienSiteOnisepfr;

    /**
     * Disciplines ONISEP (séparateur ;)
     */
    @JsonProperty(value = "onisep_discipline")
    private String onisepDiscipline;

    /**
     * Domaine et sous domaine ONISEP (séparateur ;)
     */
    @JsonProperty(value = "onisep_domaine_sousdomaine")
    private String onisepDomaineSousdomaine;

    /**
     * Code RNCP
     */
    @JsonProperty(value = "rncp_code")
    private String rncpCode;
    /**
     * Intitulé du code RNCP
     */
    @JsonProperty(value = "rncp_intitule")
    private String rncpIntitule;
    /**
     * Le titre RNCP est éligible en apprentissage
     */
    @JsonProperty(value = "rncp_eligible_apprentissage")
    private boolean rncpEligibleApprentissage;
    @JsonProperty(value = "rncp_details")
    private RncpDetails rncpDetails;
    /**
     * Codes ROME
     */
    @JsonProperty(value = "rome_codes")
    private List<String> romeCodes;
    /**
     * Périodes de début de la formation
     */
    private List<String> periode;
    /**
     * Capacité d'accueil
     */
    private Integer capacite;
    /**
     * Durée de la formation en années
     */
    private String duree;
    /**
     * Durée incohérente avec les codes mefs
     */
    @JsonProperty(value = "duree_incoherente")
    private boolean dureeIncoherente;
    /**
     * Année de la formation (cursus)
     */
    private String annee;
    /**
     * Année incohérente avec les codes mefs
     */
    @JsonProperty(value = "annee_incoherente")
    private boolean anneeIncoherente;
    /**
     * Est publiée, la formation est éligible pour le catalogue
     */
    private boolean published;
    /**
     * La publication vers les plateformes est forcée (contournement catalogue non-éligible dans certains cas)
     */
    @JsonProperty(value = "forced_published")
    private boolean forcedPublished;
    /**
     * Date d'ajout en base de données
     */
    @JsonProperty(value = "created_at")
    private String createdAt;
    /**
     * Date de dernières mise à jour
     */
    @JsonProperty(value = "last_update_at")
    private String lastUpdateAt;
    /**
     * Latitude et longitude de l'établissement recherchable dans Idea
     */
    @JsonProperty(value = "idea_geo_coordonnees")
    private String ideaGeoCoordonneesEtablissement;
    @JsonProperty(value = "lieu_formation_geo_coordonnees")
    private String lieuFormationGeoCoordonnees;
    @JsonProperty(value = "lieu_formation_geo_coordonnees_computed")
    private String lieuFormationGeoCoordonneesComputed;
    private Integer distance;
    /**
     * Adresse du lieu de formation
     */
    @JsonProperty(value = "lieu_formation_adresse")
    private String lieuFormationAdresse;
    @JsonProperty(value = "lieu_formation_adresse_computed")
    private String lieuFormationAdresseComputed;
    @JsonProperty(value = "etablissement_lieu_formation_siret")
    private String etablissementLieuFormationSiret;
    /**
     * Id de formation RCO (id_formation + id_action + id_certifinfo)
     *
     * @deprecated
     */
    @JsonProperty(value = "id_rco_formation")
    private String idRcoFormation;
    /**
     * Identifiant de la formation
     */
    @JsonProperty(value = "id_formation")
    private String idFormation;
    /**
     * Identifiant des actions concaténés
     */
    @JsonProperty(value = "id_action")
    private String idAction;
    /**
     * Identifiant des actions concaténés
     */
    @JsonProperty(value = "ids_action")
    private List<String> idsAction = new ArrayList<>();
    /**
     * Identifiant certifInfo (unicité de la certification)
     */
    @JsonProperty(value = "id_certifinfo")
    private String idCertifinfo;
    /**
     * Tableau de tags (2020, 2021, etc.)
     */
    private List<String> tags = new ArrayList<>();
    /**
     * BCN : libelle court fusion table n_formation_diplome ou v_formation_diplome
     */
    @JsonProperty(value = "libelle_court")
    private String libelleCourt;
    /**
     * BCN : niveau formation diplôme
     */
    @JsonProperty(value = "niveau_formation_diplome")
    private String niveauFormationDiplome;
    /**
     * BCN : Codes MEF 10 caractères
     */
    @JsonProperty(value = "bcn_mefs_10")
    private List<ItemOfBcnMefs10> bcnMefs10 = new ArrayList<>();
    @JsonProperty(value = "distance_lieu_formation_etablissement_formateur")
    private Integer distanceLieuFormationEtablissementFormateur;
    @JsonProperty(value = "niveau_entree_obligatoire")
    private Integer codeNiveauEntree;
    @JsonProperty(value = "entierement_a_distance")
    private boolean entierementADistance;
    @JsonProperty(value = "france_competence_infos")
    private FranceCompetenceInfos franceCompetenceInfos;
    /**
     * Formation éligible au catalogue générale
     */
    @JsonProperty(value = "catalogue_published")
    private boolean cataloguePublished;
    @JsonProperty(value = "date_debut")
    private List<String> dateDebut = new ArrayList<>();
    @JsonProperty(value = "date_fin")
    private List<String> dateFin = new ArrayList<>();
    @JsonProperty(value = "id_RCO")
    private String idRCO;
    private String objectif;
    private String contenu;
    @JsonProperty(value = "etablissement_gestionnaire_actif")
    private String etablissementGestionnaireActif;
    @JsonProperty(value = "etablissement_formateur_actif")
    private String etablissementFormateurActif;
    @JsonProperty(value = "siret_actif")
    private String siretActif;
    @JsonProperty(value = "num_tel")
    private String numTel;
    @JsonProperty(value = "cle_me_remplace")
    private List<String> cleMeRemplace;
    @JsonProperty(value = "cle_me_remplace_par")
    private List<String> cleMeRemplacePar;
    @JsonProperty(value = "etablissement_lieu_siret_actif")
    private String etablissementLieuSiretActif;
    @JsonProperty(value = "etablissement_lieu_formation_uai")
    private String etablissementLieuFormationUai;
    @JsonProperty(value = "conditions_specifiques")
    private String conditionsSpecifiques;
    @JsonProperty(value = "effectif_minimal")
    private List<String> effectifMinimal = new ArrayList<>();
    @JsonProperty(value = "capacite_simultanee")
    private List<String> capaciteSimultanee = new ArrayList<>();
    @JsonProperty(value = "capacite_cumulee")
    private List<String> capaciteCumulee = new ArrayList<>();
    @JsonProperty(value = "referent_handicap")
    private String referentHandicap;
    @JsonProperty(value = "etablissement_gestionnaire_id")
    private String etablissementGestionnaireId;
    @JsonProperty(value = "etablissement_gestionnaire_siret")
    private String etablissementGestionnaireSiret;
    @JsonProperty(value = "etablissement_gestionnaire_enseigne")
    private String etablissementGestionnaireEnseigne;
    @JsonProperty(value = "etablissement_gestionnaire_uai")
    private String etablissementGestionnaireUai;
    @JsonProperty(value = "etablissement_gestionnaire_published")
    private boolean etablissementGestionnairePublished;
    @JsonProperty(value = "etablissement_gestionnaire_habilite_rncp")
    private boolean etablissementGestionnaireHabiliteRncp;
    @JsonProperty(value = "etablissement_gestionnaire_certifie_qualite")
    private boolean etablissementGestionnaireCertifieQualite;
    @JsonProperty(value = "etablissement_gestionnaire_adresse")
    private String etablissementGestionnaireAdresse;
    @JsonProperty(value = "etablissement_gestionnaire_code_postal")
    private String etablissementGestionnaireCodePostal;
    @JsonProperty(value = "etablissement_gestionnaire_code_commune_insee")
    private String etablissementGestionnaireCodeCommuneInsee;
    @JsonProperty(value = "etablissement_gestionnaire_localite")
    private String etablissementGestionnaireLocalite;
    @JsonProperty(value = "etablissement_gestionnaire_complement_adresse")
    private String etablissementGestionnaireComplementAdresse;
    @JsonProperty(value = "etablissement_gestionnaire_cedex")
    private String etablissementGestionnaireCedex;
    @JsonProperty(value = "etablissement_gestionnaire_entreprise_raison_sociale")
    private String etablissementGestionnaireEntrepriseRaisonSociale;
    @JsonProperty(value = "geo_coordonnees_etablissement_gestionnaire")
    private String geoCoordonneesEtablissementGestionnaire; // TODO
    @JsonProperty(value = "etablissement_gestionnaire_region")
    private String etablissementGestionnaireRegion;
    @JsonProperty(value = "etablissement_gestionnaire_num_departement")
    private String etablissementGestionnaireNumDepartement;
    @JsonProperty(value = "etablissement_gestionnaire_nom_departement")
    private String etablissementGestionnaireNomDepartement;
    @JsonProperty(value = "etablissement_gestionnaire_nom_academie")
    private String etablissementGestionnaireNomAcademie;
    @JsonProperty(value = "etablissement_gestionnaire_num_academie")
    private String etablissementGestionnaireNumAcademie;
    @JsonProperty(value = "etablissement_gestionnaire_siren")
    private String etablissementGestionnaireSiren;
    @JsonProperty(value = "etablissement_gestionnaire_nda")
    private String etablissementGestionnaireNda;
    @JsonProperty(value = "etablissement_gestionnaire_date_creation")
    private String etablissementGestionnaireDateCreation;
    @JsonProperty(value = "etablissement_formateur_id")
    private String etablissementFormateurId;
    @JsonProperty(value = "etablissement_formateur_siret")
    private String etablissementFormateurSiret;
    @JsonProperty(value = "etablissement_formateur_enseigne")
    private String etablissementFormateurEnseigne;
    @JsonProperty(value = "etablissement_formateur_uai")
    private String etablissementFormateurUai;
    @JsonProperty(value = "etablissement_formateur_published")
    private boolean etablissementFormateurPublished;
    @JsonProperty(value = "etablissement_formateur_habilite_rncp")
    private boolean etablissementFormateurHabiliteRncp;
    @JsonProperty(value = "etablissement_formateur_certifie_qualite")
    private boolean etablissementFormateurCertifieQualite;
    @JsonProperty(value = "etablissement_formateur_adresse")
    private String etablissementFormateurAdresse;
    @JsonProperty(value = "etablissement_formateur_code_postal")
    private String etablissementFormateurCodePostal;
    @JsonProperty(value = "etablissement_formateur_code_commune_insee")
    private String etablissementFormateurCodeCommuneInsee;
    @JsonProperty(value = "etablissement_formateur_localite")
    private String etablissementFormateurLocalite;
    @JsonProperty(value = "etablissement_formateur_complement_adresse")
    private String etablissementFormateurComplementAdresse;
    @JsonProperty(value = "etablissement_formateur_cedex")
    private String etablissementFormateurCedex;
    @JsonProperty(value = "etablissement_formateur_entreprise_raison_sociale")
    private String etablissementFormateurEntrepriseRaisonSociale;
    @JsonProperty(value = "geo_coordonnees_etablissement_formateur")
    private String geoCoordonneesEtablissementFormateur;
    @JsonProperty(value = "etablissement_formateur_region")
    private String etablissementFormateurRegion;
    @JsonProperty(value = "etablissement_formateur_num_departement")
    private String etablissementFormateurNumDepartement;
    @JsonProperty(value = "etablissement_formateur_nom_departement")
    private String etablissementFormateurNomDepartement;
    @JsonProperty(value = "etablissement_formateur_nom_academie")
    private String etablissementFormateurNomAcademie;
    @JsonProperty(value = "etablissement_formateur_num_academie")
    private String etablissementFormateurNumAcademie;
    @JsonProperty(value = "etablissement_formateur_siren")
    private String etablissementFormateurSiren;
    @JsonProperty(value = "etablissement_formateur_nda")
    private String etablissementFormateurNda;
    @JsonProperty(value = "etablissement_formateur_date_creation")
    private String etablissementFormateurDateCreation;
    @JsonProperty(value = "etablissement_reference")
    private String etablissementReference;
    @JsonProperty(value = "etablissement_reference_published")
    private boolean etablissementReferencePublished;
    @JsonProperty(value = "etablissement_reference_habilite_rncp")
    private boolean etablissementReferenceHabiliteRncp;
    @JsonProperty(value = "etablissement_reference_certifie_qualite")
    private boolean etablissementReferenceCertifieQualite;
    @JsonProperty(value = "etablissement_reference_date_creation")
    private String etablissementReferenceDateCreation;

    public Boolean isCertifiante() {
        return rncpCode != null && !rncpCode.isBlank();
    }

    public String getFormationOnisepId() {
        return extractOnisepId(onisepUrl).toString();
    }

    public Long getFormationId() {
        if (getFormationOnisepId() != null)
            return toNormalizedId("AF", getFormationOnisepId());
        else {
            if (cfd != null)
                return toNormalizedId("cfd", cfd);
            return null;
        }
    }

}
