package com.guillaumegasnier.education.core.etablissements.services;

import com.guillaumegasnier.education.core.etablissements.entities.ContratEntity;
import com.guillaumegasnier.education.core.etablissements.entities.EtablissementEntity;
import com.guillaumegasnier.education.core.etablissements.entities.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.core.etablissements.entities.NatureEntity;

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
}
