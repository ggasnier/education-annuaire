package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSportEntity;
import com.guillaumegasnier.education.core.enums.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementSportRepository extends JpaRepository<EtablissementSportEntity, Long> {

    List<EtablissementSportEntity> findAllByPkUaiOrderByPkCategorie(String uai);

    Optional<EtablissementSportEntity> findByPkUaiAndPkSportAndPkCategorie(String uai, Sport sport, Sport.Categorie categorie);
}
