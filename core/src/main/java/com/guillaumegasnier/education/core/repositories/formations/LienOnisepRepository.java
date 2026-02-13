package com.guillaumegasnier.education.core.repositories.formations;

import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LienOnisepRepository extends JpaRepository<LienOnisepEntity, Long> {
    Optional<LienOnisepEntity> findByPkClefAndPkValeur(String clef, String valeur);
}
