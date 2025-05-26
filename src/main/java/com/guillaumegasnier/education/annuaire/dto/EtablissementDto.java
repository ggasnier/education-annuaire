package com.guillaumegasnier.education.annuaire.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Etablissement", description = "Informations sur un établissement")
public class EtablissementDto extends EtablissementRequestDto {

    protected String nomNature;

    protected String nomEtat;

    protected String nomCommune;

    protected String codeDepartement;

    protected String nomDepartement;

    protected String codeRegion;

    protected String nomRegion;

    protected String codeAcademie;

    protected String nomAcademie;
}
