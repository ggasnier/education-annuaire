package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.core.enums.*;
import com.guillaumegasnier.education.core.repositories.etablissements.*;
import com.guillaumegasnier.education.core.repositories.formations.OrganismeRepository;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.validations.etablissements.ValidUai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
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
    private final EtablissementJPORepository etablissementJPORepository;
    private final EtablissementIdentifiantRepository etablissementIdentifiantRepository;
    private final EtablissementMasaRepository etablissementMasaRepository;

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
    // @Transactional(propagation = Propagation.SUPPORTS)
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

    // @Override
    // public void saveSectionsSportEtudes(@NonNull List<SportEtudeEntity> entities)
    // {
    // sportEtudeRepository.saveAll(entities);
    // }

    // @Override
    // public List<SectionSportiveEntity> getSectionSportiveListByUai(String uai) {
    // return sectionSportiveRepository.findAllByPkUai(uai);
    // }

    @Override
    public EtablissementEntity saveEtablissement(EtablissementEntity entity) {
        return etablissementRepository.save(entity);
    }

    @Override
    public List<EtablissementEntity> findAll() {
        return etablissementRepository.findAll();
    }

    // @Override
    // public List<EtablissementEntity> findEtablissementByNda(String
    // numeroDeclarationActivite) {
    // return
    // etablissementRepository.findByNumeroDeclarationActivite(numeroDeclarationActivite);
    // }

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrganisme(OrganismeEntity entity) {
        organismeRepository.save(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrganismes(List<OrganismeEntity> entities) {
        organismeRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveEtablissementSportEntity(List<EtablissementSportEntity> entities) {
        etablissementSportRepository.saveAll(entities);
        em.flush();
        em.clear();
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveMetadata(List<EtablissementMetadataEntity> entities) {
        etablissementMetadataRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public List<EtablissementMetadataEntity> getMetadataListByUai(String uai) {
        return etablissementMetadataRepository.findAllByPkUaiOrderByPkAnneeDesc(uai);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveContacts(@NonNull List<EtablissementContactEntity> entities) {
        etablissementContactRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public List<EtablissementContactEntity> getContactListByUai(String uai) {
        return etablissementContactRepository.findAllByPkUai(uai);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveJPO(@NonNull List<EtablissementJPOEntity> entities) {
        etablissementJPORepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)

    public void saveMasa(@NonNull List<EtablissementMasaEntity> entities) {
        etablissementMasaRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public List<EtablissementJPOEntity> getJourneesPortesOuvertes(String uai) {
        return etablissementJPORepository.findAllByPkUaiOrderByPkDateDebut(uai);
    }

    @Override
    public EtablissementIdentifiantEntity findIdentifiant(EtablissementEntity entity, String clef, String valeur) {
        return etablissementIdentifiantRepository.findByPkUaiAndPkClefAndPkValeur(entity.getUai(), clef, valeur)
                .orElseGet(() -> etablissementIdentifiantRepository.save(new EtablissementIdentifiantEntity(entity, clef, valeur)));
    }

    @Override
    public Optional<EtablissementOptionEntity> findOption(String uai, OptionEtablissement option) {
        return optionEtablissementRepository.findByPkUaiAndPkOption(uai, option);
    }

    @Override
    public Optional<EtablissementSportEntity> findSport(String uai, Sport sport, Sport.Categorie categorie) {
        return etablissementSportRepository.findByPkUaiAndPkSportAndPkCategorie(uai, sport, categorie);
    }

    @Override
    public Optional<EtablissementJPOEntity> findJPO(String uai, LocalDate dateDebut, LocalDate dateFin) {
        return etablissementJPORepository.findByPkUaiAndPkDateDebutAndPkDateFin(uai, dateDebut, dateFin);
    }

    @Override
    public Optional<EtablissementLangueEntity> findLangue(String uai, Langue langue, Langue.Categorie categorie, String enseignement) {
        return langueRepository.findByPkUaiAndPkLangueAndPkCategorieAndPkEnseignement(uai, langue, categorie, enseignement);
    }

    @Override
    public Optional<EtablissementSpecialiteEntity> findSpecialite(String uai, SpecialiteBac specialite) {
        return specialiteRepository.findByPkUaiAndPkSpecialite(uai, specialite);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSpecialites(List<EtablissementSpecialiteEntity> entities) {
        specialiteRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    /*
     * @Override
     * public void saveSectionsInternationales(@NonNull
     * List<SectionInternationaleEntity> entities) {
     * sectionInternationaleRepository.saveAll(entities);
     * }
     */

    // @Override
    // public void saveSectionsSporties(List<SectionSportiveEntity> entities) {
    // sectionSportiveRepository.saveAll(entities);
    // }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLangues(List<EtablissementLangueEntity> entities) {
        langueRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOptions(List<EtablissementOptionEntity> entities) {
        optionEtablissementRepository.saveAll(entities);
        em.flush();
        em.clear();
    }

    @Override
    public List<EtablissementEntity> findEtablissementsActif() {
        return etablissementRepository.findAllByActif(true);
    }

    @Override
    public Optional<EtablissementMasaEntity> findMasa(String masaId) {
        return etablissementMasaRepository.findById(masaId);
    }

    @Override
    public Optional<EtablissementContactEntity> findContact(String uai, Contact contact, String valeur) {
        return etablissementContactRepository.findByPkUaiAndPkContactAndPkValeur(uai, contact, valeur);
    }
}
