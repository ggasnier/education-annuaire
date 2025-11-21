package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtablissementSportRepository extends JpaRepository<EtablissementSportEntity, Long> {

    List<EtablissementSportEntity> findAllByPkUaiOrderByPkCategorie(String uai);
}
