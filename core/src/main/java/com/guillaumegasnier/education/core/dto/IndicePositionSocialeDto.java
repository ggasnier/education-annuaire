package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndicePositionSocialeDto implements IndicePositionSociale {

    @JsonProperty(value = "ecart_type")
    public Double ecartType;
    private Double indice;

}
