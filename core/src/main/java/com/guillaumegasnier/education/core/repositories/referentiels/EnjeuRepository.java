package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.EnjeuEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnjeuRepository extends JpaRepository<@NonNull EnjeuEntity, @NonNull String> {
}
