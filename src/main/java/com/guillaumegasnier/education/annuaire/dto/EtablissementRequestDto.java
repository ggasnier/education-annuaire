package com.guillaumegasnier.education.annuaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.annuaire.enums.EtatEtablissement;
import com.guillaumegasnier.education.annuaire.validations.ValidSiret;
import com.guillaumegasnier.education.annuaire.validations.ValidUai;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EtablissementRequest", description = "Informations sur un nouvel établissement")
public class EtablissementRequestDto {

    @ValidUai
    @JsonProperty(required = true)
    protected String uai;

    @ValidSiret
    protected String siret;

    protected String nom;

    protected String adresse;

    protected String complement;

    @JsonProperty(value = "code_postal")
    protected String codePostal;

    @JsonProperty(value = "nature_code")
    protected String codeNature;

    @JsonProperty(value = "etat_code")
    protected EtatEtablissement codeEtat;

    @JsonProperty(value = "commune_code")
    protected String codeCommune;

}
