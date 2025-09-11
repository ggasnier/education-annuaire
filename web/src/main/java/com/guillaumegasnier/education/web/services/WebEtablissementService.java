package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.LangueDto;
import com.guillaumegasnier.education.web.dto.etablissements.IPSDto;
import com.guillaumegasnier.education.web.dto.etablissements.OptionDto;
import com.guillaumegasnier.education.web.dto.etablissements.SectionSportiveDto;

import java.util.List;
import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDto> findEtablissementByUai(String uai);

    List<OptionDto> getOptionListByUai(String uai);

    List<LangueDto> getLangueListByUai(String uai);

    List<SectionSportiveDto> getSectionSportiveListByUai(String uai);

    List<IPSDto> getIPSListByUai(String uai);
}
