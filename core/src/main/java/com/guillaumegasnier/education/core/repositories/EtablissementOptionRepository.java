package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtablissementOptionRepository extends JpaRepository<EtablissementOptionEntity, EtablissementOptionPK> {

    List<EtablissementOptionEntity> findAllByPkUai(String uai);
}
