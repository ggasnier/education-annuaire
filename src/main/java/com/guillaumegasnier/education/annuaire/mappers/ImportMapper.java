package com.guillaumegasnier.education.annuaire.mappers;

import com.guillaumegasnier.education.annuaire.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.annuaire.datasets.etablissements.EnEtablissementDataset;
import com.guillaumegasnier.education.annuaire.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.annuaire.datasets.references.AcademieDataset;
import com.guillaumegasnier.education.annuaire.datasets.references.CommuneDataset;
import com.guillaumegasnier.education.annuaire.datasets.references.DepartementDataset;
import com.guillaumegasnier.education.annuaire.datasets.references.RegionDataset;
import com.guillaumegasnier.education.annuaire.domains.*;
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
    public abstract EtablissementEntity toEtablissementEntity(EnEtablissementDataset dataset);

    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract RegionEntity toRegionEntity(RegionDataset dataset);

    @Mapping(target = "region", ignore = true)
    @Mapping(target = "academie", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract DepartementEntity toDepartementEntity(DepartementDataset dataset);

    @Mapping(target = "codePays", constant = "FR")
    @Mapping(target = "departement", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract CommuneEntity toCommuneEntity(CommuneDataset dataset);

    @Mapping(target = "nomCourt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract NatureEntity toNatureEntity(NatureDataset dataset);

    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract ContratEntity toContratEntity(ContratDataset dataset);

    @Mapping(target = "updatedAt", ignore = true) // Ne pas mapper
    @Mapping(target = "createdAt", ignore = true) // Ne pas mapper
    public abstract AcademieEntity toAcademieEntity(AcademieDataset dataset);
}
