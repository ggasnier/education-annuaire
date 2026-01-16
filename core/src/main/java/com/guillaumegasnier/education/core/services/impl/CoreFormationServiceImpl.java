package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.referentiels.RomeEntity;
import com.guillaumegasnier.education.core.repositories.ActionFormationRepository;
import com.guillaumegasnier.education.core.repositories.FormationRepository;
import com.guillaumegasnier.education.core.repositories.OrganismeRepository;
import com.guillaumegasnier.education.core.repositories.RomeRepository;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private final OrganismeRepository organismeRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public CoreFormationServiceImpl(FormationRepository formationRepository, ActionFormationRepository actionFormationRepository, RomeRepository romeRepository, OrganismeRepository organismeRepository) {
        this.formationRepository = formationRepository;
        this.actionFormationRepository = actionFormationRepository;
        this.romeRepository = romeRepository;
        this.organismeRepository = organismeRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFormations(@NonNull List<FormationEntity> entities) {
        formationRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveActionFormation(@NonNull List<ActionFormationEntity> entities) {
        actionFormationRepository.saveAll(entities);
        em.flush();
        em.clear();
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
    public void saveOrganismes(List<OrganismeEntity> entities) {
        organismeRepository.saveAll(entities);
    }

    @Override
    public Optional<ActionFormationEntity> findActionFormation(UUID id) {
        return actionFormationRepository.findById(id);
    }
}
