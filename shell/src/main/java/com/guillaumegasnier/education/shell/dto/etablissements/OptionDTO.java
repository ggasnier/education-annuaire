package com.guillaumegasnier.education.shell.dto.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.validations.etablissements.ValidUai;

public record OptionDTO(
        @ValidUai
        String uai,
        OptionEtablissement option,
        String nom) {
}
