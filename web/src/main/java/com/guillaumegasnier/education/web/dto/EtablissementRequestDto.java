package com.guillaumegasnier.education.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.ValidUai;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(name = "EtablissementRequest", description = "Informations sur un nouvel établissement")
public class EtablissementRequestDto {

    @ValidUai
    @JsonProperty(required = true)
    protected String uai;

    @ValidSiret
    protected String siret;

    protected String nom;

    @Size(max = 50)
    protected String adresse;

    @Size(max = 50)
    protected String complement;

    @Size(max = 5)
    @JsonProperty(value = "code_postal")
    protected String codePostal;

    @Size(max = 3)
    @JsonProperty(value = "code_nature")
    protected String codeNature;

    @JsonProperty(value = "code_etat", required = true)
    protected EtatEtablissement codeEtat;

    @Size(max = 5)
    @JsonProperty(value = "code_commune")
    protected String codeCommune;

    @Size(max = 2)
    @JsonProperty(value = "code_contrat")
    protected String codeContrat;

}
