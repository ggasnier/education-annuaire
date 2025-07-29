package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ImportFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportFormationServiceImpl implements ImportFormationService {

    private final FormationMapper formationMapper;

    @Autowired
    public ImportFormationServiceImpl(FormationMapper formationMapper) {
        this.formationMapper = formationMapper;
    }
}
