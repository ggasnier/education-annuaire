package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActionFormationRepository extends JpaRepository<ActionFormationEntity, UUID> {

    Optional<ActionFormationEntity> findByParcoursupId(Integer parcoursupId);
}
