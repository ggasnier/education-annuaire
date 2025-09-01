package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.OptionEtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionEtablissementRepository extends JpaRepository<OptionEtablissementEntity, Long> {

    List<OptionEtablissementEntity> findAllByPkUai(String uai);
}
