package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.etablissements.CommuneWithEtablissementsDTO;
import com.guillaumegasnier.education.web.dto.territoires.*;

import java.util.List;
import java.util.Optional;

public interface WebTerritoireService {

    List<PaysDTO> getPaysList();

    CommuneDTO createCommune(CommuneRequestDTO dto);

    List<AcademieWithDepartementsDTO> getAcademiesWithDepartements();

    Optional<CommuneDTO> findCommune(String code);

    Optional<DepartementDTO> findDepartement(String code);

    List<CommuneWithEtablissementsDTO> getCommuneWithEtablissementsDTO(String code);
}
