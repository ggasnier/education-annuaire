package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.formations.ActionFormationDto;
import com.guillaumegasnier.education.web.dto.formations.FormationDto;

public interface WebFormationService {

    ActionFormationDto getActionFormation(long id, long actionId);

    FormationDto getFormation(long id);
}
