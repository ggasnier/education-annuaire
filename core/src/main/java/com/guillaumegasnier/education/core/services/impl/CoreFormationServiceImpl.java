package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.LienOnisepEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.repositories.formations.ActionFormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.FormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.LienOnisepRepository;
import com.guillaumegasnier.education.core.repositories.formations.OrganismeRepository;
import com.guillaumegasnier.education.core.repositories.referentiels.MetierRepository;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoreFormationServiceImpl implements CoreFormationService {

    private final FormationRepository formationRepository;
    private final ActionFormationRepository actionFormationRepository;
    private final MetierRepository metierRepository;
    private final OrganismeRepository organismeRepository;
    private final LienOnisepRepository lienOnisepRepository;

    @PersistenceContext
    private EntityManager em;

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
    public Optional<FormationEntity> findFormation(@NonNull Long id) {
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRomes(List<MetierEntity> entities) {
        metierRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public Set<MetierEntity> getRomes(List<String> codes) {
        return metierRepository.findAllByCodeIn(codes);
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrganismes(List<OrganismeEntity> entities) {
        organismeRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public Optional<ActionFormationEntity> findActionFormation(Long id) {
        return actionFormationRepository.findById(id);
    }

    @Override
    public List<ActionFormationEntity> findFormations(String uai) {
        return actionFormationRepository.findAllByEtablissementUaiOrderByFormationNom(uai);
    }

    @Override
    public Optional<LienOnisepEntity> findLienOnisep(String clef, String valeur) {
        return lienOnisepRepository.findByPkClefAndPkValeur(clef, valeur);
    }

    @Override
    public long getNbrFormations() {
        return formationRepository.count();
    }

    @Override
    public List<FormationEntity> findAll() {
        return formationRepository.findAll();
    }
}
