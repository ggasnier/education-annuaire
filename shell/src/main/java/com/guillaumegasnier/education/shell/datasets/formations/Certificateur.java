package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Certificateur {

    private String certificateur;

    @JsonProperty(value = "siret_certificateur")
    private String siretCertificateur;
}
