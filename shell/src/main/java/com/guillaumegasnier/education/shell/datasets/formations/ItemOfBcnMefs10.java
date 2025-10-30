package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemOfBcnMefs10 {

    private String mef10;

    private Modalite modalite;

    @JsonProperty(value = "date_fermeture")
    private String dateFermeture;
}
