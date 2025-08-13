package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class InformationsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "sections_sportives", required = true)
    private List<String> sectionsSportives = new ArrayList<>();

    @JsonProperty(value = "sections_sports_etudes", required = true)
    private List<String> sectionSportsEtudes = new ArrayList<>();

}
