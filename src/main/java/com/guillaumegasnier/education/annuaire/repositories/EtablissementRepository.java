package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementEntity, String> {

    @Query("SELECT e FROM EtablissementEntity e LEFT JOIN FETCH e.contacts WHERE e.uai = :uai")
    Optional<EtablissementEntity> findWithContactsByUai(@Param("uai") String uai);
}
