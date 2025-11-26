package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.EtablissementDetailsDto;
import com.guillaumegasnier.education.web.dto.etablissements.SportWithCategorieDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDto> findEtablissementByUai(String uai);

//    List<OptionDto> getOptionListByUai(String uai);
//
//    List<SpecialiteBac> getSpecialiteListByUai(String uai);
//
//    List<LangueWithCategorieDto> getLangueListByUai(String uai);

    Optional<EtablissementDto> createEtablissement(EtablissementRequestDto dto);

    List<SportWithCategorieDto> getSportListByUai(String uai);

    EtablissementDetailsDto findEtablissementDetailsDtoByUai(@NonNull String uai);
}
