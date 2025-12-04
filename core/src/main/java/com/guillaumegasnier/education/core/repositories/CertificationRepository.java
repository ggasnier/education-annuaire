package com.guillaumegasnier.education.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, UUID> {
    Optional<CertificationEntity> findByCodeRNCP(String codeRNCP);
}
