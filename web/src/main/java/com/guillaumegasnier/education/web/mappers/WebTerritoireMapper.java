package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.domains.territoires.PaysEntity;
import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.CommuneRequestDto;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class WebTerritoireMapper {

    public abstract PaysDTO toPaysDTO(PaysEntity entity);

    public abstract CommuneDto toCommuneDto(CommuneEntity entity);

    @Mapping(target = "pays", ignore = true)
    @Mapping(target = "departement", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract CommuneEntity toCommuneEntity(CommuneRequestDto dto);
}
