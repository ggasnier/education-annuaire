package com.guillaumegasnier.education.core.repositories.territoires;

import com.guillaumegasnier.education.core.domains.territoires.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, String> {
    List<RegionEntity> findAllByOrderByCode();
}
