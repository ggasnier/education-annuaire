package com.guillaumegasnier.education.web.dto.etablissements;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "IPS", description = "Informations sur l'IPS d'un établissement")
public record IPSDto(
        String uai,
        int annee,
        String codeCategorie,
        String nomCategorie,
        Double valeur,
        Double ecartType
) {


}
