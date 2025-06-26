package com.guillaumegasnier.education.annuaire.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdImportService implements ImportService {

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
