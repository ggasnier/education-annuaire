package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
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

    private final MetierRepository metierRepository;

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
}
