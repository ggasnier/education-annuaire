package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.datasets.DepartementDataset;
import com.guillaumegasnier.education.annuaire.datasets.EnEtablissementDataset;
import com.guillaumegasnier.education.annuaire.datasets.RegionDataset;
import com.guillaumegasnier.education.annuaire.domains.DepartementEntity;
import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import com.guillaumegasnier.education.annuaire.domains.RegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ImportMapper {

    @Mapping(target = "nature", ignore = true)
    @Mapping(target = "etat", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "complement", ignore = true)
    @Mapping(target = "commune", ignore = true)
    @Mapping(target = "codePostal", ignore = true)
    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract EtablissementEntity toEtablissementEntity(EnEtablissementDataset enEtablissementDataset);

    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract RegionEntity toRegionEntity(RegionDataset regionDataset);

    @Mapping(target = "region", ignore = true)
    @Mapping(target = "academie", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract DepartementEntity toDepartementEntity(DepartementDataset departementDataset);

}
