package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSportEntity;
import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.LangueWithCategorieDto;
import com.guillaumegasnier.education.web.dto.etablissements.OptionDto;
import com.guillaumegasnier.education.web.dto.etablissements.SportWithCategorieDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<SpecialiteBac> getSpecialiteListByUai(String uai) {
        return coreEtablissementService.getSpecialiteListByUai(uai).stream().map(webEtablissementMapper::toSpecialiteBac).toList();
    }

    @Override
    public List<LangueWithCategorieDto> getLangueListByUai(String uai) {
        return webEtablissementMapper.toLangueWithCategorieDtoList(coreEtablissementService.getLangueListByUai(uai));
    }

//    @Override
//    public List<SectionSportiveDto> getSectionSportiveListByUai(String uai) {
//        return coreEtablissementService.getSectionSportiveListByUai(uai).stream().map(webEtablissementMapper::toSectionSportiveDto).toList();
//    }

//    @Override
//    public List<IPSDto> getIPSListByUai(String uai) {
//        return coreEtablissementService.getIPSListByUai(uai).stream().map(webEtablissementMapper::toIndicePositionSocialeEntity).toList();
//    }

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

    @Override
    public List<SportWithCategorieDto> getSportListByUai(String uai) {

        List<EtablissementSportEntity> entities = coreEtablissementService.getSportListByUai(uai);

        if (entities.isEmpty()) {
            return List.of();
        }

        Map<String, List<EtablissementSportEntity>> grouped = entities.stream()
                .filter(e -> e != null && e.getPk() != null && e.getPk().getCategorie() != null)
                .collect(Collectors.groupingBy(e -> e.getPk().getCategorie(), LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    SportWithCategorieDto.CategorieDto categorieNom = SportWithCategorieDto.CategorieDto.valueOf(entry.getKey().toUpperCase());
                    List<Sport> sports = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(e -> e.getPk().getSport())
                            .toList();
                    return new SportWithCategorieDto(categorieNom, sports);
                })
                .collect(Collectors.toList());
    }
}
