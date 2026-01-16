package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementLangueEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementLanguePK;
import com.guillaumegasnier.education.core.enums.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementLangueRepository extends JpaRepository<EtablissementLangueEntity, EtablissementLanguePK> {

    List<EtablissementLangueEntity> findAllByPkUai(String uai);

    List<EtablissementLangueEntity> findAllByPkUaiOrderByPkEnseignementAscPkLangueAsc(String uai);

    Optional<EtablissementLangueEntity> findByPkUaiAndPkLangueAndPkCategorieAndPkEnseignement(String uai, Langue langue, Langue.Categorie categorie, String enseignement);
}
