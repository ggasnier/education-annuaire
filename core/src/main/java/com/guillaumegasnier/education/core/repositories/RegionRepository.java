package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, String> {
    List<RegionEntity> findAllByOrderByCode();
}
