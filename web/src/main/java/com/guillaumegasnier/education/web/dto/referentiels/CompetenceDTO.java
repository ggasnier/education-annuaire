package com.guillaumegasnier.education.web.dto.referentiels;

import com.guillaumegasnier.education.core.enums.CoeurMetier;

public record CompetenceDTO(
        CoeurMetier coeurMetier,
        String categorie,
        String nom) {
}
