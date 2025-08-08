package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.NatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NatureRepository extends JpaRepository<NatureEntity, String> {
    List<NatureEntity> findAllByOrderByCode();
}
