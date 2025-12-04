package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.repositories.*;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.validations.ValidUai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreEtablissementServiceImpl implements CoreEtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final NatureRepository natureRepository;
    private final ContratRepository contratRepository;
    private final SpecialiteRepository specialiteRepository;
    private final EtablissementLangueRepository langueRepository;
    private final EtablissementOptionRepository optionEtablissementRepository;
    private final OrganismeRepository organismeRepository;
    private final EtablissementSportRepository etablissementSportRepository;
    private final EtablissementMetadataRepository etablissementMetadataRepository;
    private final EtablissementContactRepository etablissementContactRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveEtablissements(@NonNull List<EtablissementEntity> entities) {
        etablissementRepository.saveAll(entities);
        em.flush();
        em.clear();
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Optional<EtablissementEntity> findEtablissement(@ValidUai String uai) {
        return etablissementRepository.findByUai(uai);
    }

    @Override
    public boolean isEtablissementExiste(String uai) {
        return etablissementRepository.existsById(uai);
    }

    @Override
    public EtablissementEntity getEtablissementReferenceByUai(String uai) {
        return etablissementRepository.getReferenceById(uai);
    }

    @Override
    public Optional<OrganismeEntity> findOrganisme(String nda) {
        return organismeRepository.findById(nda);
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
    public List<EtablissementOptionEntity> getOptionListByUai(String uai) {
        return optionEtablissementRepository.findAllByPkUai(uai);
    }

    @Override
    public List<EtablissementLangueEntity> getLangueListByUai(String uai) {
        return langueRepository.findAllByPkUaiOrderByPkEnseignementAscPkLangueAsc(uai);
    }

    @Override
    public List<EtablissementSportEntity> getSportListByUai(String uai) {
        return etablissementSportRepository.findAllByPkUaiOrderByPkCategorie(uai);
    }

//    @Override
//    public void saveSectionsSportEtudes(@NonNull List<SportEtudeEntity> entities) {
//        sportEtudeRepository.saveAll(entities);
//    }

//    @Override
//    public List<SectionSportiveEntity> getSectionSportiveListByUai(String uai) {
//        return sectionSportiveRepository.findAllByPkUai(uai);
//    }

    @Override
    public EtablissementEntity saveEtablissement(EtablissementEntity entity) {
        return etablissementRepository.save(entity);
    }

    @Override
    public List<EtablissementEntity> findAll() {
        return etablissementRepository.findAll();
    }

//    @Override
//    public List<EtablissementEntity> findEtablissementByNda(String numeroDeclarationActivite) {
//        return etablissementRepository.findByNumeroDeclarationActivite(numeroDeclarationActivite);
//    }

    @Override
    public List<EtablissementEntity> findEtablissementListByDepartement(String code) {
        return etablissementRepository.findAllByCommuneDepartementCode(code);
    }

    @Override
    public List<EtablissementSpecialiteEntity> getSpecialiteListByUai(String uai) {
        return specialiteRepository.findAllByPkUai(uai);
    }

    @Override
    public List<EtablissementEntity> findEtablissementListByCommune(String code) {
        return etablissementRepository.findAllByCommuneCodeOrderByNatureAscNomAsc(code);
    }

    @Override
    public void saveOrganisme(OrganismeEntity entity) {
        organismeRepository.save(entity);
    }

    @Override
    public void saveOrganismes(List<OrganismeEntity> entities) {
        organismeRepository.saveAll(entities);
    }

    @Override
    public void saveEtablissementSportEntity(List<EtablissementSportEntity> entities) {
        etablissementSportRepository.saveAll(entities);
    }

    @Override
    public int getNbrEtablissements() {
        return etablissementRepository.countByActif(true);
    }

    @Override
    public Optional<EtablissementMetadataEntity> findMetadata(String uai, Integer annee) {
        return etablissementMetadataRepository.findByPkUaiAndPkAnnee(uai, annee);
    }

    @Override
    public void saveMetadata(List<EtablissementMetadataEntity> entities) {
        etablissementMetadataRepository.saveAll(entities);
    }

    @Override
    public List<EtablissementMetadataEntity> getMetadataListByUai(String uai) {
        return etablissementMetadataRepository.findAllByPkUaiOrderByPkAnneeDesc(uai);
    }

    @Override
    public void saveContacts(@NonNull List<EtablissementContactEntity> entities) {
        etablissementContactRepository.saveAll(entities);
    }

    @Override
    public void saveSpecialites(List<EtablissementSpecialiteEntity> entities) {
        specialiteRepository.saveAll(entities); // TODO supprimer les spécialités ou trouver un moyen de virer les anciennes
    }

    /*@Override
    public void saveSectionsInternationales(@NonNull List<SectionInternationaleEntity> entities) {
        sectionInternationaleRepository.saveAll(entities);
    }*/

//    @Override
//    public void saveSectionsSporties(List<SectionSportiveEntity> entities) {
//        sectionSportiveRepository.saveAll(entities);
//    }

    @Override
    public void saveLangues(List<EtablissementLangueEntity> entities) {
        langueRepository.saveAll(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOptions(List<EtablissementOptionEntity> entities) {
        optionEtablissementRepository.saveAll(entities);
        em.flush();
        em.clear();
    }
}
