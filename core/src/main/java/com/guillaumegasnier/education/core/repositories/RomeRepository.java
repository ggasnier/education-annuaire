package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RomeRepository extends JpaRepository<RomeEntity, String> {

    Set<RomeEntity> findAllByCodeIn(List<String> codes);
}
