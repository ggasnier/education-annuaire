package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.CommuneRequestDto;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;
import com.guillaumegasnier.education.web.mappers.WebTerritoireMapper;
import com.guillaumegasnier.education.web.services.WebTerritoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public CommuneDto createCommune(CommuneRequestDto dto) {
        CommuneEntity entity = webTerritoireMapper.toCommuneEntity(dto);
        entity.setPays(coreTerritoireService.getPays(dto.getCodePays()));
        if (StringUtils.hasText(dto.getCodeDepartement())) {
            coreTerritoireService.findDepartement(dto.getCodeDepartement()).ifPresent(entity::setDepartement);
        }
        return webTerritoireMapper.toCommuneDto(coreTerritoireService.createCommune(entity));
    }
}
