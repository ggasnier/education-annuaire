package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.EtablissementDetailsDto;
import com.guillaumegasnier.education.web.dto.etablissements.NatureDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDto> findEtablissementByUai(String uai);

    Optional<EtablissementDto> createEtablissement(EtablissementRequestDto dto);

    Optional<EtablissementDto> updateEtablissement(EtablissementRequestDto dto);

    EtablissementDetailsDto findEtablissementDetailsDtoByUai(@NonNull String uai);

    List<NatureDto> getNatureList();
}
