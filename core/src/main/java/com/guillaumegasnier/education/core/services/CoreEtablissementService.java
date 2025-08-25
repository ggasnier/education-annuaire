package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CoreEtablissementService {

    void saveEtablissements(@NonNull List<EtablissementEntity> entities);

    void saveNatures(@NonNull List<NatureEntity> entities);

    void saveContrats(@NonNull List<ContratEntity> entities);

    void saveIPS(@NonNull List<IndicePositionSocialeEntity> entities);

    void saveEtablissement(Optional<EtablissementEntity> entity);

    void saveSpecialites(List<SpecialiteEntity> entities);

    Optional<EtablissementEntity> findEtablissement(@NonNull String uai);

    Optional<NatureEntity> findNature(String codeNature);

    Optional<ContratEntity> findContrat(String codeContrat);

    Optional<IndicePositionSocialeEntity> findIPS(String uai, int annee);


}
