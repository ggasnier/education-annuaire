package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import com.guillaumegasnier.education.core.repositories.ActionFormationRepository;
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
    private final ActionFormationRepository actionFormationRepository;
    private final RomeRepository romeRepository;
    //private final CertificationRepository certificationRepository;

    @Autowired
    public CoreFormationServiceImpl(FormationRepository formationRepository, ActionFormationRepository actionFormationRepository, RomeRepository romeRepository) {
        this.formationRepository = formationRepository;
        this.actionFormationRepository = actionFormationRepository;
        this.romeRepository = romeRepository;
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
    public void saveFormations(@NonNull List<FormationEntity> entities) {
        formationRepository.saveAll(entities);
    }

    @Override
    public Optional<FormationEntity> findFormationByOnisepId(Integer onisepId) {
        return formationRepository.findByOnisepId(onisepId);
    }

    @Override
    public void saveRomes(List<RomeEntity> entities) {
        romeRepository.saveAll(entities);
    }

    /*@Override
    public Optional<CertificationEntity> findCertificationByRNCP(String codeRNCP) {
        return certificationRepository.findByCodeRNCP(codeRNCP);
    }

    @Override
    public void saveCertification(CertificationEntity entity) {
        certificationRepository.save(entity);
    }*/

    @Override
    public Set<RomeEntity> getRomes(List<String> codes) {
        return romeRepository.findAllByCodeIn(codes);
    }

    @Override
    public Optional<FormationEntity> findFormationByParcoursupId(Integer parcoursupId) {
        return formationRepository.findByParcoursupId(parcoursupId);
    }

    @Override
    public Optional<ActionFormationEntity> findActionFormationByParcoursupId(Integer parcoursupId) {
        return actionFormationRepository.findByParcoursupId(parcoursupId);
    }

    @Override
    public void saveActionFormation(List<ActionFormationEntity> entities) {
        actionFormationRepository.saveAll(entities);
    }
}
