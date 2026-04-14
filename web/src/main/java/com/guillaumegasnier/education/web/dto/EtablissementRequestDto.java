package com.guillaumegasnier.education.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.validations.ValidSiret;
import com.guillaumegasnier.education.core.validations.etablissements.ValidUai;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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

    @Size(max = 5)
    @JsonProperty(value = "code_commune")
    protected String codeCommune;

    @Size(max = 2)
    @JsonProperty(value = "code_contrat")
    protected String codeContrat;

    @JsonProperty(value = "date_ouverture")
    protected LocalDate dateOuverture;

    @JsonProperty(value = "date_fermeture")
    protected LocalDate dateFermeture;

    @Size(min = 2, max = 2)
    @JsonProperty(value = "code_secteur")
    protected String codeSecteur;

    protected Boolean actif;

}
