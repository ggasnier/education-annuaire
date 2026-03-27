package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sources {

    EN("Education Nationale"),
    ESR(""),
    ONISEP(""),
    CDC(""),
    FT("France Travail"),
    FC("France compétences"),
    CARIF("Carif-Oref"),
    MTEDS("Ministère du Travail du Plein emploi et de l'Insertion"),
    INSEE("Institut national de la statistique et des études économiques"),
    MASA("Ministère de l'Agriculture et de la Souveraineté alimentaire"),
    AUTRE("");

    private final String nom;
}
