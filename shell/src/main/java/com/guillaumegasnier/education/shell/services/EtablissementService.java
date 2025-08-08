package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.ContratEntity;
import com.guillaumegasnier.education.core.domains.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.core.domains.NatureEntity;
import com.guillaumegasnier.education.shell.datasets.etablissements.ContratDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.NatureDataset;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface EtablissementService {

    void saveEtablissements(List<EtablissementEntity> entities);

    void saveNatures(List<NatureEntity> entities);

    void saveContrats(List<ContratEntity> entities);

    Optional<EtablissementEntity> findEtablissement(String uai);

    Optional<NatureEntity> findNature(String codeNature);

    Optional<ContratEntity> findContrat(String codeContrat);

    Optional<IndicePositionSocialeEntity> getIPS(String uai, int annee);

    void saveIPS(List<IndicePositionSocialeEntity> entities);

    String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    String createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    String createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    String createOrUpdateIPSColleges(@NonNull List<? extends IPSDataset> datasets);

}
