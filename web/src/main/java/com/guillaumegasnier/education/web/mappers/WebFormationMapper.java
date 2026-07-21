package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.web.dto.formations.ActionFormationDto;
import com.guillaumegasnier.education.web.dto.formations.FormationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebFormationMapper {

    @Mapping(target = "certifiante", source = "formation.certifiante")
    @Mapping(target = "formationId", source = "formation.id")
    @Mapping(target = "nom", source = "formation.nom")
    public abstract ActionFormationDto toActionFormationDto(ActionFormationEntity entity);

    public abstract FormationDto toFormationDto(FormationEntity formationEntity);
}
