package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementJPOEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementJPOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementJPORepository extends JpaRepository<EtablissementJPOEntity, EtablissementJPOPK> {
}
