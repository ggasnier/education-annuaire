package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementJPOEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementJPOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementJPORepository extends JpaRepository<EtablissementJPOEntity, EtablissementJPOPK> {

    List<EtablissementJPOEntity> findAllByPkUaiOrderByPkDateDebut(String uai);

    Optional<EtablissementJPOEntity> findByPkUaiAndPkDateDebutAndPkDateFin(String uai, LocalDate dateDebut, LocalDate dateFin);
}
