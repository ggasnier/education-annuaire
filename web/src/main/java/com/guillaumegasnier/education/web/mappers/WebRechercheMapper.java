package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.recherche.ResultatEntity;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebRechercheMapper {

    public abstract ResultatRechercheDto toResultatRechercheDto(ResultatEntity entity);

}
