package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.references.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<PaysEntity, String> {
}
