package com.guillaumegasnier.education.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.ValidUai;
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

    @JsonProperty(value = "code_nature")
    protected String codeNature;

    @JsonProperty(value = "code_etat")
    protected EtatEtablissement codeEtat;

    @JsonProperty(value = "code_commune")
    protected String codeCommune;

}
