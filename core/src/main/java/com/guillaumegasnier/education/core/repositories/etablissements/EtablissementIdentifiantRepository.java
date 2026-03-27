package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementIdentifiantEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementIdentifiantPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtablissementIdentifiantRepository extends JpaRepository<EtablissementIdentifiantEntity, EtablissementIdentifiantPK> {
    Optional<EtablissementIdentifiantEntity> findByPkUaiAndPkClefAndPkValeur(String uai, String clef, String valeur);
}
