package com.guillaumegasnier.education.core.references.repositories;

import com.guillaumegasnier.education.core.references.entities.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<PaysEntity, String> {
}
