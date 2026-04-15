package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellRechercheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShellRechercheServiceImpl implements ShellRechercheService {

    private final CoreRechercheService coreRechercheService;
    private final CoreEtablissementService coreEtablissementService;
    private final CoreReferentielService coreReferentielService;
    private final CoreFormationService coreFormationService;
    private final EtablissementMapper etablissementMapper;
    private final ReferentielMapper referentielMapper;
    private final FormationMapper formationMapper;

    @Override
    public void importEtablissementsRecherche() {
        log.info("Début import établissements dans ES");

        coreRechercheService.recreateEtablissementsIndex();

        coreRechercheService.saveEtablissements(
                coreEtablissementService
                        .findEtablissementsActif()
                        .stream()
                        .map(etablissementMapper::toRechercheEtablissementEntity)
                        .toList());

        coreRechercheService.deleteEtablissements(coreEtablissementService
                .findEtablissementsNotActif()
                .stream()
                .map(etablissementMapper::toRechercheEtablissementEntity)
                .toList());

        log.info("Fin import établissements dans ES");
    }

    @Override
    public void importMetiersRecherche() {
        log.info("Début import métiers dans ES");

        coreRechercheService.saveMetiers(
                coreReferentielService.findAll()
                        .stream()
                        .map(referentielMapper::toRechercheMetierEntity)
                        .toList());
    }

    @Override
    public void importFormationsRecherche() {
        log.info("Début import formations dans ES");

        coreRechercheService.saveFormations(
                coreFormationService.findAll()
                        .stream()
                        .map(formationMapper::toRechercheFormationEntity)
                        .toList());
    }
}
