package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.EtablissementEntity;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebEtablissementMapper {

    @Mapping(target = "codeContrat", source = "contrat.code")
    @Mapping(target = "nomContrat", source = "contrat.nom")
    @Mapping(target = "nomPays", ignore = true)
    @Mapping(target = "codePays", ignore = true)
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "nomEtat", source = "etat.nom")
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "codeEtat", source = "etat")
    @Mapping(target = "codeCommune", source = "commune.code")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "codeDepartement", source = "commune.departement.code")
    @Mapping(target = "nomDepartement", source = "commune.departement.nom")
    @Mapping(target = "codeRegion", source = "commune.departement.region.code")
    @Mapping(target = "nomRegion", source = "commune.departement.region.nom")
    @Mapping(target = "codeAcademie", source = "commune.departement.academie.code")
    @Mapping(target = "nomAcademie", source = "commune.departement.academie.nom")
    public abstract EtablissementDto toEtablissementDto(EtablissementEntity entity);
}
