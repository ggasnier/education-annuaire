package com.guillaumegasnier.education.web.services;


import com.guillaumegasnier.education.web.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.web.dto.formations.FormationDTO;

public interface WebFormationService {

    ActionFormationDTO getActionFormation(long id, long actionId);

    FormationDTO getFormation(long id);

    long getNbrFormations();
}
