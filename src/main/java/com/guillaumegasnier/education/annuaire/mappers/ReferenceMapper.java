package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.domains.*;
import com.guillaumegasnier.education.annuaire.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ReferenceMapper {

    @Mapping(target = "pays", ignore = true)
    @Mapping(target = "departement", ignore = true)
    public abstract CommuneEntity toCommuneEntity(CommuneRequestDto request);

    @Mapping(target = "nomDepartement", source = "departement.nom")
    @Mapping(target = "codePays", source = "pays.code")
    @Mapping(target = "codeDepartement", source = "departement.code")
    public abstract CommuneDto toCommuneDto(CommuneEntity entity);

    @Mapping(target = "nomRegion", source = "region.nom")
    @Mapping(target = "nomAcademie", source = "academie.nom")
    @Mapping(target = "codeRegion", source = "region.code")
    @Mapping(target = "codeAcademie", source = "academie.code")
    public abstract DepartementDto toDepartementDto(DepartementEntity entity);

    public abstract RegionDto toRegionDto(RegionEntity entity);

    public abstract NatureDto toNatureDto(NatureEntity entity);

    public abstract AcademieDto toAcademieDto(AcademieEntity entity);

    public abstract List<NatureDto> toNatureDto(List<NatureEntity> entities);

    public abstract List<AcademieDto> toAcademieDto(List<AcademieEntity> entities);

    public abstract List<RegionDto> toRegionDto(List<RegionEntity> entities);

    public abstract List<DepartementDto> toDepartementDto(List<DepartementEntity> entities);

}
