package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.ContratEntity;
import com.guillaumegasnier.education.core.domains.etablissements.EtablissementContactEntity;
import com.guillaumegasnier.education.core.enums.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratRepository extends JpaRepository<ContratEntity, String> {

    Optional<EtablissementContactEntity> findByPkUaiAndPkContactAndPKValeur(String uai, Contact contact, String valeur);
}
