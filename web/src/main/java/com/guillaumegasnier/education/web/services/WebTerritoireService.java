package com.guillaumegasnier.education.web.services;

import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.CommuneRequestDto;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;

import java.util.List;

public interface WebTerritoireService {

    List<PaysDTO> getPaysList();

    CommuneDto createCommune(CommuneRequestDto dto);
}
