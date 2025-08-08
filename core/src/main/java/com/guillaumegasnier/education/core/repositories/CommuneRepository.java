package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.CommuneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<CommuneEntity, String> {
}
