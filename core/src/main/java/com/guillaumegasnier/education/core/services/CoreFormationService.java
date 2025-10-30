package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.formations.CertificationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.RomeEntity;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CoreFormationService {

    Optional<FormationEntity> findFormation(@NonNull UUID id);

    void saveFormation(FormationEntity entity);

    void saveFormations(@NonNull List<FormationEntity> entities);

    Optional<FormationEntity> findFormationByOnisepId(Integer onisepId);

    void saveRomes(List<RomeEntity> entities);

    Optional<CertificationEntity> findCertificationByRNCP(String certificationCode);

    void saveCertification(CertificationEntity entity);

    Set<RomeEntity> getRomes(List<String> codes);
}
