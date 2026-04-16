package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheFormationEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.recherche.*;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.services.impl.recherche.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Façade — délègue chaque opération au service spécialisé correspondant.
 * Aucune logique métier ici.
 */
@Slf4j
@Service
public class CoreRechercheServiceImpl implements CoreRechercheService {

    private final EtablissementRechercheService etablissementService;
    private final MetierRechercheService metierService;
    private final CompetenceRechercheService competenceService;
    private final CertificationRechercheService certificationService;
    private final FormationRechercheService formationService;

    @Autowired
    public CoreRechercheServiceImpl(
            EtablissementRechercheService etablissementService,
            MetierRechercheService metierService,
            CompetenceRechercheService competenceService,
            CertificationRechercheService certificationService,
            FormationRechercheService formationService) {
        this.etablissementService = etablissementService;
        this.metierService = metierService;
        this.competenceService = competenceService;
        this.certificationService = certificationService;
        this.formationService = formationService;
    }

    // -------------------------------------------------------------------------
    // Gestion de l'index
    // -------------------------------------------------------------------------

    @Override
    public void recreateEtablissementsIndex() {
        etablissementService.recreateIndex();
    }

    // -------------------------------------------------------------------------
    // Recherche
    // -------------------------------------------------------------------------

    @Override
    public RechercheEtablissementDTO rechercheEtablissements(@NonNull RechercheCriteria criteria) {
        return etablissementService.rechercheEtablissements(criteria);
    }

    @Override
    public RechercheMetierDTO rechercheMetiers(@NonNull RechercheCriteria criteria) {
        return metierService.rechercheMetiers(criteria);
    }

    @Override
    public RechercheCompetenceDTO rechercheCompetences(@NonNull RechercheCriteria criteria) {
        return competenceService.rechercheCompetences(criteria);
    }

    @Override
    public RechercheCertificationDTO rechercheCertifications(@NonNull RechercheCriteria criteria) {
        return certificationService.rechercheCertifications(criteria);
    }

    @Override
    public RechercheFormationDTO rechercheFormations(@NonNull RechercheCriteria criteria) {
        return formationService.rechercheFormations(criteria);
    }

    // -------------------------------------------------------------------------
    // Persistence
    // -------------------------------------------------------------------------

    @Override
    public void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities) {
        etablissementService.saveEtablissements(entities);
    }

    @Override
    public void deleteEtablissements(List<RechercheEtablissementEntity> entities) {
        etablissementService.deleteEtablissements(entities);
    }

    @Override
    public void saveMetiers(@NonNull List<RechercheMetierEntity> entities) {
        metierService.saveMetiers(entities);
    }

    @Override
    public void saveFormations(List<RechercheFormationEntity> entities) {
        formationService.saveFormations(entities);
    }
}
