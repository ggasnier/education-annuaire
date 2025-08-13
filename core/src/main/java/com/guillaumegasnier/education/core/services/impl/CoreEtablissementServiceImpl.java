package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.ContratEntity;
import com.guillaumegasnier.education.core.domains.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.core.domains.NatureEntity;
import com.guillaumegasnier.education.core.repositories.ContratRepository;
import com.guillaumegasnier.education.core.repositories.EtablissementRepository;
import com.guillaumegasnier.education.core.repositories.IndicePositionSocialeRepository;
import com.guillaumegasnier.education.core.repositories.NatureRepository;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CoreEtablissementServiceImpl implements CoreEtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final NatureRepository natureRepository;
    private final ContratRepository contratRepository;
    private final IndicePositionSocialeRepository indicePositionSocialeRepository;

    @Autowired
    public CoreEtablissementServiceImpl(EtablissementRepository etablissementRepository, NatureRepository natureRepository, ContratRepository contratRepository, IndicePositionSocialeRepository indicePositionSocialeRepository) {
        this.etablissementRepository = etablissementRepository;
        this.natureRepository = natureRepository;
        this.contratRepository = contratRepository;
        this.indicePositionSocialeRepository = indicePositionSocialeRepository;
    }

    @Override
    public void saveEtablissements(@NonNull List<EtablissementEntity> entities) {
        etablissementRepository.saveAll(entities);
    }

    @Override
    public void saveNatures(@NonNull List<NatureEntity> entities) {
        natureRepository.saveAll(entities);
    }

    @Override
    public void saveContrats(@NonNull List<ContratEntity> entities) {
        contratRepository.saveAll(entities);
    }

    @Override
    public void saveIPS(@NonNull List<IndicePositionSocialeEntity> entities) {
        indicePositionSocialeRepository.saveAll(entities);
    }

    @Override
    public Optional<EtablissementEntity> findEtablissement(@NonNull String uai) {
        return etablissementRepository.findById(uai);
    }

    @Override
    @Cacheable("nature")
    public Optional<NatureEntity> findNature(String codeNature) {
        return natureRepository.findById(codeNature);
    }

    @Override
    @Cacheable("contrat")
    public Optional<ContratEntity> findContrat(String codeContrat) {
        return contratRepository.findById(codeContrat);
    }

    @Override
    public Optional<IndicePositionSocialeEntity> findIPS(String uai, int annee) {
        return indicePositionSocialeRepository.findByPkUaiAndPkAnnee(uai, annee);
    }

    @Override
    public void saveEtablissement(@NonNull Optional<EtablissementEntity> entity) {
        entity.ifPresent(etablissementRepository::save);
    }
}
