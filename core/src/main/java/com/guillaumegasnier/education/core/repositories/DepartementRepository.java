package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.DepartementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<DepartementEntity, String> {
    List<DepartementEntity> findAllByOrderByCode();
}
