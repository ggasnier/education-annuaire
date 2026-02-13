package com.guillaumegasnier.education.web.dto.etablissements;

import java.time.LocalDate;
import java.time.LocalTime;

public record JPODto(LocalDate dateDebut,
                     LocalDate dateFin,
                     LocalTime heureDebut,
                     LocalTime heureFin,
                     String commentaire) {
}
