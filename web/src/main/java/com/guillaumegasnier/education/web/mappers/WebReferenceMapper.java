package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.territoires.AcademieEntity;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.domains.territoires.DepartementEntity;
import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.references.AcademieDto;
import com.guillaumegasnier.education.web.dto.references.AcademieWithDepartementsDto;
import com.guillaumegasnier.education.web.dto.references.DepartementDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WebReferenceMapper {

    AcademieDto toAcademieDto(AcademieEntity academie);

    DepartementDto toDepartementDto(DepartementEntity departement);

    default List<AcademieWithDepartementsDto> groupByAcademie(List<DepartementEntity> departements) {
        if (departements == null) return Collections.emptyList();

        Map<AcademieEntity, List<DepartementEntity>> grouped = departements.stream()
                .filter(d -> d != null && d.getAcademie() != null)
                .collect(Collectors.groupingBy(DepartementEntity::getAcademie, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    AcademieDto acadDto = toAcademieDto(entry.getKey());
                    List<DepartementDto> deps = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toDepartementDto)
                            .collect(Collectors.toList());
                    return new AcademieWithDepartementsDto(acadDto, deps);
                })
                .collect(Collectors.toList());
    }

    CommuneDto toCommuneDto(CommuneEntity key);
}
