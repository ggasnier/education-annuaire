package com.guillaumegasnier.education.web.dto.etablissements;

import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.web.dto.EtablissementDto;

import java.util.List;

public record EtablissementDetailsDto(

        EtablissementDto etablissement,

        List<OptionWithCategorieDto> options,

        List<SpecialiteBac> specialites,

        List<LangueWithCategorieDto> langues,

        List<SportWithCategorieDto> sports,

        List<ContactDto> contacts,

        List<MetadataDto> metadatas) {
}
