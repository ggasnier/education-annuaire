package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.repositories.*;
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
    private final SpecialiteRepository specialiteRepository;
    private final SectionInternationaleRepository sectionInternationaleRepository;

    @Autowired
    public CoreEtablissementServiceImpl(EtablissementRepository etablissementRepository, NatureRepository natureRepository, ContratRepository contratRepository, IndicePositionSocialeRepository indicePositionSocialeRepository, SpecialiteRepository specialiteRepository, SectionInternationaleRepository sectionInternationaleRepository) {
        this.etablissementRepository = etablissementRepository;
        this.natureRepository = natureRepository;
        this.contratRepository = contratRepository;
        this.indicePositionSocialeRepository = indicePositionSocialeRepository;
        this.specialiteRepository = specialiteRepository;
        this.sectionInternationaleRepository = sectionInternationaleRepository;
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

    @Override
    public void saveSpecialites(List<SpecialiteEntity> entities) {
        specialiteRepository.saveAll(entities); // TODO supprimer les spécialités ou trouver un moyen de virer les anciennes
    }

    @Override
    public void saveSectionsInternationales(@NonNull List<SectionInternationaleEntity> entities) {
        sectionInternationaleRepository.saveAll(entities);
    }
}
