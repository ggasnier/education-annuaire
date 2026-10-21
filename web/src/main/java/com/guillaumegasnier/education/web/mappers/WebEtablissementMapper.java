package com.guillaumegasnier.education.web.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.SpecialiteBac;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.web.dto.etablissements.*;
import com.guillaumegasnier.education.web.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.web.dto.formations.FormationDTO;
import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class WebEtablissementMapper {

    @Mapping(target = "nomSecteur", source = "secteur.nom")
    @Mapping(target = "codeSecteur", source = "secteur")
    @Mapping(target = "codeContrat", source = "contrat.code")
    @Mapping(target = "nomContrat", source = "contrat.nom")
    @Mapping(target = "nomPays", source = "commune.pays.nom")
    @Mapping(target = "codePays", source = "commune.pays.code")
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
    public abstract EtablissementDTO toEtablissementDTO(EtablissementEntity entity);

    @Mapping(target = "emoji", source = "pk.option.emoji")
    @Mapping(target = "nom", source = "pk.option.nom")
    @Mapping(target = "code", source = "pk.option")
    public abstract OptionDto toOptionDto(EtablissementOptionEntity enity);

    public SpecialiteBac toSpecialiteBac(EtablissementSpecialiteEntity entity) {
        return entity.getPk().getSpecialite();
    }

    public List<NatureWithEtablissementsDTO> groupbyNature(List<EtablissementDTO> etablissements) {
        if (etablissements == null) return Collections.emptyList();


        Map<String, List<EtablissementDTO>> grouped = etablissements.stream()
                .filter(e -> e != null && e.getCodeNature() != null)
                .collect(Collectors.groupingBy(EtablissementDTO::getNomNature, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    String nomNature = entry.getKey();
                    List<EtablissementDTO> etablissementDtoList = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .toList();
                    return new NatureWithEtablissementsDTO(nomNature, etablissementDtoList);
                })
                .toList();
    }

    public List<CommuneWithEtablissementsDTO> groupByCommune(List<EtablissementEntity> etablissements) {
        if (etablissements == null) return Collections.emptyList();

        Map<CommuneEntity, List<EtablissementEntity>> grouped = etablissements.stream()
                .filter(e -> e != null && e.getCommune() != null)
                .collect(Collectors.groupingBy(EtablissementEntity::getCommune, LinkedHashMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    CommuneDTO communeDto = toCommuneDto(entry.getKey());
                    List<EtablissementDTO> etablissementDtoList = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toEtablissementDTO)
                            .toList();
                    return new CommuneWithEtablissementsDTO(communeDto, etablissementDtoList);
                })
                .toList();
    }

    public abstract CommuneDTO toCommuneDto(CommuneEntity key);

    public abstract NatureDTO toNatureDTO(NatureEntity key);

    public List<LangueWithCategorieDTO> toLangueWithCategorieDtoList(List<EtablissementLangueEntity> entities) {
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
                    List<LangueDTO> langues = entry.getValue().stream()
                            .filter(Objects::nonNull)
                            .map(this::toLangueDTO)
                            .toList();
                    return new LangueWithCategorieDTO(categorie, langues);
                })
                .toList();
    }

    private LangueDTO toLangueDTO(EtablissementLangueEntity entity) {
        LangueDTO l = new LangueDTO();
        l.setNom(entity.getPk().getLangue().getNom());
        l.setEmoji(entity.getPk().getLangue().getEmoji());
        l.setEnseignement(entity.getPk().getEnseignement());
        return l;
    }

    public List<SportWithCategorieDTO> toSportWithCategorieDtoList(List<EtablissementSportEntity> entities) {

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
                    return new SportWithCategorieDTO(entry.getKey(), sports);
                })
                .toList();
    }

    @Mapping(target = "code", source = "pk.contact")
    @Mapping(target = "valeur", source = "pk.valeur")
    @Mapping(target = "nom", source = "pk.contact.nom")
    public abstract ContactDTO toContactDto(EtablissementContactEntity entity);

    public List<OptionWithCategorieDTO> toOptionWithCategorieDto(List<EtablissementOptionEntity> entities) {
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
                    return new OptionWithCategorieDTO(categorie, options);
                })
                .sorted(Comparator.comparing(c -> c.getCategorie().getOrdre()))
                .toList();
    }

    @Mapping(target = "dateFin", source = "pk.dateFin")
    @Mapping(target = "dateDebut", source = "pk.dateDebut")
    public abstract JPODto toJPODto(EtablissementJPOEntity etablissementJPOEntity);

    @Named("toCertifiante")
    public String toCertifiante(Boolean isCertifiante) {
        if (isCertifiante != null && isCertifiante) return "Certifiante";
        return "";
    }

    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "positionnement", ignore = true)
    @Mapping(target = "parcoursDeFormation", ignore = true)
    @Mapping(target = "objectif", ignore = true)
    @Mapping(target = "identifiantModule", ignore = true)
    @Mapping(target = "contenu", ignore = true)
    @Mapping(target = "codeNiveauSortie", ignore = true)
    @Mapping(target = "codeNiveauEntree", ignore = true)
    @Mapping(target = "nom", source = "formation.nom")
    @Mapping(target = "certifiante", source = "formation.certifiante", qualifiedByName = "toCertifiante")
    public abstract FormationDTO toFormationDTO(ActionFormationEntity entity);

    @Mapping(target = "certifiante", source = "formation.certifiante")
    @Mapping(target = "formationId", source = "formation.id")
    @Mapping(target = "nom", source = "formation.nom")
    public abstract ActionFormationDTO toActionFormationDTO(ActionFormationEntity entity);

    @Mapping(target = "indiceNational", source = "metadatas.ips.indiceNational")
    @Mapping(target = "indiceDepartement", source = "metadatas.ips.indiceDepartement")
    @Mapping(target = "indiceAcademie", source = "metadatas.ips.indiceAcademie")
    @Mapping(target = "indice", source = "metadatas.ips.indice")
    @Mapping(target = "ecartType", source = "metadatas.ips.ecartType")
    @Mapping(target = "annee", source = "pk.annee")
    @Mapping(target = "effectifs", source = "metadatas.effectifs")
    public abstract IndicesPositionSocialeDTO toIndicesPositionSocialeDTO(EtablissementMetadataEntity entity);

    public List<IndicateurValeurAjouteeDTO> toIndicateurValeurAjouteeDTO(@NonNull EtablissementMetadataEntity entity) {
        return entity.getMetadatas().getIva().getResultats().stream()
                .map(resultat -> {
                    IndicateurValeurAjouteeDTO dto = new IndicateurValeurAjouteeDTO();
                    dto.setAnnee(entity.getPk().getAnnee());
                    dto.setEffectifs(resultat.getEffectif());
                    dto.setFiliere(resultat.getFiliere());
                    dto.setTaux(resultat.getTaux());
                    dto.setTauxMentions(resultat.getTauxMentions());
                    dto.setValeurAjoutee(resultat.getValeurAjoutee());
                    dto.setTauxAcces(resultat.getTauxAcces());
                    dto.setValeurAjouteeTauxMentions(resultat.getValeurAjouteeTauxMentions());
                    return dto;
                })
                .toList();
    }


    @Mapping(target = "annee", source = "pk.annee")
    @Mapping(target = "occupe", source = "metadatas.internat.occupe")
    @Mapping(target = "disponible", source = "metadatas.internat.disponible")
    public abstract Internat toInternatDto(EtablissementMetadataEntity entity);
}
