package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.etablissements.*;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public interface WebEtablissementService {

    Optional<EtablissementDTO> findEtablissementByUai(String uai);

    Optional<EtablissementDTO> createEtablissement(EtablissementRequestDTO dto);

    Optional<EtablissementDTO> updateEtablissement(EtablissementRequestDTO dto);

    EtablissementDetailsDTO findEtablissementDetailsDtoByUai(@NonNull String uai);

    List<NatureDTO> getNatureList();

    int getNbrEtablissements();

    List<EtablissementDTO> findEtablissementListByCommune(String code);

    List<NatureWithEtablissementsDTO> getNatureWithEtablissementsList(String code);
}
