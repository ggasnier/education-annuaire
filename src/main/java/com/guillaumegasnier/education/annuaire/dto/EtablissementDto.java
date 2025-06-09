package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder(
        {"uai", "nom", "siret",
                "code_nature", "nom_nature",
                "code_etat", "nom_etat",
                "adresse", "complement", "code_postal",
                "code_commune", "nom_commune",
                "code_departement", "nom_departement",
                "code_academie", "nom_academie",
                "code_region", "nom_region",
                "code_pays", "nom_pays"
        }
)
@Getter
@Setter
@Schema(name = "Etablissement", description = "Informations sur un établissement")
public class EtablissementDto extends EtablissementRequestDto {

    @JsonProperty(value = "nom_nature")
    protected String nomNature;

    @JsonProperty(value = "nom_etat")
    protected String nomEtat;

    @JsonProperty(value = "nom_commune")
    protected String nomCommune;

    @JsonProperty(value = "code_departement")
    protected String codeDepartement;
    @JsonProperty(value = "nom_departement")
    protected String nomDepartement;

    @JsonProperty(value = "code_region")
    protected String codeRegion;
    @JsonProperty(value = "nom_region")
    protected String nomRegion;

    @JsonProperty(value = "code_academie")
    protected String codeAcademie;
    @JsonProperty(value = "nom_academie")
    protected String nomAcademie;

    @JsonProperty(value = "code_pays")
    protected String codePays;
    @JsonProperty(value = "nom_pays")
    protected String nomPays;
}
