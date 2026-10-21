package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.territoires.AcademieEntity;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.domains.territoires.DepartementEntity;
import com.guillaumegasnier.education.web.dto.territoires.AcademieDTO;
import com.guillaumegasnier.education.web.dto.territoires.AcademieWithDepartementsDTO;
import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import com.guillaumegasnier.education.web.dto.territoires.DepartementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.*;
import java.util.stream.Collectors;

@Deprecated
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WebReferenceMapper {

    AcademieDTO toAcademieDTO(AcademieEntity academie);

    @Mapping(target = "nomRegion", ignore = true)
    @Mapping(target = "nomAcademie", ignore = true)
    @Mapping(target = "codeRegion", ignore = true)
    @Mapping(target = "codeAcademie", ignore = true)
    DepartementDTO toDepartementDTO(DepartementEntity departement);

    default List<AcademieWithDepartementsDTO> groupByAcademie(List<DepartementEntity> departements) {
        if (departements == null) return Collections.emptyList();

        Map<AcademieEntity, List<DepartementEntity>> grouped = departements.stream()
                .filter(d -> d != null && d.getAcademie() != null)
                .collect(Collectors.groupingBy(DepartementEntity::getAcademie, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    AcademieDTO acadDto = toAcademieDTO(entry.getKey());
                    List<DepartementDTO> deps = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toDepartementDTO)
                            .toList();
                    return new AcademieWithDepartementsDTO(acadDto, deps);
                })
                .toList();
    }

    CommuneDTO toCommuneDto(CommuneEntity key);
}
