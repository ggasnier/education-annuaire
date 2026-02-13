package com.guillaumegasnier.education.shell.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {DateMapper.class})
public abstract class RechercheMapper {
}
