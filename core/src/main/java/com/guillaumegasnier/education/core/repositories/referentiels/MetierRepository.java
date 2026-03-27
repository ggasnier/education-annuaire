package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MetierRepository extends JpaRepository<MetierEntity, String> {

    Set<MetierEntity> findAllByCodeIn(List<String> codes);
}
