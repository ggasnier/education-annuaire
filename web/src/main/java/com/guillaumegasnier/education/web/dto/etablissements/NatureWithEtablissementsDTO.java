package com.guillaumegasnier.education.web.dto.etablissements;

import java.util.List;

public record NatureWithEtablissementsDTO(String NomNature,
                                          List<EtablissementDTO> etablissements) {
}
