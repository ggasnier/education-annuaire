package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.CertificationNationaleEntity;
import com.guillaumegasnier.education.core.enums.TypologieDiplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificationNationaleRepository extends JpaRepository<CertificationNationaleEntity, String> {

    Optional<CertificationNationaleEntity> findByTypologieDiplomeAndNomIgnoreCaseAndActifAndNouvelleCertificationIsNull(
            TypologieDiplome typologieDiplome, String nom, boolean actif);
}
