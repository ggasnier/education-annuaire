package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementContactEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementContactPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementContactRepository extends JpaRepository<EtablissementContactEntity, EtablissementContactPK> {
}
