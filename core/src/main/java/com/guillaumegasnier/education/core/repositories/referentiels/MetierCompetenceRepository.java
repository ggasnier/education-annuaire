package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.MetierCompetenceEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetierCompetenceRepository extends JpaRepository<@NonNull MetierCompetenceEntity, @NonNull Long> {

    Optional<MetierCompetenceEntity> findByPkMetierCodeAndPkCompetenceCode(String code, Integer code1);

    @Query("""
            SELECT mc FROM MetierCompetenceEntity mc
            JOIN FETCH mc.competence c
            JOIN FETCH c.macroCompetence mc2
            JOIN FETCH mc2.objectif o
            JOIN FETCH o.enjeu e
            JOIN FETCH e.domaine d
            WHERE mc.pk.metierCode = :code
            """)
    List<MetierCompetenceEntity> findAllByPkMetierCode(String code);
}
