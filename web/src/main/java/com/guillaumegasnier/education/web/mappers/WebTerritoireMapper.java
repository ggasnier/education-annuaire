package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.domains.territoires.DepartementEntity;
import com.guillaumegasnier.education.core.domains.territoires.PaysEntity;
import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import com.guillaumegasnier.education.web.dto.territoires.CommuneRequestDTO;
import com.guillaumegasnier.education.web.dto.territoires.DepartementDTO;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class WebTerritoireMapper {

    public abstract PaysDTO toPaysDTO(PaysEntity entity);

    public abstract CommuneDTO toCommuneDTO(CommuneEntity entity);

    @Mapping(target = "pays", ignore = true)
    @Mapping(target = "departement", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract CommuneEntity toCommuneEntity(CommuneRequestDTO dto);

    @Mapping(target = "nomRegion", source = "region.nom")
    @Mapping(target = "nomAcademie", source = "academie.nom")
    @Mapping(target = "codeRegion", source = "region.code")
    @Mapping(target = "codeAcademie", source = "academie.code")
    public abstract DepartementDTO toDepartementDTO(DepartementEntity entity);
}
