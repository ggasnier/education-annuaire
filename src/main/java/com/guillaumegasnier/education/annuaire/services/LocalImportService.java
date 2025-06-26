package com.guillaumegasnier.education.annuaire.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalImportService implements ImportService {
    @Override
    public String importEnEtablissements() {
        return "";
    }

    @Override
    public String importEsrEtablissements() {
        return "";
    }

    @Override
    public String importCarifEtablissements() {
        return "";
    }
}
