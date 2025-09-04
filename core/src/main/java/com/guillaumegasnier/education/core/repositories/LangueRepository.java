package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.LangueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LangueRepository extends JpaRepository<LangueEntity, Long> {

    List<LangueEntity> findAllByPkUai(String uai);

    List<LangueEntity> findAllByPkUaiOrderByPkEnseignementAscPkLangueAsc(String uai);
}
