package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.LangueWithCategorieDto;
import com.guillaumegasnier.education.web.dto.etablissements.OptionDto;
import com.guillaumegasnier.education.web.dto.etablissements.SportWithCategorieDto;

import java.util.List;
import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDto> findEtablissementByUai(String uai);

    List<OptionDto> getOptionListByUai(String uai);

    List<SpecialiteBac> getSpecialiteListByUai(String uai);

    List<LangueWithCategorieDto> getLangueListByUai(String uai);

    Optional<EtablissementDto> createEtablissement(EtablissementRequestDto dto);

    List<SportWithCategorieDto> getSportListByUai(String uai);
}
