package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EtablissementDetailsDto {

    private EtablissementDto etablissement;

    private List<OptionDto> options;

    private List<SpecialiteBac> specialites;

    private List<LangueWithCategorieDto> langues;

    private List<SportWithCategorieDto> sports;

    private List<MetadataDto> metadatas;
}
