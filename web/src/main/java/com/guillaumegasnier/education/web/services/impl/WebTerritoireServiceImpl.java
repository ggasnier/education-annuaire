package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.web.dto.etablissements.CommuneWithEtablissementsDTO;
import com.guillaumegasnier.education.web.dto.territoires.*;
import com.guillaumegasnier.education.web.mappers.WebTerritoireMapper;
import com.guillaumegasnier.education.web.services.WebTerritoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebTerritoireServiceImpl implements WebTerritoireService {

    private final CoreTerritoireService coreTerritoireService;
    private final WebTerritoireMapper webTerritoireMapper;

    @Override
    public List<PaysDTO> getPaysList() {
        return coreTerritoireService.getPaysList().stream().map(webTerritoireMapper::toPaysDTO).toList();
    }

    @Override
    public CommuneDTO createCommune(CommuneRequestDTO dto) {
        CommuneEntity entity = webTerritoireMapper.toCommuneEntity(dto);
        entity.setPays(coreTerritoireService.getPays(dto.getCodePays()));
        if (StringUtils.hasText(dto.getCodeDepartement())) {
            coreTerritoireService.findDepartement(dto.getCodeDepartement()).ifPresent(entity::setDepartement);
        }
        return webTerritoireMapper.toCommuneDTO(coreTerritoireService.createCommune(entity));
    }

    @Override
    public List<AcademieWithDepartementsDTO> getAcademiesWithDepartements() {
        return List.of();
    }

    @Override
    public Optional<CommuneDTO> findCommune(String code) {
        return coreTerritoireService.findCommune(code).map(webTerritoireMapper::toCommuneDTO);
    }

    @Override
    public Optional<DepartementDTO> findDepartement(String code) {
        return coreTerritoireService.findDepartement(code).map(webTerritoireMapper::toDepartementDTO);
    }

    @Override
    public List<CommuneWithEtablissementsDTO> getCommuneWithEtablissementsDTO(String code) {
        return List.of();
    }
}
