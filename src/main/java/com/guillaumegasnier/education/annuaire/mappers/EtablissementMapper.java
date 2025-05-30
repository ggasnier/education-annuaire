package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import com.guillaumegasnier.education.annuaire.domains.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.dto.IPSDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class EtablissementMapper {

    @Mapping(target = "nomPays", source = "commune.pays.nom")
    @Mapping(target = "codePays", source = "commune.pays.code")
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
    @Mapping(target = "codeAcademie", source = "commune.departement.academie.code")
    public abstract EtablissementDto toDto(EtablissementEntity entity);

    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "nature", ignore = true)
    @Mapping(target = "etat", source = "codeEtat")
    @Mapping(target = "commune", ignore = true)
    public abstract EtablissementEntity toEntity(EtablissementRequestDto dto);

    @Mapping(target = "uai", source = "pk.uai")
    @Mapping(target = "annee", source = "pk.annee")
    public abstract IPSDto toDto(IndicePositionSocialeEntity entity);

    public abstract List<IPSDto> toDto(List<IndicePositionSocialeEntity> entities);

}
