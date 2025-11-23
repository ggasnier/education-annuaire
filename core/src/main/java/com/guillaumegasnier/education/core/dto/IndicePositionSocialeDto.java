package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicePositionSocialeDto implements IndicePositionSociale {

    @JsonProperty(value = "ecart_type")
    public Double ecartType;

    private Double indice;

    @JsonProperty(value = "i_n_pv")
    private Double indiceNationalPrive;
    @JsonProperty(value = "i_n_pu")
    private Double indiceNationalPublic;
    @JsonProperty(value = "i_n")
    private Double indiceNational;

    @JsonProperty(value = "i_a_pv")
    private Double indiceAcademiePrive;
    @JsonProperty(value = "i_a_pu")
    private Double indiceAcademiePublic;
    @JsonProperty(value = "i_a")
    private Double indiceAcademie;

    @JsonProperty(value = "i_d_pv")
    private Double indiceDepartementPrive;
    @JsonProperty(value = "i_d_pu")
    private Double indiceDepartementPublic;
    @JsonProperty(value = "i_d")
    private Double indiceDepartement;
}
