package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEtablissementService {

    String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    String createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    String createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    String createOrUpdateIPS(@NonNull List<? extends IPSDataset> datasets, String categorie);

    String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets);

    String createOrUpdateSectionsSportEtudes(@NonNull List<SectionSportEtudeDataset> datasets);

    String createOrUpdateLangues(@NonNull List<LangueDataset> datasets);

    String createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets);

    String createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets);

    String createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets);

    String createOrUpdateEtablissementsRecherche();
}
