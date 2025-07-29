package com.guillaumegasnier.education.core.etablissements.repositories;

import com.guillaumegasnier.education.core.etablissements.entities.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementEntity, String> {

}
