package com.guillaumegasnier.education.core.repositories.formations;

import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<FormationEntity, Long> {
    Optional<FormationEntity> findByOnisepId(Integer onisepId);

    Optional<FormationEntity> findByParcoursupId(Integer parcoursupId);
}
