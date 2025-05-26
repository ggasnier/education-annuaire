package com.guillaumegasnier.education.annuaire.repositories;

import com.guillaumegasnier.education.annuaire.domains.IndicePositionSocialeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndicePositionSocialeRepository extends CrudRepository<IndicePositionSocialeEntity, Long> {

    List<IndicePositionSocialeEntity> findAllByPkUaiOrderByPkAnneeDesc(String uai);

    Optional<IndicePositionSocialeEntity> findByPkUaiAndPkAnnee(String uai, Integer annee);
}
