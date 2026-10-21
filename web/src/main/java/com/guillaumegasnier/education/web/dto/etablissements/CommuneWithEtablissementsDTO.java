package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "CommuneWithEtablissements")
public record CommuneWithEtablissementsDTO(
        CommuneDTO commune,
        List<EtablissementDTO> etablissements) {
}
