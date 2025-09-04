package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.SectionSportiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionSportiveRepository extends JpaRepository<SectionSportiveEntity, Long> {
    
    List<SectionSportiveEntity> findAllByPkUai(String uai);
}
