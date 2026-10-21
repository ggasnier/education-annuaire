package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.DomaineEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends JpaRepository<@NonNull DomaineEntity, @NonNull Integer> {
}
