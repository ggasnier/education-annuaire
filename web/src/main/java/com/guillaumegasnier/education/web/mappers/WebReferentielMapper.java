package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.referentiels.*;
import com.guillaumegasnier.education.web.dto.referentiels.CompetenceDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDTO;
import com.guillaumegasnier.education.web.dto.referentiels.SavoirDTO;
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
    public abstract MetierDetailsDTO toMetierDetailsDTO(MetierEntity entity);

    public abstract MetierDTO toMetierDTO(MetierEntity metierEntity);

    @Mapping(target = "sousCategorie", source = "savoir.sousCategorie")
    @Mapping(target = "categorie", source = "savoir.categorie")
    @Mapping(target = "transitionEco", source = "savoir.transitionEco")
    @Mapping(target = "nom", source = "savoir.nom")
    public abstract SavoirDTO toSavoirDTO(MetierSavoirLienEntity entity);

    @Mapping(target = "nom", source = "competence.nom")
    @Mapping(target = "categorie", source = "competence.macroCompetence.objectif.enjeu.domaine.nom")
    public abstract CompetenceDTO toCompetenceDTO(MetierCompetenceEntity entity);
}
