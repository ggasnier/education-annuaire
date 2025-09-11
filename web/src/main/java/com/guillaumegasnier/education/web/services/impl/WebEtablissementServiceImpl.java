package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.LangueDto;
import com.guillaumegasnier.education.web.dto.etablissements.IPSDto;
import com.guillaumegasnier.education.web.dto.etablissements.OptionDto;
import com.guillaumegasnier.education.web.dto.etablissements.SectionSportiveDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WebEtablissementServiceImpl implements WebEtablissementService {

    private final CoreEtablissementService coreEtablissementService;
    private final WebEtablissementMapper webEtablissementMapper;

    @Autowired
    public WebEtablissementServiceImpl(CoreEtablissementService coreEtablissementService, WebEtablissementMapper webEtablissementMapper) {
        this.coreEtablissementService = coreEtablissementService;
        this.webEtablissementMapper = webEtablissementMapper;
    }

    @Override
    public Optional<EtablissementDto> findEtablissementByUai(String uai) {
        return coreEtablissementService.findEtablissement(uai).map(webEtablissementMapper::toEtablissementDto);
    }

    @Override
    public List<OptionDto> getOptionListByUai(String uai) {
        return coreEtablissementService.getOptionListByUai(uai).stream().map(webEtablissementMapper::toOptionDto).toList();
    }

    @Override
    public List<LangueDto> getLangueListByUai(String uai) {
        return coreEtablissementService.getLangueListByUai(uai).stream().map(webEtablissementMapper::toLangueDto).toList();
    }

    @Override
    public List<SectionSportiveDto> getSectionSportiveListByUai(String uai) {
        return coreEtablissementService.getSectionSportiveListByUai(uai).stream().map(webEtablissementMapper::toSectionSportiveDto).toList();
    }

    @Override
    public List<IPSDto> getIPSListByUai(String uai) {
        return coreEtablissementService.getIPSListByUai(uai).stream().map(webEtablissementMapper::toIndicePositionSocialeEntity).toList();
    }
}
