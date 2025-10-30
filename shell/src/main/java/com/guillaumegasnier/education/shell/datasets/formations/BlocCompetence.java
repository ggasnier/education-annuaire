package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlocCompetence {

    @JsonProperty(value = "numero_bloc")
    private String numeroBloc;

    private String intitule;

    @JsonProperty(value = "liste_competences")
    private String listeCompetences;

    @JsonProperty(value = "modalites_evaluation")
    private String modalitesEvaluation;
}
