package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CoreFormationService {

    Optional<FormationEntity> findFormation(@NonNull Long id);

    void saveFormation(FormationEntity entity);

    void saveFormations(@NonNull List<FormationEntity> entities);

    Optional<FormationEntity> findFormationByOnisepId(Integer onisepId);

    void saveRomes(List<RomeEntity> entities);

    //Optional<CertificationEntity> findCertificationByRNCP(String certificationCode);

    //void saveCertification(CertificationEntity entity);

    Set<RomeEntity> getRomes(List<String> codes);

    Optional<FormationEntity> findFormationByParcoursupId(Integer parcoursupId);

    Optional<ActionFormationEntity> findActionFormationByParcoursupId(Integer codeInterneFormation);

    void saveActionFormation(List<ActionFormationEntity> entities);

    void saveOrganismes(List<OrganismeEntity> entities);

    Optional<ActionFormationEntity> findActionFormation(Long id);

    List<ActionFormationEntity> findFormations(String uai);

    Optional<LienOnisepEntity> findLienOnisep(String clef, String valeur);

    long getNbrFormations();
}
