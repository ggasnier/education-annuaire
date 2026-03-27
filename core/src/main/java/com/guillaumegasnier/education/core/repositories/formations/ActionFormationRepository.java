package com.guillaumegasnier.education.core.repositories.formations;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionFormationRepository extends JpaRepository<ActionFormationEntity, Long> {

    Optional<ActionFormationEntity> findByParcoursupId(Integer parcoursupId);

    List<ActionFormationEntity> findAllByEtablissementUai(String uai);

    List<ActionFormationEntity> findAllByEtablissementUaiOrderByFormationNom(String uai);
}
