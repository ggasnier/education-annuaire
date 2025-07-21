package com.guillaumegasnier.education.core.references.repositories;

import com.guillaumegasnier.education.core.references.entities.CommuneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<CommuneEntity, String> {
}
