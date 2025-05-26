package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.CommuneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<CommuneEntity, String> {
}
