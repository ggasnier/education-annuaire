package com.guillaumegasnier.education.core.etablissements.services.impl;

import com.guillaumegasnier.education.core.etablissements.entities.ContratEntity;
import com.guillaumegasnier.education.core.etablissements.entities.EtablissementEntity;
import com.guillaumegasnier.education.core.etablissements.entities.IndicePositionSocialeEntity;
import com.guillaumegasnier.education.core.etablissements.entities.NatureEntity;
import com.guillaumegasnier.education.core.etablissements.repositories.ContratRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.EtablissementRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.IndicePositionSocialeRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.NatureRepository;
import com.guillaumegasnier.education.core.etablissements.services.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementServiceImpl implements EtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final NatureRepository natureRepository;
    private final ContratRepository contratRepository;
    private final IndicePositionSocialeRepository ipsRepository;

    @Autowired
    public EtablissementServiceImpl(EtablissementRepository etablissementRepository, NatureRepository natureRepository, ContratRepository contratRepository, IndicePositionSocialeRepository ipsRepository) {
        this.etablissementRepository = etablissementRepository;
        this.natureRepository = natureRepository;
        this.contratRepository = contratRepository;
        this.ipsRepository = ipsRepository;
    }

    @Override
    public void saveEtablissements(List<EtablissementEntity> etablissements) {
        etablissementRepository.saveAll(etablissements);
    }

    @Override
    public void saveNatures(List<NatureEntity> natures) {
        natureRepository.saveAll(natures);
    }

    @Override
    public void saveContrats(List<ContratEntity> contrats) {
        contratRepository.saveAll(contrats);
    }

    @Override
    public Optional<EtablissementEntity> findEtablissement(String uai) {
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
    public Optional<IndicePositionSocialeEntity> getIPS(String uai, int annee) {
        return ipsRepository.findByPkUaiAndPkAnnee(uai, annee);
    }

    @Override
    public void saveIPS(List<IndicePositionSocialeEntity> entities) {
        ipsRepository.saveAll(entities);
    }
    
}
