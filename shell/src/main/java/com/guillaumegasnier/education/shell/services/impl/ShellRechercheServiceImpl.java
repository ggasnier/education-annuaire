package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.services.ShellRechercheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShellRechercheServiceImpl implements ShellRechercheService {

    private final CoreRechercheService coreRechercheService;
    private final CoreEtablissementService coreEtablissementService;
    private final CoreReferentielService coreReferentielService;
    private final EtablissementMapper etablissementMapper;
    private final ReferentielMapper referentielMapper;

    @Override
    public void importEtablissementsRecherche() {
        coreRechercheService.saveEtablissements(
                coreEtablissementService
                        .findEtablissementsActif()
                        .stream()
                        .map(etablissementMapper::toRechercheEtablissementEntity)
                        .toList());
    }

    @Override
    public void importMetiersRecherche() {
        coreRechercheService.saveMetiers(
                coreReferentielService.findAll()
                        .stream()
                        .map(referentielMapper::toRechercheMetierEntity)
                        .toList());
    }
}
