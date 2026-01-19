package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSpecialiteEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementSpecialitePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialiteRepository extends JpaRepository<EtablissementSpecialiteEntity, EtablissementSpecialitePK> {
    List<EtablissementSpecialiteEntity> findAllByPkUai(String uai);
}
