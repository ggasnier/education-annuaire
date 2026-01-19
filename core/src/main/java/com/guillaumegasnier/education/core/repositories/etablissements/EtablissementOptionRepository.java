package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementOptionPK;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementOptionRepository extends JpaRepository<EtablissementOptionEntity, EtablissementOptionPK> {

    List<EtablissementOptionEntity> findAllByPkUai(String uai);

    Optional<EtablissementOptionEntity> findByPkUaiAndPkOption(String uai, OptionEtablissement option);
}
