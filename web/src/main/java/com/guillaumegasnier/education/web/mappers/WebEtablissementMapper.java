package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.LangueDto;
import com.guillaumegasnier.education.web.dto.etablissements.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebEtablissementMapper {

    @Mapping(target = "nomSecteur", source = "secteur.nom")
    @Mapping(target = "codeSecteur", source = "secteur")
    @Mapping(target = "nomEtat", ignore = true)
    @Mapping(target = "codeContrat", source = "contrat.code")
    @Mapping(target = "nomContrat", source = "contrat.nom")
    @Mapping(target = "nomPays", ignore = true)
    @Mapping(target = "codePays", ignore = true)
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "codeCommune", source = "commune.code")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "codeDepartement", source = "commune.departement.code")
    @Mapping(target = "nomDepartement", source = "commune.departement.nom")
    @Mapping(target = "codeRegion", source = "commune.departement.region.code")
    @Mapping(target = "nomRegion", source = "commune.departement.region.nom")
    @Mapping(target = "codeAcademie", source = "commune.departement.academie.code")
    @Mapping(target = "nomAcademie", source = "commune.departement.academie.nom")
    public abstract EtablissementDto toEtablissementDto(EtablissementEntity entity);

    @Mapping(target = "emoji", source = "pk.option.emoji")
    @Mapping(target = "nom", source = "pk.option.nom")
    @Mapping(target = "code", source = "pk.option")
    public abstract OptionDto toOptionDto(EtablissementOptionEntity enity);

    public SpecialiteBac toSpecialiteBac(EtablissementSpecialiteEntity entity) {
        return entity.getPk().getSpecialite();
    }

    public List<NatureWithEtablissementsDto> groupbyNature(List<EtablissementEntity> etablissements) {
        if (etablissements == null) return Collections.emptyList();

        Map<NatureEntity, List<EtablissementEntity>> grouped = etablissements.stream()
                .filter(e -> e != null && e.getNature() != null)
                .collect(Collectors.groupingBy(EtablissementEntity::getNature, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    NatureDto natureDto = toNatureDto(entry.getKey());
                    List<EtablissementDto> etablissementDtoList = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toEtablissementDto)
                            .toList();
                    return new NatureWithEtablissementsDto(natureDto, etablissementDtoList);
                })
                .toList();
    }

    public List<CommuneWithEtablissementsDto> groupByCommune(List<EtablissementEntity> etablissements) {
        if (etablissements == null) return Collections.emptyList();

        Map<CommuneEntity, List<EtablissementEntity>> grouped = etablissements.stream()
                .filter(e -> e != null && e.getCommune() != null)
                .collect(Collectors.groupingBy(EtablissementEntity::getCommune, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    CommuneDto communeDto = toCommuneDto(entry.getKey());
                    List<EtablissementDto> etablissementDtoList = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toEtablissementDto)
                            .toList();
                    return new CommuneWithEtablissementsDto(communeDto, etablissementDtoList);
                })
                .toList();
    }

    public abstract CommuneDto toCommuneDto(CommuneEntity key);

    public abstract NatureDto toNatureDto(NatureEntity key);

    public List<LangueWithCategorieDto> toLangueWithCategorieDtoList(List<EtablissementLangueEntity> entities) {
        if (entities == null) return Collections.emptyList();

        Map<Langue.Categorie, List<EtablissementLangueEntity>> grouped = entities.stream()
                .filter(d -> d != null && d.getPk().getEnseignement() != null)
                .collect(
                        Collectors.groupingBy(l -> l.getPk().getCategorie(),
                                LinkedHashMap::new,
                                Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    Langue.Categorie categorie = entry.getKey();
                    List<LangueDto> langues = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toLangueDto)
                            .toList();
                    return new LangueWithCategorieDto(categorie, langues);
                })
                .toList();
    }

    private LangueDto toLangueDto(EtablissementLangueEntity entity) {
        LangueDto l = new LangueDto();
        l.setNom(entity.getPk().getLangue().getNom());
        l.setEmoji(entity.getPk().getLangue().getEmoji());
        l.setEnseignement(entity.getPk().getEnseignement());
        return l;
    }

    @Mapping(target = "tauxMentions", source = "metadatas.iva.resultat.tauxMentions")
    @Mapping(target = "indiceNational", source = "metadatas.ips.indiceNational")
    @Mapping(target = "valeurAjoutee", source = "metadatas.iva.resultat.valeurAjoutee")
    @Mapping(target = "tauxReussite", source = "metadatas.iva.resultat.taux")
    @Mapping(target = "indiceDepartement", source = "metadatas.ips.indiceDepartement")
    @Mapping(target = "indiceAcademie", source = "metadatas.ips.indiceAcademie")
    @Mapping(target = "indice", source = "metadatas.ips.indice")
    @Mapping(target = "ecartType", source = "metadatas.ips.ecartType")
    @Mapping(target = "effectifs", source = "metadatas.effectifs")
    @Mapping(target = "annee", source = "pk.annee")
    public abstract MetadataDto toMetadataDto(EtablissementMetadataEntity entity);

    public List<SportWithCategorieDto> toSportWithCategorieDtoList(List<EtablissementSportEntity> entities) {

        if (entities.isEmpty()) {
            return List.of();
        }

        Map<Sport.Categorie, List<EtablissementSportEntity>> grouped = entities.stream()
                .filter(e -> e != null && e.getPk() != null && e.getPk().getCategorie() != null)
                .collect(Collectors.groupingBy(e -> e.getPk().getCategorie(), LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    List<Sport> sports = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(e -> e.getPk().getSport())
                            .toList();
                    return new SportWithCategorieDto(entry.getKey(), sports);
                })
                .toList();
    }

    @Mapping(target = "valeur", source = "pk.valeur")
    @Mapping(target = "nom", source = "pk.contact.nom")
    public abstract ContactDto toContactDto(EtablissementContactEntity entity);

    public List<OptionWithCategorieDto> toOptionWithCategorieDto(List<EtablissementOptionEntity> entities) {
        if (entities == null) return Collections.emptyList();

        Map<OptionEtablissement.Categorie, List<EtablissementOptionEntity>> grouped = entities.stream()
                .filter(d -> d != null && d.getPk().getOption().getCategorie() != null)
                .collect(
                        Collectors.groupingBy(l -> l.getPk().getOption().getCategorie(),
                                LinkedHashMap::new,
                                Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    OptionEtablissement.Categorie categorie = entry.getKey();
                    List<OptionDto> options = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toOptionDto)
                            .toList();
                    return new OptionWithCategorieDto(categorie, options);
                })
                .sorted(Comparator.comparing(c -> c.getCategorie().getOrdre()))
                .toList();
    }

    @Mapping(target = "dateFin", source = "pk.dateFin")
    @Mapping(target = "dateDebut", source = "pk.dateDebut")
    public abstract JPODto toJPODto(EtablissementJPOEntity etablissementJPOEntity);
}
