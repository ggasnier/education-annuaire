package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.ContratEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratRepository extends JpaRepository<ContratEntity, String> {
}
