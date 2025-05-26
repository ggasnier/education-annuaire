package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, typeConversionPolicy = ReportingPolicy.WARN)
public abstract class EtablissementMapper {

    @Mapping(target = "nomRegion", source = "commune.departement.region.nom")
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "nomEtat", source = "etat.nom")
    @Mapping(target = "nomDepartement", source = "commune.departement.nom")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "nomAcademie", source = "commune.departement.academie.nom")
    @Mapping(target = "codeRegion", source = "commune.departement.region.code")
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "codeEtat", source = "etat")
    @Mapping(target = "codeDepartement", source = "commune.departement.code")
    @Mapping(target = "codeCommune", source = "commune.code")
    @Mapping(target = "codeAcademie", source = "commune.departement.academie")
    public abstract EtablissementDto toDto(EtablissementEntity entity);
}
