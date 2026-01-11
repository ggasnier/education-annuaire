package com.guillaumegasnier.education.shell.dto;

import com.guillaumegasnier.education.core.enums.Sport;

public record SportDTO(
        String uai,
        Sport sport,
        Sport.Categorie categorie) {
}
