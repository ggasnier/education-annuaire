package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.SectionInternationaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionInternationaleRepository extends JpaRepository <SectionInternationaleEntity, Long> {
}
