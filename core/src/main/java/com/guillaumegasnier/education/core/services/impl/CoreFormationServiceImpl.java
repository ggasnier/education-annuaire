package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.formations.CertificationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.RomeEntity;
import com.guillaumegasnier.education.core.repositories.CertificationRepository;
import com.guillaumegasnier.education.core.repositories.FormationRepository;
import com.guillaumegasnier.education.core.repositories.RomeRepository;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CoreFormationServiceImpl implements CoreFormationService {

    private final FormationRepository formationRepository;
    private final RomeRepository romeRepository;
    private final CertificationRepository certificationRepository;

    @Autowired
    public CoreFormationServiceImpl(FormationRepository formationRepository, RomeRepository romeRepository, CertificationRepository certificationRepository) {
        this.formationRepository = formationRepository;
        this.romeRepository = romeRepository;
        this.certificationRepository = certificationRepository;
    }

    @Override
    public Optional<FormationEntity> findFormation(@NonNull UUID id) {
        return formationRepository.findById(id);
    }

    @Override
    public void saveFormation(FormationEntity entity) {
        formationRepository.save(entity);
    }

    @Override
    public Optional<FormationEntity> findFormationByOnisepId(Integer onisepId) {
        return formationRepository.findByOnisepId(onisepId);
    }

    @Override
    public void saveRomes(List<RomeEntity> entities) {
        romeRepository.saveAll(entities);
    }

    @Override
    public Optional<CertificationEntity> findCertificationByRNCP(String codeRNCP) {
        return certificationRepository.findByCodeRNCP(codeRNCP);
    }

    @Override
    public void saveCertification(CertificationEntity entity) {
        certificationRepository.save(entity);
    }

    @Override
    public Set<RomeEntity> getRomes(List<String> codes) {
        return romeRepository.findAllByCodeIn(codes);
    }
}
