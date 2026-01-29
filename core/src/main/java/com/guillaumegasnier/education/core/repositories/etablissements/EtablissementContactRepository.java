package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementContactEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementContactPK;
import com.guillaumegasnier.education.core.enums.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementContactRepository extends JpaRepository<EtablissementContactEntity, EtablissementContactPK> {
    List<EtablissementContactEntity> findAllByPkUai(String uai);

    Optional<EtablissementContactEntity> findByPkUaiAndPkContactAndPkValeur(String uai, Contact contact, String valeur);
}
