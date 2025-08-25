package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.EtablissementDto;

import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDto> findEtablissementByUai(String uai);
}
