package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.shell.datasets.FICHES;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellReferencielService {

    void createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    void createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    default void createOrUpdateCertificationsRncp(@NonNull FICHES fiches) {

    }
}
