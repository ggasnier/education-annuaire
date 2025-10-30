package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RncpDetails {

    @JsonProperty(value = "date_debut_validite_enregistrement")
    private String dateFinValiditeEnregistrement;

    @JsonProperty(value = "active_inactive")
    private String activeInactive;

    @JsonProperty(value = "etat_fiche_rncp")
    private String etatFicheRncp;

    @JsonProperty(value = "niveau_europe")
    private String niveauEurope;

    @JsonProperty(value = "code_type_certif")
    private String codeTypeCertif;

    @JsonProperty(value = "type_certif")
    private String typeCertif;

    @JsonProperty(value = "ancienne_fiche")
    private List<String> ancienneFiche;

    @JsonProperty(value = "nouvelle_fiche")
    private List<String> nouvelleFiche;

    private int demande;

    private List<Certificateur> certificateurs;

    @JsonProperty(value = "nsf_code")
    private String nsfCode;

    @JsonProperty(value = "nsf_libelle")
    private String nsfLibelle;

    private List<Rome> romes;

    @JsonProperty(value = "blocs_competences")
    private List<BlocCompetence> blocsCompetences;

    @JsonProperty(value = "voix_acces")
    private String voixAcces;

    private List<Partenaire> partenaires;

    @JsonProperty(value = "rncp_outdated")
    private boolean rncpOutdated;

}
