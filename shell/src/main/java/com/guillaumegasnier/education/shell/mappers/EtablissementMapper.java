package com.guillaumegasnier.education.shell.mappers;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.etablissements.NatureEntity;
import com.guillaumegasnier.education.core.domains.recherche.DocumentEntity;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class EtablissementMapper {

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

}
