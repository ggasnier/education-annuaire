package com.guillaumegasnier.education.core.repositories.territoires;

import com.guillaumegasnier.education.core.domains.territoires.CommuneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommuneRepository extends JpaRepository<CommuneEntity, String> {

    Optional<CommuneEntity> findByNomIgnoreCase(String nomCommune);
}
