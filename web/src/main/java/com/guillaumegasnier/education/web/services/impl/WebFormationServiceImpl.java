package com.guillaumegasnier.education.web.services.impl;

import com.guillaumegasnier.education.core.repositories.formations.ActionFormationRepository;
import com.guillaumegasnier.education.core.repositories.formations.FormationRepository;
import com.guillaumegasnier.education.web.dto.formations.ActionFormationDto;
import com.guillaumegasnier.education.web.dto.formations.FormationDto;
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

    @Override
    public ActionFormationDto getActionFormation(long id, long actionId) {
        return actionFormationRepository.findById(actionId).map(webFormationMapper::toActionFormationDto).orElse(null);
    }

    @Override
    public FormationDto getFormation(long id) {
        return formationRepository.findById(id).map(webFormationMapper::toFormationDto).orElse(null);
    }
}
