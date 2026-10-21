package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.referentiels.*;
import com.guillaumegasnier.education.core.enums.TypologieDiplome;
import com.guillaumegasnier.education.core.repositories.referentiels.*;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private final ObjectifRepository objectifRepository;
    private final MacroCompetenceRepository macroCompetenceRepository;
    private final MetierSavoirRepository metierSavoirRepository;
    private final MetierSavoirLienRepository metierSavoirLienRepository;
    private final EnjeuRepository enjeuRepository;
    private final DomaineRepository domaineRepository;
    private final MetierCompetenceRepository metierCompetenceRepository;

    @PersistenceContext
    private EntityManager em;

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
    public Optional<CertificationNationaleEntity> findCertification(String code) {
        if (code == null) return Optional.empty();
        return certificationNationaleRepository.findById(code);
    }

    @Override
    public void saveCompetences(List<CompetenceEntity> entities) {
        competenceRepository.saveAll(entities);
    }

    @Override
    public void saveMacroCompetences(@NonNull List<MacroCompetenceEntity> entities) {
        macroCompetenceRepository.saveAll(entities);
    }

    @Override
    public void saveObjectifs(@NonNull List<ObjectifEntity> entities) {
        objectifRepository.saveAll(entities);
    }

    @Override
    public Optional<ObjectifEntity> findObjectif(@NonNull String code) {
        return objectifRepository.findById(code);
    }

    @Override
    public Optional<EnjeuEntity> findEnjeu(@NonNull String code) {
        return enjeuRepository.findById(code);
    }

    @Override
    public void saveEnjeux(@NonNull List<EnjeuEntity> entities) {
        enjeuRepository.saveAll(entities);
    }

    @Override
    public void saveDomaines(@NonNull List<DomaineEntity> entities) {
        domaineRepository.saveAll(entities);
    }

    @Override
    public Optional<DomaineEntity> findDomaine(int code) {
        return domaineRepository.findById(code);
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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveCertifications(List<CertificationNationaleEntity> entities) {
        certificationNationaleRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public Optional<CertificationNationaleEntity> findCertification(TypologieDiplome typologieDiplome, String nom, boolean actif) {
        return certificationNationaleRepository.findByTypologieDiplomeAndNomIgnoreCaseAndActifAndNouvelleCertificationIsNull(typologieDiplome, nom, actif);
    }

    @Override
    public void saveSavoirs(List<MetierSavoirEntity> savoirs) {
        metierSavoirRepository.saveAll(savoirs);
    }

    @Override
    public Optional<MetierSavoirEntity> findSavoir(int codeOgr) {
        return metierSavoirRepository.findById(codeOgr);
    }

    @Override
    public void saveMetierSavoirLien(List<MetierSavoirLienEntity> liens) {
        metierSavoirLienRepository.saveAll(liens);
    }

    @Override
    public void saveMetierCompetenceLien(List<MetierCompetenceEntity> entities) {
        metierCompetenceRepository.saveAll(entities);
    }

    @Override
    public Optional<MetierSavoirLienEntity> findMetierSavoirLien(String codeMetier, int codeSavoir) {
        return metierSavoirLienRepository.findByPkCodeMetierAndPkCodeSavoir(codeMetier, codeSavoir);
    }

    @Override
    public List<MetierSavoirLienEntity> getMetierSavoirList(@NonNull String code) {
        return metierSavoirLienRepository.findAllByPkCodeMetier(code);
    }

    @Override
    public List<MetierCompetenceEntity> getMetierCompetenceList(String code) {
        return metierCompetenceRepository.findAllByPkMetierCode(code);
    }

    public Optional<MetierCompetenceEntity> findMetierCompetence(String code, Integer code1) {
        return metierCompetenceRepository.findByPkMetierCodeAndPkCompetenceCode(code, code1);
    }
}
