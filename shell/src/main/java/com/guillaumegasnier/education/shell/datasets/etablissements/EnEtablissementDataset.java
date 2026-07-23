package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.Contact;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatAdresse;

/**
 * Type_etablissement
 * Statut_public_prive
 * Voie_generale
 * Voie_technologique
 * Voie_professionnelle
 * <p>
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
 */
@Getter
@Setter
@ToString
public final class EnEtablissementDataset implements EtablissementDataset {

    @CsvBindByName(column = "Identifiant_de_l_etablissement")
    private String uai;
    @CsvBindByName(column = "SIREN_SIRET")
    private String siret;
    @CsvBindByName(column = "Nom_etablissement")
    private String nom;
    @CsvBindByName(column = "Statut_public_prive")
    private String statutPublicPrive;
    @CsvBindByName(column = "Adresse_1")
    private String adresse;
    @CsvBindByName(column = "Adresse_2")
    private String complement;
    @CsvBindByName(column = "Code_postal")
    private String codePostal;
    @CsvBindByName(column = "code_nature")
    private String codeNature;
    @CsvBindByName(column = "Code_type_contrat_prive")
    private String codeContrat;
    @CsvBindByName(column = "Code_commune")
    private String codeCommune;
    @CsvBindByName(column = "Nom_commune")
    private String nomCommune;
    @CsvBindByName(column = "etat")
    private String etat;
    @CsvBindByName(column = "Telephone")
    private String contactTelephone;
    @CsvBindByName(column = "Mail")
    private String contactMail;
    @CsvBindByName(column = "Web")
    private String contactWeb;
    @CsvBindByName(column = "date_ouverture")
    private String dateOuverture;
    @CsvBindByName(column = "Restauration")
    private String restauration;
    @CsvBindByName(column = "Hebergement")
    private String hebergement;
    @CsvBindByName(column = "ULIS")
    private String ulis;
    @CsvBindByName(column = "Apprentissage")
    private String apprentissage;
    @CsvBindByName(column = "Segpa")
    private String segpa;
    @CsvBindByName(column = "Section_arts")
    private String sectionArts;
    @CsvBindByName(column = "Section_cinema")
    private String sectionCinema;
    @CsvBindByName(column = "Section_theatre")
    private String sectionTheatre;
    @CsvBindByName(column = "Section_sport")
    private String sectionSport;
    @CsvBindByName(column = "Section_internationale")
    private String sectionInternationale;
    @CsvBindByName(column = "Section_europeenne")
    private String sectionEuropeenne;
    @CsvBindByName(column = "Lycee_Agricole")
    private String lyceeAgricole;
    @CsvBindByName(column = "Lycee_militaire")
    private String lyceeMilitaire;
    @CsvBindByName(column = "Lycee_des_metiers")
    private String lyceeDesMetiers;
    @CsvBindByName(column = "Appartenance_Education_Prioritaire")
    private String educationPrioritaire;

    @Override
    public LocalDate getDateOuverture() {
        if (dateOuverture == null) return null;
        return LocalDate.parse(dateOuverture);
    }

    @Override
    public String getAdresse() {
        return formatAdresse(adresse);
    }

    @Override
    public EtablissementDataset cloneWithUai(String uai) {
        try {
            EnEtablissementDataset copy = (EnEtablissementDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public EtablissementDataset cloneWithSiret(String siret) {
        try {
            EnEtablissementDataset copy = (EnEtablissementDataset) this.clone();
            copy.setSiret(siret);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String getSiret() {
        if (siret == null) return null;
        if (siret.isBlank()) return null;
        return siret;
    }

    @Override
    public String getComplement() {
        return formatAdresse(complement);
    }

    @Override
    public Boolean isActif() {
        return true;
    }

    @Override
    public String getEducationPrioritaire() {
        if (educationPrioritaire == null || educationPrioritaire.isBlank()) return null;
        return educationPrioritaire;
    }

    @Override
    public Secteur getSecteur() {
        if (statutPublicPrive != null) {
            if (statutPublicPrive.equals("Public")) {
                return Secteur.PU;
            } else if (statutPublicPrive.equals("Privé")) {
                return Secteur.PV;
            }
        }
        return null;
    }

    @Override
    public List<ContactEtablissementDataset> getContacts() {
        List<ContactEtablissementDataset> contacts = new ArrayList<>();

        if (contactTelephone != null && !contactTelephone.isEmpty())
            ContactEtablissementDataset.of(Contact.TEL, contactTelephone).ifPresent(contacts::add);

        if (contactMail != null && !contactMail.isEmpty())
            ContactEtablissementDataset.of(Contact.EMAIL, contactMail).ifPresent(contacts::add);

        if (contactWeb != null && !contactWeb.isEmpty())
            ContactEtablissementDataset.of(Contact.WEB, contactWeb).ifPresent(contacts::add);

        return contacts;
    }

    @Override
    public Set<OptionEtablissement> getOptions() {
        record Indicateur(String valeur, OptionEtablissement option) {
        }

        Set<OptionEtablissement> options = Stream.of(
                        new Indicateur(restauration, OptionEtablissement.RESTAURATION),
                        new Indicateur(hebergement, OptionEtablissement.HEBERGEMENT),
                        new Indicateur(ulis, OptionEtablissement.ULIS),
                        new Indicateur(apprentissage, OptionEtablissement.APPRENTISSAGE),
                        new Indicateur(segpa, OptionEtablissement.SEGPA),
                        new Indicateur(sectionArts, OptionEtablissement.SECTION_ARTS),
                        new Indicateur(sectionCinema, OptionEtablissement.SECTION_CINEMA),
                        new Indicateur(sectionTheatre, OptionEtablissement.SECTION_THEATRE),
                        new Indicateur(sectionSport, OptionEtablissement.SECTION_SPORT),
                        new Indicateur(sectionInternationale, OptionEtablissement.SECTION_INTERNATIONALE),
                        new Indicateur(sectionEuropeenne, OptionEtablissement.SECTION_EUROPEENNE),
                        new Indicateur(lyceeAgricole, OptionEtablissement.LYCEE_AGRICOLE),
                        new Indicateur(lyceeMilitaire, OptionEtablissement.LYCEE_MILITAIRE),
                        new Indicateur(lyceeDesMetiers, OptionEtablissement.LYCEE_DES_METIERS)
                )
                .filter(i -> "1".equals(i.valeur()))
                .map(Indicateur::option)
                .collect(Collectors.toCollection(HashSet::new));

        if (educationPrioritaire != null) {
            if ("REP".equals(educationPrioritaire)) options.add(OptionEtablissement.REP);
            else if ("REP+".equals(educationPrioritaire)) options.add(OptionEtablissement.REPP);
        }

        return options;
    }
}
