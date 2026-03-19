package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.referentiels.CompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MacroCompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.repositories.referentiels.CertificationNationaleRepository;
import com.guillaumegasnier.education.core.repositories.referentiels.CompetenceRepository;
import com.guillaumegasnier.education.core.repositories.referentiels.MacroCompetenceRepository;
import com.guillaumegasnier.education.core.repositories.referentiels.MetierRepository;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoreReferentielServiceImpl implements CoreReferentielService {

    private final CertificationNationaleRepository certificationNationaleRepository;
    private final MetierRepository metierRepository;
    private final CompetenceRepository competenceRepository;
    private final MacroCompetenceRepository macroCompetenceRepository;

    @Override
    public void saveMetiers(List<MetierEntity> entities) {
        metierRepository.saveAll(entities);
    }

    @Override
    public Optional<MetierEntity> findMetier(String code) {
        return metierRepository.findById(code);
    }

    @Override
    public List<MetierEntity> findAll() {
        return metierRepository.findAll();
    }

    @Override
    public Optional<CompetenceEntity> findCompetence(int code) {
        return competenceRepository.findById(code);
    }

    @Override
    public Optional<MacroCompetenceEntity> findMacroCompetence(String code) {
        return macroCompetenceRepository.findById(code);
    }

    @Override
    public void saveCompetences(List<CompetenceEntity> entities) {
        competenceRepository.saveAll(entities);
    }

    @Override
    public long getNbrMetiers() {
        return metierRepository.count();
    }

    @Override
    public long getNbrCompetences() {
        return competenceRepository.count();
    }

    @Override
    public long getNbrCertifications() {
        return certificationNationaleRepository.count();
    }
}
