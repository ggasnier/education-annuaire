package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.references.*;
import com.guillaumegasnier.education.core.repositories.*;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CoreReferenceServiceImpl implements CoreReferenceService {

    private final PaysRepository paysRepository;
    private final AcademieRepository academieRepository;
    private final RegionRepository regionRepository;
    private final DepartementRepository departementRepository;
    private final CommuneRepository communeRepository;

    @Autowired
    public CoreReferenceServiceImpl(PaysRepository paysRepository, AcademieRepository academieRepository, RegionRepository regionRepository, DepartementRepository departementRepository, CommuneRepository communeRepository) {
        this.paysRepository = paysRepository;
        this.academieRepository = academieRepository;
        this.regionRepository = regionRepository;
        this.departementRepository = departementRepository;
        this.communeRepository = communeRepository;
    }

    @Override
    public void savePays(List<PaysEntity> entities) {
        paysRepository.saveAll(entities);
    }

    @Override
    public void saveAcademies(List<AcademieEntity> entities) {
        academieRepository.saveAll(entities);
    }

    @Override
    public void saveRegions(List<RegionEntity> entities) {
        regionRepository.saveAll(entities);
    }

    @Override
    public void saveDepartements(List<DepartementEntity> entities) {
        departementRepository.saveAll(entities);
    }

    @Override
    public void saveCommunes(List<CommuneEntity> entities) {
        communeRepository.saveAll(entities);
    }

    @Override
    @Cacheable("region")
    public RegionEntity getRegion(String codeRegion) {
        return regionRepository.findById(codeRegion).orElse(null);
    }

    @Override
    @Cacheable("academie")
    public AcademieEntity getAcademie(String codeAcademie) {
        return academieRepository.findById(codeAcademie).orElse(null);
    }

    @Override
    @Cacheable("departement")
    public DepartementEntity getDepartement(String codeDepartement) {
        return departementRepository.findById(codeDepartement).orElse(null);
    }

    @Override
    public Optional<CommuneEntity> findCommuneByNom(String nomCommune) {
        try {
            return communeRepository.findByNom(nomCommune);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.warn("Trop de résultats pour la commune {}", nomCommune);
            return Optional.empty();
        }
    }

    @Override
    public PaysEntity getPays(String codePays) {
        return paysRepository.getReferenceById(codePays);
    }

    @Override
    @Cacheable("commune")
    public Optional<CommuneEntity> findCommune(@NonNull String codeCommune) {
        return communeRepository.findById(codeCommune);
    }
}
