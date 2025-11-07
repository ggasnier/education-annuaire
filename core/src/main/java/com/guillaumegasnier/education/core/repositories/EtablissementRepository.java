package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementEntity, UUID> {

    Optional<EtablissementEntity> findByUai(String uai);

    List<EtablissementEntity> findByNumeroDeclarationActivite(String numeroDeclarationActivite);

    List<EtablissementEntity> findAllByCommuneDepartementCode(String code);

    List<EtablissementEntity> findAllByCommuneCodeOrderByNatureAscNomAsc(String code);
}
