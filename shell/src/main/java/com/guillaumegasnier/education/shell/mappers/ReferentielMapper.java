package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.dto.MetierAppellationDto;
import com.guillaumegasnier.education.shell.datasets.CODESNSF;
import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.NOMENCLATUREEUROPE;
import com.guillaumegasnier.education.shell.datasets.OuiNonType;
import com.guillaumegasnier.education.shell.datasets.referentiels.ArborescenceCompetenceDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ReferentielMapper {

    @Named("toActif")
    public Boolean toActif(OuiNonType ouiNonType) {
        return OuiNonType.OUI.equals(ouiNonType);
    }

    @Named("toNSFList")
    public List<String> toNSFList(@NonNull CODESNSF nsf) {
        return nsf.getNSF().stream().map(CODESNSF.NSF::getCODE).toList();
    }

    @Named("toNiveau")
    public int toNiveau(NOMENCLATUREEUROPE nomenclatureeurope) {
        String s = nomenclatureeurope.getNIVEAU();
        if (s == null || s.length() < 4) return -1;
        char c = s.charAt(s.length() - 1);
        return (c >= '1' && c <= '8') ? (c - '0') : -1;
    }

    @Mapping(target = "typologieDiplome", ignore = true)
    @Mapping(target = "typeEmploiAccessibles", ignore = true)
    @Mapping(target = "secteursActivite", ignore = true)
    @Mapping(target = "romeList", ignore = true)
    @Mapping(target = "prerequisEntreeFormation", ignore = true)
    @Mapping(target = "capacitesAttestees", ignore = true)
    @Mapping(target = "activitesVisees", ignore = true)
    @Mapping(target = "niveau", source = "NOMENCLATUREEUROPE", qualifiedByName = "toNiveau")
    @Mapping(target = "nsfList", source = "CODESNSF", qualifiedByName = "toNSFList")
    @Mapping(target = "nouveauCode", ignore = true)
    @Mapping(target = "actif", source = "ACTIF", qualifiedByName = "toActif")
    @Mapping(target = "nom", source = "INTITULE")
    @Mapping(target = "code", source = "NUMEROFICHE")
    public abstract CertificationDTO toCertificationDTO(FICHES.FICHE fiche);

    public List<NSFDTO> toNSFDTO(@NonNull List<CODESNSF.NSF> nsf) {
        return nsf.stream().map(nsf1 -> new NSFDTO(nsf1.getCODE(), nsf1.getLIBELLE())).toList();
    }

    @Mapping(target = "competences", ignore = true)
    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "metdatas", ignore = true)
    @Mapping(target = "code", source = "codeRome")
    @Mapping(target = "metierParent", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    public abstract MetierEntity toMetierEntity(RomeDataset dataset);

    public List<String> toAppelations(Set<MetierAppellationDto> appellations) {
        return appellations.stream().map(MetierAppellationDto::getNom).toList();
    }

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "appellations", source = "metdatas.appellations")
    public abstract RechercheMetierEntity toRechercheMetierEntity(MetierEntity entity);

    @Mapping(target = "code", source = "codeDomaineCompetence")
    @Mapping(target = "nom", source = "domaineCompetence")
    public abstract DomaineCompetenceDTO toDomaineCompetenceDTO(ArborescenceCompetenceDataset dataset);

    @Mapping(target = "code", source = "codeEnjeu")
    @Mapping(target = "nom", source = "enjeu")
    public abstract EnjeuDTO toEnjeuDTO(ArborescenceCompetenceDataset dataset);

    @Mapping(target = "code", source = "codeObjectif")
    @Mapping(target = "nom", source = "objectif")
    public abstract ObjectifDTO toObjectifDTO(ArborescenceCompetenceDataset dataset);

    @Mapping(target = "code", source = "codeMacroCompetence")
    @Mapping(target = "nom", source = "libelleMacroCompetence")
    public abstract MacroCompetenceDTO toMacroCompetenceDTO(ArborescenceCompetenceDataset dataset);

    @Mapping(target = "code", source = "codeOgrCompetence")
    @Mapping(target = "nom", source = "libelleCompetence")
    public abstract CompetenceDTO toCompetenceDTO(ArborescenceCompetenceDataset dataset);
}
