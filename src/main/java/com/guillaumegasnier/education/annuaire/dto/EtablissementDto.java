package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder(
        {"uai", "nom", "siret",
                "nature_code", "nature_nom",
                "etat_code", "etat_nom",
                "adresse", "complement", "code_postal",
                "commune_code", "commune_nom",
                "departement_code", "departement_nom",
                "academie_code", "academie_nom",
                "region_code", "region_nom",
                "pays_code", "pays_nom"
        }
)
@Getter
@Setter
@Schema(name = "Etablissement", description = "Informations sur un établissement")
public class EtablissementDto extends EtablissementRequestDto {

    @JsonProperty(value = "nature_nom")
    protected String nomNature;

    @JsonProperty(value = "etat_nom")
    protected String nomEtat;

    @JsonProperty(value = "commune_nom")
    protected String nomCommune;

    @JsonProperty(value = "departement_code")
    protected String codeDepartement;
    @JsonProperty(value = "departement_nom")
    protected String nomDepartement;

    @JsonProperty(value = "region_code")
    protected String codeRegion;
    @JsonProperty(value = "region_nom")
    protected String nomRegion;

    @JsonProperty(value = "academie_code")
    protected String codeAcademie;
    @JsonProperty(value = "academie_nom")
    protected String nomAcademie;

    @JsonProperty(value = "pays_code")
    protected String codePays;
    @JsonProperty(value = "pays_nom")
    protected String nomPays;
}
