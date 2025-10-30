package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Partenaire {

    @JsonProperty(value = "nom_partenaire")
    private String nomPartenaire;

    @JsonProperty(value = "siret_partenaire")
    private String siretPartenaire;

    @JsonProperty(value = "habilitation_partenaire")
    private String habilitationPartenaire;
}