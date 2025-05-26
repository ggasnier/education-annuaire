package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<PaysEntity, String> {
}
