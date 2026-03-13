package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.dto.MetierAppellationDto;
import com.guillaumegasnier.education.core.dto.MetierBlocDto;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebReferentielMapper {

    @Named("toDefinitions")
    public List<String> toDefinitions(Set<MetierBlocDto> dtos) {
        return dtos.stream().filter(dto -> dto.getCode() == 3)
                .sorted(Comparator.comparingInt(MetierBlocDto::getPosition))
                .map(MetierBlocDto::getTexte)
                .toList();
    }

    @Named("toAccess")
    public List<String> toAccess(Set<MetierBlocDto> dtos) {
        return dtos.stream().filter(dto -> dto.getCode() == 4)
                .sorted(Comparator.comparingInt(MetierBlocDto::getPosition))
                .map(MetierBlocDto::getTexte)
                .toList();
    }

    @Named("toAppellations")
    public List<String> toAppellations(Set<MetierAppellationDto> dtos) {
        return dtos.stream()
                .map(MetierAppellationDto::getNom)
                .sorted()
                .toList();
    }

    @Mapping(target = "definitions", source = "metdatas.blocs", qualifiedByName = "toDefinitions")
    @Mapping(target = "appellations", source = "metdatas.appellations", qualifiedByName = "toAppellations")
    @Mapping(target = "access", source = "metdatas.blocs", qualifiedByName = "toAccess")
    public abstract MetierDetailsDto toMetierDetailsDto(MetierEntity entity);
}
