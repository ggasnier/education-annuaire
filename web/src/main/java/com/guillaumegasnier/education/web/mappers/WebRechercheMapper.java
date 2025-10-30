package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.recherche.ResultatEntity;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebRechercheMapper {

    @Mapping(target = "totalElements", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "results", ignore = true)
    @Mapping(target = "page", ignore = true)
    public abstract ResultatRechercheDto toResultatRechercheDto(ResultatEntity entity);

}
