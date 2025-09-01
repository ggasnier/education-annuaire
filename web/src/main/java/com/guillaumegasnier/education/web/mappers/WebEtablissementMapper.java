package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.LangueEntity;
import com.guillaumegasnier.education.core.domains.etablissements.OptionEtablissementEntity;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.LangueDto;
import com.guillaumegasnier.education.web.dto.OptionDto;
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

    @Mapping(target = "nom", source = "pk.option.nom")
    @Mapping(target = "code", source = "pk.option")
    public abstract OptionDto toOptionDto(OptionEtablissementEntity enity);

    @Mapping(target = "nom", source = "pk.langue.nom")
    @Mapping(target = "niveau", source = "pk.enseignement")
    @Mapping(target = "code", source = "pk.langue")
    public abstract LangueDto toLangueDto(LangueEntity entity);

}
