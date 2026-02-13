package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementMasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementMasaRepository extends JpaRepository<EtablissementMasaEntity, String> {
}
