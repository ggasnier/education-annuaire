package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.NatureEntity;
import com.guillaumegasnier.education.core.domains.etablissements.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.dto.IndicateurValeurAjouteeDto;
import com.guillaumegasnier.education.core.dto.IndicePositionSocialeDto;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

@Slf4j
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {DateMapper.class})
public abstract class EtablissementMapper {

    @Mapping(target = "organisme", ignore = true)
    @Mapping(target = "nature", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "commune", ignore = true)
    @Mapping(target = "sources", ignore = true) // Ne pas mapper
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract EtablissementEntity toEntity(EtablissementDataset dataset);

    @Mapping(target = "nomCourt", ignore = true) // Ne pas mapper (TODO)
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

    public <T extends IndicateurValeurAjoutee & Metadata> IndicateurValeurAjouteeDto toIndicateurValeurAjouteeDto(
            T dataset) {
        IndicateurValeurAjouteeDto iva = new IndicateurValeurAjouteeDto();
        iva.setResultats(dataset.getResultats());
        return iva;
    }
}
