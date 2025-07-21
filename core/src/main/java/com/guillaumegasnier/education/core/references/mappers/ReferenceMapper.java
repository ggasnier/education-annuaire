package com.guillaumegasnier.education.core.references.mappers;

import com.guillaumegasnier.education.core.references.dto.DepartementDto;
import com.guillaumegasnier.education.core.references.entities.DepartementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ReferenceMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "codeAcademie", target = "academie.code")
    @Mapping(source = "codeRegion", target = "region.code")
    public abstract DepartementEntity toEntity(DepartementDto departementDto);
//
//    @InheritInverseConfiguration(name = "toEntity")
//    abstract DepartementDto toDto(DepartementEntity departementEntity);
//
//    @InheritConfiguration(name = "toEntity")
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    abstract DepartementEntity partialUpdate(DepartementDto departementDto, @MappingTarget DepartementEntity departementEntity);

//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "codePays", ignore = true)
//    @Mapping(target = "departement", ignore = true)
//    public abstract CommuneEntity toCommuneEntity(CommuneRequestDto request);
//
//    @Mapping(target = "nomDepartement", source = "departement.nom")
//    @Mapping(target = "codePays", source = "codePays")
//    @Mapping(target = "codeDepartement", source = "departement.code")
//    public abstract CommuneDto toCommuneDto(CommuneEntity entity);
//
//    @Mapping(target = "nomRegion", source = "region.nom")
//    @Mapping(target = "nomAcademie", source = "academie.nom")
//    @Mapping(target = "codeRegion", source = "region.code")
//    @Mapping(target = "codeAcademie", source = "academie.code")
//    public abstract DepartementDto toDepartementDto(DepartementEntity entity);
//
//    public abstract RegionDto toRegionDto(RegionEntity entity);
//
//    public abstract NatureDto toNatureDto(NatureEntity entity);
//
//    public abstract AcademieDto toAcademieDto(AcademieEntity entity);
//
//    public abstract List<NatureDto> toNatureDto(List<NatureEntity> entities);
//
//    public abstract List<AcademieDto> toAcademieDto(List<AcademieEntity> entities);
//
//    public abstract List<RegionDto> toRegionDto(List<RegionEntity> entities);
//
//    public abstract List<DepartementDto> toDepartementDto(List<DepartementEntity> entities);

}
