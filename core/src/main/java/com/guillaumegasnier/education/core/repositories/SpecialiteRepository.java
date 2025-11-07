package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.SpecialiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialiteRepository extends JpaRepository<SpecialiteEntity, Long> {
    List<SpecialiteEntity> findAllByPkUai(String uai);
}
