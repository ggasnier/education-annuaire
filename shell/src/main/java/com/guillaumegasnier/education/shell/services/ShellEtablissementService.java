package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.SectionSportiveDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEtablissementService {

    String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    String createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    String createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    String createOrUpdateIPSColleges(@NonNull List<? extends IPSDataset> datasets);

    String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets);

}
