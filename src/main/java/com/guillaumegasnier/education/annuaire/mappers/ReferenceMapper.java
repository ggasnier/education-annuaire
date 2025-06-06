package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.domains.AcademieEntity;
import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import com.guillaumegasnier.education.annuaire.domains.DepartementEntity;
import com.guillaumegasnier.education.annuaire.dto.AcademieDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneRequestDto;
import com.guillaumegasnier.education.annuaire.dto.DepartementDto;
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

    public abstract AcademieDto toDto(AcademieEntity entity);

    @Mapping(target = "nomRegion", source = "region.code")
    @Mapping(target = "nomAcademie", source = "academie.nom")
    @Mapping(target = "codeRegion", source = "region.code")
    @Mapping(target = "codeAcademie", source = "academie.code")
    public abstract DepartementDto toDto(DepartementEntity entity);
}
