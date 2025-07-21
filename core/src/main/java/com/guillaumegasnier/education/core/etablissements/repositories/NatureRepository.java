package com.guillaumegasnier.education.core.etablissements.repositories;

import com.guillaumegasnier.education.core.etablissements.entities.NatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NatureRepository extends JpaRepository<NatureEntity, String> {
    List<NatureEntity> findAllByOrderByCode();
}
