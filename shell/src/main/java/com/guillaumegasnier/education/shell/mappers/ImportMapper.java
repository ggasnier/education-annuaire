package com.guillaumegasnier.education.shell.mappers;


import com.guillaumegasnier.education.core.etablissements.entities.ContratEntity;
import com.guillaumegasnier.education.core.etablissements.entities.EtablissementEntity;
import com.guillaumegasnier.education.core.etablissements.entities.NatureEntity;
import com.guillaumegasnier.education.core.references.entities.AcademieEntity;
import com.guillaumegasnier.education.core.references.entities.CommuneEntity;
import com.guillaumegasnier.education.core.references.entities.DepartementEntity;
import com.guillaumegasnier.education.core.references.entities.RegionEntity;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EnEtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.references.AcademieDataset;
import com.guillaumegasnier.education.shell.datasets.references.CommuneDataset;
import com.guillaumegasnier.education.shell.datasets.references.DepartementDataset;
import com.guillaumegasnier.education.shell.datasets.references.RegionDataset;
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
