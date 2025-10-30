package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.LangueDto;
import com.guillaumegasnier.education.web.dto.etablissements.IPSDto;
import com.guillaumegasnier.education.web.dto.etablissements.OptionDto;
import com.guillaumegasnier.education.web.dto.etablissements.SectionSportiveDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WebEtablissementServiceImpl implements WebEtablissementService {

    private final CoreEtablissementService coreEtablissementService;
    private final CoreReferenceService coreReferenceService;
    private final WebEtablissementMapper webEtablissementMapper;

    @Autowired
    public WebEtablissementServiceImpl(CoreEtablissementService coreEtablissementService, CoreReferenceService coreReferenceService, WebEtablissementMapper webEtablissementMapper) {
        this.coreEtablissementService = coreEtablissementService;
        this.coreReferenceService = coreReferenceService;
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

    @Override
    public Optional<EtablissementDto> createEtablissement(@NonNull EtablissementRequestDto dto) {

        if (coreEtablissementService.findEtablissement(dto.getUai()).isEmpty()) {
            EtablissementEntity entity = new EtablissementEntity();
            entity.setUai(dto.getUai());
            entity.setNom(dto.getNom());
            entity.setAdresse(dto.getAdresse());
            entity.setComplement(dto.getComplement());
            entity.setCodePostal(dto.getCodePostal());

            if (dto.getCodeNature() != null) {
                coreEtablissementService.findNature(dto.getCodeNature()).ifPresent(entity::setNature);
            }
            if (dto.getCodeCommune() != null) {
                coreReferenceService.findCommune(dto.getCodeCommune()).ifPresent(entity::setCommune);
            }
            if (dto.getCodeContrat() != null) {
                coreEtablissementService.findContrat(dto.getCodeContrat()).ifPresent(entity::setContrat);
            }

            entity.addSource("api");

            return Optional.of(coreEtablissementService.saveEtablissement(entity))
                    .map(webEtablissementMapper::toEtablissementDto);
        } else {
            return Optional.empty();
        }
    }
}
