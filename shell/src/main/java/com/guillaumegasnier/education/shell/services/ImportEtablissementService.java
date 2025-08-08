package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import org.springframework.lang.NonNull;

import java.util.List;

@Deprecated
public interface ImportEtablissementService {

    String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    String createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    String createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    String createOrUpdateIPSColleges(@NonNull List<? extends IPSDataset> datasets);
}
