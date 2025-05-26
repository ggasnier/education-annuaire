package com.guillaumegasnier.education.annuaire.services;

import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.repositories.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtablissementService {

    private final EtablissementRepository etablissementRepository;

    @Autowired
    public EtablissementService(EtablissementRepository etablissementRepository) {
        this.etablissementRepository = etablissementRepository;
    }

    public Optional<EtablissementDto> createEtablissement(EtablissementRequestDto etablissement) {
        return Optional.empty();
    }
}
