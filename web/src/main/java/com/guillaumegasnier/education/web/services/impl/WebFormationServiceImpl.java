package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.repositories.formations.ActionFormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.FormationRepository;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.web.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.web.dto.formations.FormationDTO;
import com.guillaumegasnier.education.web.mappers.WebFormationMapper;
import com.guillaumegasnier.education.web.services.WebFormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebFormationServiceImpl implements WebFormationService {

    private final FormationRepository formationRepository;
    private final ActionFormationRepository actionFormationRepository;
    private final WebFormationMapper webFormationMapper;
    private final CoreFormationService coreFormationService;

    @Override
    public ActionFormationDTO getActionFormation(long id, long actionId) {
        return actionFormationRepository.findById(actionId).map(webFormationMapper::toActionFormationDTO).orElse(null);
    }

    @Override
    public FormationDTO getFormation(long id) {
        return formationRepository.findById(id).map(webFormationMapper::toFormationDTO).orElse(null);
    }

    @Override
    public long getNbrFormations() {
        return coreFormationService.getNbrFormations();
    }
}
