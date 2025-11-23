package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.NatureEntity;
import com.guillaumegasnier.education.core.domains.organismes.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.recherche.DocumentEntity;
import com.guillaumegasnier.education.core.dto.IndicePositionSocialeDto;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nomNature", source = "nature.nom")
    @Mapping(target = "nomContrat", source = "contrat.nom")
    @Mapping(target = "nomCommune", source = "commune.nom")
    @Mapping(target = "docType", constant = "etablissements")
    @Mapping(target = "docId", source = "uai")
    @Mapping(target = "description", ignore = true) // TODO
    @Mapping(target = "codeNature", source = "nature.code")
    @Mapping(target = "codeContrat", source = "contrat.code")
    @Mapping(target = "codeCommune", source = "commune.code")
    public abstract DocumentEntity toDocumentEntity(EtablissementEntity etablissementEntity);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "groupes", ignore = true)
    @Mapping(target = "nda", source = "numeroDeclarationActivite")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "commune", ignore = true)
    @Mapping(target = "certificationQualiopi", ignore = true)
    public abstract OrganismeEntity toOrganismeEntity(TravailOrganismeFormationDataset dataset);

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
}
