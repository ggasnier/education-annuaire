package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.etablissements.services.EtablissementService;
import com.guillaumegasnier.education.shell.services.IImportEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportEtablissementService implements IImportEtablissementService {

    private final EtablissementService etablissementService;

    @Autowired
    public ImportEtablissementService(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }
}
