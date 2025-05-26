package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
}
