package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.MacroCompetenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MacroCompetenceRepository extends JpaRepository<MacroCompetenceEntity, String> {
}
