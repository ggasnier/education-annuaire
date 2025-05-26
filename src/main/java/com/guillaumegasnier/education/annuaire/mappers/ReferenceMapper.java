package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.dto.CommuneDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ReferenceMapper {

    @Mapping(target = "pays", ignore = true)
    @Mapping(target = "departement", ignore = true)
    public abstract CommuneEntity toEntity(CommuneRequestDto request);

    @Mapping(target = "libelleDepartement", ignore = true)
    @Mapping(target = "codePays", ignore = true)
    @Mapping(target = "codeDepartement", ignore = true)
    public abstract CommuneDto toDto(CommuneEntity entity);
    
}
