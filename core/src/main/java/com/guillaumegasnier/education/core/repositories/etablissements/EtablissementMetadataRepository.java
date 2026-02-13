package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementMetadataRepository extends JpaRepository<EtablissementMetadataEntity, Long> {
    Optional<EtablissementMetadataEntity> findByPkUaiAndPkAnnee(String uai, Integer annee);

    List<EtablissementMetadataEntity> findAllByPkUaiOrderByPkAnneeDesc(String uai);
}
