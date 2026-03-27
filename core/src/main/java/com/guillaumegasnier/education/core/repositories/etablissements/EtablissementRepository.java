package com.guillaumegasnier.education.core.repositories.etablissements;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementEntity, String> {

    Optional<EtablissementEntity> findByUai(String uai);

    @Query("""
            SELECT e FROM EtablissementEntity e
            LEFT JOIN FETCH e.contrat
            LEFT JOIN FETCH e.nature
            LEFT JOIN FETCH e.commune co
            LEFT JOIN FETCH co.pays
            LEFT JOIN FETCH co.departement d
            LEFT JOIN FETCH d.region
            LEFT JOIN FETCH d.academie
            LEFT JOIN FETCH e.masa
            WHERE e.uai = :uai
            """)
    Optional<EtablissementEntity> findEtablissementByUai(@Param("uai") String uai);

    List<EtablissementEntity> findAllByCommuneDepartementCode(String code);

    List<EtablissementEntity> findAllByCommuneCodeOrderByNatureAscNomAsc(String code);

    Optional<EtablissementEntity> findByMasaId(String masaId);

    int countByActif(boolean b);

    List<EtablissementEntity> findAllByActif(boolean b);

    @Query("""
            SELECT DISTINCT e FROM EtablissementEntity e
            LEFT JOIN FETCH e.contrat
            LEFT JOIN FETCH e.nature
            LEFT JOIN FETCH e.commune co
            LEFT JOIN FETCH co.pays
            LEFT JOIN FETCH co.departement d
            LEFT JOIN FETCH d.region
            LEFT JOIN FETCH d.academie
            LEFT JOIN FETCH e.options
            WHERE e.actif = true or e.actif IS NULL
            """)
    List<EtablissementEntity> findAllActifWithOptions();

    @Query("""
            SELECT DISTINCT e FROM EtablissementEntity e
            LEFT JOIN FETCH e.contrat
            LEFT JOIN FETCH e.nature
            LEFT JOIN FETCH e.commune co
            LEFT JOIN FETCH co.pays
            LEFT JOIN FETCH co.departement d
            LEFT JOIN FETCH d.region
            LEFT JOIN FETCH d.academie
            LEFT JOIN FETCH e.options
            WHERE e.actif = false
            """)
    List<EtablissementEntity> findAllNotActifWithOptions();
}
