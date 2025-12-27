package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.web.dto.EtablissementDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NatureWithEtablissementsDto {

    private NatureDto nature;

    private List<EtablissementDto> etablissements;
}
