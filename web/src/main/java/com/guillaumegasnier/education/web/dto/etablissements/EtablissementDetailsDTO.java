package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.web.dto.formations.ActionFormationDTO;

import java.util.List;

public record EtablissementDetailsDTO(EtablissementDTO etablissement,
                                      List<OptionWithCategorieDTO> options,
                                      List<SpecialiteBac> specialites,
                                      List<LangueWithCategorieDTO> langues,
                                      List<SportWithCategorieDTO> sports,
                                      List<ContactDTO> contacts,
                                      List<JPODto> journees,
                                      List<IndicesPositionSocialeDTO> ips,
                                      List<IndicateurValeurAjouteeDTO> iva,
                                      List<Internat> internats,
                                      List<ActionFormationDTO> formations) {
}
