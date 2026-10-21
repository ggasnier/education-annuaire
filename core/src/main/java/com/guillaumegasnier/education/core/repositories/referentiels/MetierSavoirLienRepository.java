package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.MetierSavoirLienEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetierSavoirLienRepository extends CrudRepository<@NonNull MetierSavoirLienEntity, @NonNull Long> {
    Optional<MetierSavoirLienEntity> findByPkCodeMetierAndPkCodeSavoir(String codeMetier, int codeSavoir);

    @Query("""
            SELECT msl FROM MetierSavoirLienEntity msl
            JOIN FETCH msl.savoir s
            WHERE msl.pk.codeMetier = :code
            """)
    List<MetierSavoirLienEntity> findAllByPkCodeMetier(@NonNull String code);
}
