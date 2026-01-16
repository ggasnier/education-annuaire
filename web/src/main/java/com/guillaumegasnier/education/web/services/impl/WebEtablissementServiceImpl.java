package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.EtablissementDetailsDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class WebEtablissementServiceImpl implements WebEtablissementService {

    private final CoreEtablissementService coreEtablissementService;
    private final CoreFormationService coreFormationService;
    private final CoreReferenceService coreReferenceService;
    private final WebEtablissementMapper webEtablissementMapper;

    @Autowired
    public WebEtablissementServiceImpl(CoreEtablissementService coreEtablissementService, CoreFormationService coreFormationService, CoreReferenceService coreReferenceService, WebEtablissementMapper webEtablissementMapper) {
        this.coreEtablissementService = coreEtablissementService;
        this.coreFormationService = coreFormationService;
        this.coreReferenceService = coreReferenceService;
        this.webEtablissementMapper = webEtablissementMapper;
    }

    @Override
    public Optional<EtablissementDto> findEtablissementByUai(String uai) {
        return coreEtablissementService.findEtablissement(uai).map(webEtablissementMapper::toEtablissementDto);
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

    @Override
    @Transactional(readOnly = true)
    public EtablissementDetailsDto findEtablissementDetailsDtoByUai(@NonNull String uai) {

        Optional<EtablissementEntity> entity = coreEtablissementService.findEtablissement(uai);

        if (entity.isEmpty()) {
            return null;
        }

        return new EtablissementDetailsDto(
                entity.map(webEtablissementMapper::toEtablissementDto).orElse(null),
                webEtablissementMapper.toOptionWithCategorieDto(coreEtablissementService.getOptionListByUai(uai)),
                coreEtablissementService.getSpecialiteListByUai(uai).stream().map(webEtablissementMapper::toSpecialiteBac).toList(),
                webEtablissementMapper.toLangueWithCategorieDtoList(coreEtablissementService.getLangueListByUai(uai)),
                webEtablissementMapper.toSportWithCategorieDtoList(coreEtablissementService.getSportListByUai(uai)),
                coreEtablissementService.getContactListByUai(uai).stream().map(webEtablissementMapper::toContactDto).toList(),
                coreEtablissementService.getJourneesPortesOuvertes(uai).stream().map(webEtablissementMapper::toJPODto).toList(),
                coreEtablissementService.getMetadataListByUai(uai).stream().map(webEtablissementMapper::toMetadataDto).toList(),
                coreFormationService.findFormations(uai).stream().map(webEtablissementMapper::toFormationDto).distinct().toList());
    }
}
