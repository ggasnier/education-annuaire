package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionEntity;
import com.guillaumegasnier.education.core.domains.etablissements.NatureEntity;
import com.guillaumegasnier.education.core.domains.etablissements.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.IndicateurValeurAjouteeDto;
import com.guillaumegasnier.education.core.dto.IndicePositionSocialeDto;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.dto.etablissements.*;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {DateMapper.class})
public abstract class EtablissementMapper {

    // Le sport
    public Sport.Categorie toSportCategorie(OptionEtablissement option) {
        return switch (option) {
            case OptionEtablissement.SECTION_SPORT -> Sport.Categorie.SS;
            case OptionEtablissement.SPORT_ETUDES -> Sport.Categorie.SE;
            default -> null;
        };
    }

    public List<SportDTO> toSportDTO(@NonNull SportDataset dataset, @NonNull Sport.Categorie categorie) {
        return dataset.getSectionList()
                .stream()
                .map(String::toUpperCase)
                .map(Sport::transformation)
                .filter(Objects::nonNull)
                .map(sport -> new SportDTO(dataset.getUai(), sport, categorie))
                .toList();
    }

    public List<SportDTO> toSportDTO(@NonNull OnisepDispositifDataset dataset, @NonNull OptionEtablissement option) {
        return dataset.getSportList().stream().map(sport ->
                new SportDTO(dataset.getUai(), sport, toSportCategorie(option))
        ).toList();
    }

    // Les langues
    public Langue.Categorie toLangueCategorie(@NonNull OptionEtablissement option) {
        return switch (option) {
            case OptionEtablissement.SECTION_EUROPEENNE -> Langue.Categorie.EU;
            case OptionEtablissement.SECTION_INTERNATIONALE -> Langue.Categorie.SI;
            case OptionEtablissement.SECTION_BILINGUE -> Langue.Categorie.BI;
            case OptionEtablissement.SECTION_ORIENTALE -> Langue.Categorie.LO;
            default -> null;
        };
    }

    public LangueDTO toLangueDTO(@NonNull LangueDataset dataset) {
        return new LangueDTO(dataset.getUai(), Langue.transformation(dataset.getLangue()), Langue.Categorie.LV, dataset.getEnseignement());
    }

    public List<LangueDTO> toLangueDTO(@NonNull OnisepDispositifDataset dataset) {
        return dataset.getLangueList().stream()
                .map(langue -> new LangueDTO(dataset.getUai(), langue, toLangueCategorie(dataset.getOption()), ""))
                .toList();
    }

    // Les options
    public OptionDTO toOptionDTO(@NonNull OnisepDispositifDataset dataset) {
        return new OptionDTO(dataset.getUai(), dataset.getOption());
    }

    public OptionDTO toOptionDTO(@NonNull SectionBinationaleDataset dataset) {
        return new OptionDTO(dataset.getUai(), dataset.getOption());
    }

    public List<OptionDTO> toOptionDTO(@NonNull SectionInternationaleDataset dataset) {
        List<OptionDTO> dtos = new ArrayList<>();
        // Indicateur SI
        dtos.add(new OptionDTO(dataset.getUai(), OptionEtablissement.SECTION_INTERNATIONALE));
        // Indicateur BFI
        dataset.getNiveaux().forEach(niveau -> {
            if (niveau.equals("BFI")) {
                dtos.add(new OptionDTO(dataset.getUai(), OptionEtablissement.BFI));
            }
        });
        return dtos;
    }

    public List<OptionDTO> toOptionDTO(@NonNull EtablissementDataset dataset) {
        return dataset.getOptions().stream()
                .map(option -> new OptionDTO(dataset.getUai(), option)
                ).toList();
    }

    // Les contacts
    public List<ContactDTO> toContactDTO(@NonNull EtablissementDataset dataset) {
        return dataset.getContacts().stream()
                .map(contact -> new ContactDTO(dataset.getUai(), contact.getContact(), contact.getValeur()))
                .toList();
    }

    // Les journées portes ouvertes
    public List<JPODataset> toJPODTO(@NonNull EtablissementDataset dataset) {
        return dataset.getJPO();
    }

    @Mapping(target = "uai", source = "masaId") // a transformer plus tard
    @Mapping(target = "heureFin", ignore = true)
    @Mapping(target = "heureDebut", ignore = true)
    public abstract JPODataset toJPODTO(MasaJpoDataset dataset);

    public abstract MasaDTO toMasaDTO(EtablissementDataset dataset);

    // Les spécialités du BAC
    public List<SpecialiteDTO> toSpecialiteDTO(@NonNull SpecialitePremiereDataset dataset) {
        return dataset.getSpecialites().stream().map(
                specialite -> new SpecialiteDTO(dataset.getUai(), specialite)
        ).toList();
    }

    // Les entitées JPA
    @Mapping(target = "masa", ignore = true)
    @Mapping(target = "identifiants", ignore = true)
    @Mapping(target = "organisme", ignore = true)
    @Mapping(target = "nature", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "commune", ignore = true)
    @Mapping(target = "sources", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract EtablissementEntity toEntity(EtablissementDataset dataset);

    @Mapping(target = "nomCourt", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract NatureEntity toNatureEntity(NatureDataset dataset);

    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract ContratEntity toContratEntity(ContratDataset dataset);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "groupes", ignore = true)
    @Mapping(target = "nda", source = "numeroDeclarationActivite")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "commune", ignore = true)
    @Mapping(target = "certificationQualiopi", ignore = true)
    public abstract OrganismeEntity toOrganismeEntity(TravailOrganismeFormationDataset dataset);

    @Mapping(target = "description", source = "entity", qualifiedByName = "toDescription")
    @Mapping(target = "nomSecteur", ignore = true)
    @Mapping(target = "codeSecteur", ignore = true)
    @Mapping(target = "nomRegion", source = "commune.departement.region.nom")
    @Mapping(target = "nomPays", source = "commune.pays.nom")
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "nomDepartement", source = "commune.departement.nom")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "nomAcademie", source = "commune.departement.academie.nom")
    @Mapping(target = "codeRegion", source = "commune.departement.region.code")
    @Mapping(target = "codePays", source = "commune.pays.code")
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "codeDepartement", source = "commune.departement.code")
    @Mapping(target = "codeCommune", source = "commune.code")
    @Mapping(target = "codeAcademie", source = "commune.departement.academie.code")
    @Mapping(target = "id", source = "uai")
    public abstract RechercheEtablissementEntity toRechercheEtablissementEntity(EtablissementEntity entity);

    @Named("toDescription")
    public String toDescription(@NonNull EtablissementEntity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getUai());
        sb.append(" ");
        sb.append(entity.getNature().getNom());
        sb.append(" ");
        sb.append(entity.getNom());
        sb.append(" ");
        sb.append(entity.getAdresse());
        sb.append(" ");
        if (entity.getComplement() != null) {
            sb.append(entity.getComplement());
            sb.append(" ");
        }
        sb.append(entity.getCodePostal());
        sb.append(" ");
        sb.append(entity.getCommune().getNom());
        sb.append(" ");
        sb.append(entity.getCommune().getDepartement().getNom());
        sb.append(" ");
        return sb.toString();
    }

    public <T extends IndicePositionSociale & Metadata> IndicePositionSocialeDto toIndicePositionSocialeDto(T dataset) {
    // Les metadatas
    public <T extends IndicePositionSociale & Metadata> IndicePositionSocialeDto toIndicePositionSocialeDto(@NonNull T dataset) {
        IndicePositionSocialeDto ips = new IndicePositionSocialeDto();
        ips.setIndice(dataset.getIndice());
        ips.setEcartType(dataset.getEcartType());
        ips.setIndiceAcademie(dataset.getIndiceAcademie());
        ips.setIndiceAcademiePublic(dataset.getIndiceAcademiePublic());
        ips.setIndiceAcademiePrive(dataset.getIndiceAcademiePrive());
        ips.setIndiceDepartement(dataset.getIndiceDepartement());
        ips.setIndiceDepartementPublic(dataset.getIndiceDepartementPublic());
        ips.setIndiceDepartementPrive(dataset.getIndiceDepartementPrive());
        ips.setIndiceNational(dataset.getIndiceNational());
        ips.setIndiceNationalPublic(dataset.getIndiceNationalPublic());
        ips.setIndiceNationalPrive(dataset.getIndiceNationalPrive());
        return ips;
    }

    public <T extends IndicateurValeurAjoutee & Metadata> IndicateurValeurAjouteeDto toIndicateurValeurAjouteeDto(@NonNull T dataset) {
        IndicateurValeurAjouteeDto iva = new IndicateurValeurAjouteeDto();
        iva.setTauxAcces(dataset.getTauxAcces());
        iva.setResultats(dataset.getResultats());
        return iva;
    }

    @Named("toOptions")
    public List<RechercheEtablissementEntity.RechercheOption> toOptions(@NonNull List<EtablissementOptionEntity> entities) {
        return entities.stream().map(this::toOption).toList();
    }

    @Mapping(target = "couleur", source = "pk.option.categorie.couleur")
    @Mapping(target = "nom", source = "pk.option.nom")
    @Mapping(target = "code", source = "pk.option")
    public abstract RechercheEtablissementEntity.RechercheOption toOption(EtablissementOptionEntity entity);

    @Mapping(target = "options", source = "options", qualifiedByName = "toOptions")
    @Mapping(target = "codeSecteur", source = "secteur")
    @Mapping(target = "nomSecteur", source = "secteur.nom")
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "codeCommune", source = "commune.code")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "codeDepartement", source = "commune.departement.code")
    @Mapping(target = "nomDepartement", source = "commune.departement.nom")
    @Mapping(target = "codeAcademie", source = "commune.departement.academie.code")
    @Mapping(target = "nomAcademie", source = "commune.departement.academie.nom")
    @Mapping(target = "codeRegion", source = "commune.departement.region.code")
    @Mapping(target = "nomRegion", source = "commune.departement.region.nom")
    @Mapping(target = "codePays", source = "commune.pays.code")
    @Mapping(target = "nomPays", source = "commune.pays.nom")
    public abstract RechercheEtablissementEntity toRechercheEtablissementEntity(EtablissementEntity entity);

}
