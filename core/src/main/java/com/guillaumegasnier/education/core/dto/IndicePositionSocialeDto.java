package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndicePositionSocialeDto implements IndicePositionSociale, Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @JsonProperty(value = "ecart_type")
    public Double ecartType;

    private Double indice;

}
