package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.shell.datasets.CarifDataset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CarifEtablissementDataset extends CarifDataset implements EtablissementDataset {

    @JsonProperty("siege_social")
    private Boolean siegeSocial;

    @JsonProperty("etablissement_siege_id")
    private String etablissementSiegeId;

    @JsonProperty("etablissement_siege_siret")
    private String etablissementSiegeSiret;

    @JsonProperty("siret")
    private String siret;

    @JsonProperty("siren")
    private String siren;

    @JsonProperty("nda")
    private String numeroDeclarationActivite;

    @JsonProperty("naf_code")
    private String nafCode;

    @JsonProperty("naf_libelle")
    private String nafLibelle;

    @JsonProperty("tranche_effectif_salarie")
    private String trancheEffectifSalarie;

    @JsonProperty("date_creation")
    private String dateCreation;

    @JsonProperty("date_mise_a_jour")
    private String dateMiseAJour;

    @JsonProperty("diffusable_commercialement")
    private Boolean diffusableCommercialement;

    @JsonProperty("enseigne")
    private String enseigne;

    @JsonProperty("onisep_nom")
    private String onisepNom;

    @JsonProperty("onisep_url")
    private String onisepUrl;

    @JsonProperty("onisep_code_postal")
    private String onisepCodePostal;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("numero_voie")
    private String numeroVoie;

    @JsonProperty("type_voie")
    private String typeVoie;

    @JsonProperty("nom_voie")
    private String nomVoie;

    @JsonProperty("complement_adresse")
    private String complement;

    @JsonProperty("code_postal")
    private String codePostal;

    @JsonProperty("num_departement")
    private String numDepartement;

    @JsonProperty("nom_departement")
    private String nomDepartement;

    @JsonProperty("localite")
    private String localite;

    @JsonProperty("code_insee_localite")
    private String codeCommune;

    @JsonProperty("cedex")
    private String cedex;

    @JsonProperty("geo_coordonnees")
    private String geoCoordonnees;

    @JsonProperty("date_fermeture")
    private String dateFermeture;

    @JsonProperty("ferme")
    private Boolean ferme;

    @JsonProperty("region_implantation_code")
    private String regionImplantationCode;

    @JsonProperty("region_implantation_nom")
    private String regionImplantationNom;

    @JsonProperty("commune_implantation_code")
    private String communeImplantationCode;

    @JsonProperty("commune_implantation_nom")
    private String communeImplantationNom;

    @JsonProperty("pays_implantation_code")
    private String paysImplantationCode;

    @JsonProperty("pays_implantation_nom")
    private String paysImplantationNom;

    @JsonProperty("num_academie")
    private Integer numAcademie;

    @JsonProperty("nom_academie")
    private String nomAcademie;

    @JsonProperty("uai")
    private String uai;

    @JsonProperty("uai_valide")
    private Boolean uaiValide;

    @JsonProperty("uais_potentiels")
    private String uaisPotentiels;

    @JsonProperty("info_datagouv_ofs")
    private String infoDatagouvOfs;

    @JsonProperty("info_datagouv_ofs_info")
    private String infoDatagouvOfsInfo;

    @JsonProperty("info_qualiopi_info")
    private String infoQualiopiInfo;

    @JsonProperty("api_entreprise_reference")
    private Boolean apiEntrepriseReference;

    @JsonProperty("entreprise_siren")
    private String entrepriseSiren;

    @JsonProperty("entreprise_procedure_collective")
    private Boolean entrepriseProcedureCollective;

    @JsonProperty("entreprise_enseigne")
    private String entrepriseEnseigne;

    @JsonProperty("entreprise_numero_tva_intracommunautaire")
    private String entrepriseNumeroTva;

    @JsonProperty("entreprise_code_effectif_entreprise")
    private String entrepriseCodeEffectif;

    @JsonProperty("entreprise_forme_juridique_code")
    private String entrepriseFormeJuridiqueCode;

    @JsonProperty("entreprise_forme_juridique")
    private String entrepriseFormeJuridique;

    @JsonProperty("entreprise_raison_sociale")
    private String entrepriseRaisonSociale;

    @JsonProperty("entreprise_nom_commercial")
    private String entrepriseNomCommercial;

    @JsonProperty("entreprise_capital_social")
    private String entrepriseCapitalSocial;

    @JsonProperty("entreprise_date_creation")
    private String entrepriseDateCreation;

    @JsonProperty("entreprise_date_radiation")
    private String entrepriseDateRadiation;

    @JsonProperty("entreprise_naf_code")
    private String entrepriseNafCode;

    @JsonProperty("entreprise_naf_libelle")
    private String entrepriseNafLibelle;

    @JsonProperty("entreprise_date_fermeture")
    private String entrepriseDateFermeture;

    @JsonProperty("entreprise_ferme")
    private Boolean entrepriseFerme;

    @JsonProperty("entreprise_siret_siege_social")
    private String entrepriseSiretSiegeSocial;

    @JsonProperty("entreprise_nom")
    private String entrepriseNom;

    @JsonProperty("entreprise_prenom")
    private String entreprisePrenom;

    @JsonProperty("entreprise_categorie")
    private String entrepriseCategorie;

    @JsonProperty("entreprise_tranche_effectif_salarie")
    private String entrepriseTrancheEffectifSalarie;

    @JsonProperty("formations_attachees")
    private String formationsAttachees;

    @JsonProperty("formations_ids")
    private List<String> formationsIds = new ArrayList<>();

    @JsonProperty("formations_uais")
    private String formationsUais;

    @JsonProperty("ds_id_dossier")
    private String dsIdDossier;

    @JsonProperty("ds_questions_siren")
    private String dsQuestionsSiren;

    @JsonProperty("ds_questions_nom")
    private String dsQuestionsNom;

    @JsonProperty("ds_questions_uai")
    private String dsQuestionsUai;

    @JsonProperty("ds_questions_has_agrement_cfa")
    private Boolean dsQuestionsHasAgrementCfa;

    @JsonProperty("ds_questions_has_certificaton_2015")
    private Boolean dsQuestionsHasCertification2015;

    @JsonProperty("ds_questions_has_ask_for_certificaton")
    private Boolean dsQuestionsHasAskForCertification;

    @JsonProperty("ds_questions_ask_for_certificaton_date")
    private String dsQuestionsAskForCertificationDate;

    @JsonProperty("ds_questions_declaration_code")
    private String dsQuestionsDeclarationCode;

    @JsonProperty("ds_questions_has_2020_training")
    private Boolean dsQuestionsHas2020Training;

    @JsonProperty("certifie_qualite")
    private Boolean certifieQualite;

    @JsonProperty("published")
    private Boolean published;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("last_update_at")
    private String lastUpdateAt;

    @JsonProperty("update_error")
    private String updateError;

    @JsonProperty("tags")
    private List<String> tags = new ArrayList<>();

    @JsonProperty("rco_uai")
    private String rcoUai;

    @JsonProperty("rco_adresse")
    private String rcoAdresse;

    @JsonProperty("rco_code_postal")
    private String rcoCodePostal;

    @JsonProperty("rco_code_insee_localite")
    private String rcoCodeInseeLocalite;

    @JsonProperty("rco_geo_coordonnees")
    private String rcoGeoCoordonnees;

    @JsonProperty("idcc")
    private String idcc;

    @JsonProperty("opco_nom")
    private String opcoNom;

    @JsonProperty("opco_siren")
    private String opcoSiren;

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            CarifEtablissementDataset copy = (CarifEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            CarifEtablissementDataset copy = (CarifEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // todo
    @Override
    public UUID getId() {
        if (getUai() != null)
            return UUID.nameUUIDFromBytes(getUai().getBytes());
        else if (numeroDeclarationActivite != null)
            return UUID.nameUUIDFromBytes(numeroDeclarationActivite.getBytes());
        else {

            return null;
        }
    }

    @Override
    public String getUai() {
        if (uai == null) return "";
        return uai.trim();
    }

    @Override
    public String getNom() {
        if (onisepNom != null) {
            return onisepNom.trim();
        } else {
            return entrepriseRaisonSociale;
        }
    }

    @Override
    public String getAdresse() {
        StringBuilder sb = new StringBuilder();

        if (numeroVoie != null) {
            sb.append(numeroVoie.trim());
            sb.append(" ");
        }

        if (typeVoie != null) {
            sb.append(typeVoie.trim());
            sb.append(" ");
        }

        if (nomVoie != null) {
            sb.append(nomVoie.trim());
            sb.append(" ");
        }


        return sb.toString();
    }

    @Override
    public String getCodePays() {
        return "";
    }

    @Override
    public LocalDate getDateFermeture() {
        try {
            return LocalDate.parse(dateFermeture);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
