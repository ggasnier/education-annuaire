package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.NatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureRepository extends JpaRepository<NatureEntity, String> {
}
