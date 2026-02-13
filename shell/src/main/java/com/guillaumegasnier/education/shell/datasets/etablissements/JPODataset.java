package com.guillaumegasnier.education.shell.datasets.etablissements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class JPODataset {

    private String uai;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private LocalTime heureDebut;

    private LocalTime heureFin;

    private String commentaire;
}
