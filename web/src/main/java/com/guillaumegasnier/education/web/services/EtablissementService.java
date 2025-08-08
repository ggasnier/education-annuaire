package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.EtablissementDto;

public interface EtablissementService {
    EtablissementDto getEtablissementByUai(String uai);
}
